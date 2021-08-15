package com.wy.solution.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BeanFactory
 * @Description 生成Bean的
 * 工厂类  生产对象(使用反射技术来进行实现)
 * @Date 2021/8/11 8:05 下午
 * @Company
 * @Author wy
 */
public class BeanFactory {
    /**
     * 任务1 读取解析xml文件，通过反射技术生成对象并且存储待用(map集合存储)
     * 任务2 对外提供获取实例对象的方法(根据类属性id来进行读取 key)
     */

    private static Map<String, Object> beanMap = new ConcurrentHashMap<>();

    static {
        //任务1 读取解析xml文件，通过反射技术生成对象并且存储待用(map集合存储)
        // step1 加载xml
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        // step2 解析xml
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(resourceAsStream);
            Element rootElement = doc.getRootElement();
            List<Element> elementList = rootElement.selectNodes("//bean");
            for (int i = 0; i < elementList.size(); i++) {
                // 处理Bean元素并且实例化对象
                dealBeanElementAndInstance(elementList, i);
            }
            // 实例化完成之后要进行维护对象的依赖关系，检查哪些对象需要进行注入，根据xml配置，传入相应的值
            // 有property子元素的bean就有传值要求
            dealBeanDI(rootElement);
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static void dealBeanDI(Element rootElement) throws InvocationTargetException, IllegalAccessException {
        List<Element> elementList = rootElement.selectNodes("//property");
        for (int i = 0; i < elementList.size(); i++) {
            Element element = elementList.get(i);
            String name = element.attributeValue("name");
            String ref = element.attributeValue("ref");
            // set + name 就是需要注入的对象
            // 找到当前所依赖的bean
            Element parent = element.getParent();
            // 获取父节点的id属性
            String parentId = parent.attributeValue("id");
            // 根据父节点id获取对象，因为要往此对象中进行注入DI set
            Object parentBean = beanMap.get(parentId);
            // 获取父节点所代表的对象的所有的方法，并且要判断，如果和所需要注入的对象的set+name相同的话，就是需要注入所调用的方法
            Method[] methods = parentBean.getClass().getMethods();
            for (int j = 0; j < methods.length; j++) {
                if (methods[j].getName().equalsIgnoreCase("set" + name)) {
                    Object o = beanMap.get(ref);
                    methods[j].invoke(parentBean, o);
                }
            }
            // 在上面注入之后会所依赖的父节点的bean已经发生了变化，所以要重新进行放到map中
            beanMap.put(parentId, parentBean);
        }
    }


    public static Object getBean(String id) {
        Object realClazz = beanMap.get(id);
        return realClazz;
    }

    private static void dealBeanElementAndInstance(List<Element> elementList, Integer index) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Element element = elementList.get(index);
//                <ioc class="JdbcAccountDaoImpl" id="accountDao"/>
//                <ioc class="TransferServiceImpl" id="transferService"/>
        // 处理每个bean元素，获取该元素下的id、class属性的值
        // 根据id获取属性
        String id = element.attributeValue("id");
        // 获取元素全限定类名
        String clazz = element.attributeValue("class");
        // 根据反射获取class对象
        Class<?> typeClass = Class.forName(clazz);
        // 进行实例化
        // 无参构造是私有的，不能进行实例化对象  或者是通过相关方法，把权限修改为public，然后再来进行实例化
        // 把构造函数方法权限进行重新设置，打破现有的private 重新设置 就可以进行实例化了
        // method.setAccessible(true);
        Object realClass = typeClass.newInstance();
        // 存储到beanMap中
        beanMap.put(id, realClass);
    }
}
