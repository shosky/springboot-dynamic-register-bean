# springboot-dynamic-register-bean
为什么要动态注册Bean？
在大部分业务系统开发中，都是基于Spring框架开发，由于Spring默认会扫描@Service、@Component等注解的Class注册到IoC容器中，但是这种注册是静态写死的，很多场景下需要基于条件注册Bean，或者需要在运行时动态注册Bean。比较经典的场景就是：
- **多种数据源**，通过配置动态注册对应的数据源Class。
- **桥接模式的实现**，在桥接模式中，Abstraction抽象角色注入的是Implemention实现接口，由于注入的是接口，只能在运行期间通过参数动态注入Implemention的实现类，所以只能动态动态注册实现。
- **多环境加载不同Bean**，很多中间件比如RocketMQ有开源版和商业版，通常在开发与测试环境使用的是开源版本，在生产环境使用商业版本。而开源版与商业版由于有不同，通过会通过不同Class来实现，所以需要动态注册到IoC容器中。
