package com.wy.solution.servlet;

import com.wy.solution.response.MyResponse;
import com.wy.solution.service.TransferService;
import com.wy.solution.service.impl.TransferServiceImpl;
import com.wy.solution.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname TransferSerlvert
 * @Description
 *
 * 分析:关于此种方式的实现，没有基于任何的一个框架的实现，通过new对象进行依赖，以及通过jdbc的方式来实现数据库的连接和调用
 *
 * 优缺点分析:
 * 优点:写起来简单，只要对于工程师来讲都非常的可读，而且可写，即使不了解任何框架的前提下，只要对Java语言有一些了解，了解一些
 * JSR规范，就可以读懂，而且可以写出来一样的功能。
 * 缺点:缺点其实也非常明显，耦合度太高，正常的设计要符合设计原则，高内聚低耦合，这样扩展性，维护性，稳定性会更强， 才能算是
 * 优秀的代码，比如说现在通过jDBC来实现的，后续要求更换数据库连接方式，这样，就会涉及到业务类也要进行更改，而且用的地方可能会
 * 非常多，这种情况下，影响面很大，不利于系统的扩展性、维护性和稳定性。不符合面向接口开发的原则。
 * 问题点比较多，而且确实不利于系统的稳定性保障，所以要通过其他的方式来进行解耦，使得开发成本降低，开发效率提高，开发效能提高，问题率下降
 * 所以就要进行来进行解耦
 *  问题1:耦合度太高
 *  问题2：如果要求要么全部执行成功，要么全部执行失败，事务的特性，这种实现方式的情况下也没办法来进行保证
 *
 *
 * 解决:
 * 所以说既然有以上问题的出现，必然是需要来寻求解决方案来进行解决的。
 * @Date 2021/8/11 2:01 上午
 * @Company
 * @Author wy
 */
@WebServlet(name = "transferServlet", urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    private TransferService transferService = new TransferServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------------");
        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");

        String fromCardNo = req.getParameter("fromCardNo");
        System.out.println(fromCardNo);

        String toCardNo = req.getParameter("toCardNo");
        System.out.println(toCardNo);
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);
        System.out.println(money);
        MyResponse result = new MyResponse();

        try {

            // 2. 调用service层方法
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
            result.setMessage(e.toString());
        }

        // 响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }
}
