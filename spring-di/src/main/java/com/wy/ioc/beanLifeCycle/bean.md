### Bean完整的声明周期经理了各种方法的调用，这些方法可以划分为以下几类
- bean自身的方法:
- 1. 这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destory-method指定的方法
- bean级生命周期接口方法
- 1. 这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法

- 容器级生命周期接口方法　
- 1. 这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。

- 工厂后处理器接口方法　
- 1. 这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。