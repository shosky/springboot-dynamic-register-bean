# springboot-dynamic-register-bean
为什么要动态注册Bean？
在大部分业务系统开发中，都是基于Spring框架开发，由于Spring默认会扫描@Service、@Component等注解的Class注册到IoC容器中，但是这种注册是静态写死的，很多场景下需要基于条件注册Bean，或者需要在运行时动态注册Bean。比较经典的场景就是：
- **多种数据源**，通过配置动态注册对应的数据源Class。
- **桥接模式的实现**，在桥接模式中，Abstraction抽象角色注入的是Implemention实现接口，由于注入的是接口，只能在运行期间通过参数动态注入Implemention的实现类，所以只能动态动态注册实现。
- **多环境加载不同Bean**，很多中间件比如RocketMQ有开源版和商业版，通常在开发与测试环境使用的是开源版本，在生产环境使用商业版本。而开源版与商业版由于有不同，通过会通过不同Class来实现，所以需要动态注册到IoC容器中。
```java
@Controller
@RequestMapping("v1/pay")
public class PayController {

    @Autowired
    private PayChannelFactory payChannelFactory;
  
    /**
     * 桥接模式实现
     * @param uId       用户ID
     * @param amount    订单金额
     * @param channel   支付渠道：weixin/alipay
     * @param mode      支付方式：h5/scanface
     * @return
     */
    @ResponseBody
    @PostMapping("dopay")
    public String pay(@RequestParam String uId, @RequestParam BigDecimal amount, @RequestParam String channel, @RequestParam String mode) {
        return payChannelFactory.getChannelService(channel, mode).doPay(uId, amount);
    }
}
```

```java
@Component
public class PayChannelFactory {

    private ApplicationContext context;

    private static Map<String, Class> channelMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.leo.springboot.dynamic.register.bean.bridgepattern.channel");
        reflections.getSubTypesOf(AbstractChannelService.class).stream().forEach(item -> {
            Name annotation = item.getDeclaredAnnotation(Name.class);
            channelMap.put(annotation.value(), item);
        });
    }

    public PayChannelFactory(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    /**
     * 动态注册AbstractChannelService
     * @param channel
     * @param mode
     * @return
     */
    public AbstractChannelService getChannelService(String channel, String mode) {
        PayModeService modeService = PayModeFactory.getPayMode(mode);
        String beanName = new StringBuffer(channel).append(mode).toString();
        AbstractChannelService channelService = (AbstractChannelService) ManualRegistBeanUtil.registerBean
                ((ConfigurableApplicationContext) context, beanName, channelMap.get(channel), modeService);
        return channelService;
    }

}
```
