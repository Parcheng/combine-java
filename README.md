# 介绍
combine-java 是一个轻量级低代码框架，在框架中一切的功能皆为组件，用户可以自由将各种组件组合在一起进行编排，实现自己的业务功能。<br>
JDK版本：17<br>
<br>
工程包含四个模块：<br>
combine-java-core：核心包，组件的所有操作都在这里<br>
combine-java-components：系统自带的组件包，封装了几十个公共组件<br>
combine-java-test：自动化测试模块<br>
combine-java-web-starter：快速使用模块，内置了 SpringBoot3，项目直接通过 pom 引入该模块即可快速使用。该工程也可以直接启动，启动后访问 http://127.0.0.1:8888/combine/api.html 可查看API<br>
<br>
组件（component）包含三部分：<br>
初始化配置：相当于同类型组件的全局配置，如：MySql 执行组件的数据库连接配置就是初始化配置<br>
逻辑配置：相当于组件独立配置，会直接影响组件的执行结果，如：MySql 执行组件要执行的具体SQL和增删改查就是逻辑配置<br>
组件执行结果：是组件直接完成后输出的结果<br>
<br>
# 如何使用？
通过 POM 引用 combine-java-web-starter<br>
```
<dependency>
    <artifactId>combine-java-web-starter</artifactId>
    <groupId>com.parch.combine</groupId>
    <version>1.0.0</version>
</dependency>
```

<br>编写一个Service，继承 starter 包提供的 AbstractCombineWebService：<br>
```
@Service
public class CombineWebService extends AbstractCombineWebService {
    public CombineWebService() {
        // 这里传入传入全局配置的JSON文件（配置项可参考API）
        super("config.json");
    }
}
```

<br>编写一个 Controller<br>
其中 call 方法是常规接口，uploadAndCall 方法是包含文件上次的接口<br>
```
@RestController
@RequestMapping("/api")
public class CombineWebController {

    @Autowired
    private CombineWebService combineWebService;

    @PostMapping("/{domain}/{function}")
    public DataResult call(@RequestBody Map<String, Object> params, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        return combineWebService.call(params, domain, function, request, response);
    }

    @PostMapping("/file/{domain}/{function}")
    public DataResult uploadAndCall(@RequestParam("params") String paramJson, @RequestParam("file") MultipartFile file, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return combineWebService.uploadAndCall(paramJson, file, domain, function, request, response);
    }
}
```

<br>编写配置文件 config.json 放在 resource 根目录下：<br>
```
{
  "initConfigs": [
    "configs/apidoc.json",  -- 系统内置的API页面的流程配置
    "configs/my_business.json"  -- 自己的业务流程配置（可以根据功能模块搞成多个文件）
  ],
  "initFlows": [
    "$build/api",  -- 系统内置的生成API页面的流程（流程可以理解为接口/功能实现逻辑），放在这里会在启动的时候生成API页面，启动完成后可访问
    "$init"   -- 自己的业务功能需要项目启动时执行的流程（如：建MySql表，初始化缓存等）
  ],
  "openRegisterConfig": false,  -- 是否开放注册，true 表示可以在项目运行期间动态注册流程
  "requestIdKey": "requestID",  -- 全局 requestId 的 key，方便在日志中进行功能的全链路追踪
  "printComponentResult": true  -- 是否打印组件的执行结果
  -- 其他配置详见API
}
```

<br>编写流程配置文件 configs/my_business.json 放在 resource 根目录下：<br>
通过在该文件编写组件配置，来实现自己的业务功能，详见API<br>
<br>
# 自定义组件
通过 SPI 加载<br>
在 resource/META_INFO/services 下创建文件 com.parch.combine.core.settings.spi.AbsGetComponents<br>
```
com.test.components.GetMyComponents
```

<br>创建 GetMyComponents 类：<br>
提示：这里创建的是组件包，一个组件包里会包含多个组件，系统会在加载时会自动扫描 GetMyComponents 类的所在包，获取该包下的所有组件<br>
注意：这个类必须要继承 com.parch.combine.core.settings.spi.AbsGetComponents<br>
```
public class GetMyComponents extends AbsGetComponents {
    public GetMyComponents() {
        super("my", "我的组件包", GetMy1Components.class);
    }
}
```

<br>创建自定义的组件1： My1Component 类<br>
提示：系统是根据 Component 直接来识别组件，该注解定义了组件的类型KEY、组件名称、组件的初始化配置和逻辑配置类<br>
```
@Component(key = "test1", name = "我的组件1", logicConfigClass = My1LogicConfig.class, initConfigClass = My1InitConfig.class)
@ComponentResult(name = "我的组件1执行结果")
public class My1Component extends AbsComponent<My1InitConfig, My1LogicConfig> {

    public DataEnumGetComponent() {
        super(My1InitConfig.class, My1LogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        My1LogicConfig logicConfig = getLogicConfig();
        My1InitConfig initConfig = getInitConfig();

        // 初始化时的配置检查
        if (CheckEmptyUtil.isEmpty(logicConfig.getXXX())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "XXX为空"));
        }
        if (CheckEmptyUtil.isEmpty(initConfig.getXXX())) {
            result.add(ComponentErrorHandler.buildCheckInitMsg(logicConfig, "XXX为空"));
        }

        ... ...

        return result;
    }

    @Override
    public DataResult execute() {
        My1LogicConfig logicConfig = getLogicConfig();
        My1InitConfig initConfig = getInitConfig();

        try {
            ... ...
            return DataResult.success(result);
        } catch (Exception e) {
            ComponentErrorHandler.print(My1ErrorEnum.FAIL, e);
            return DataResult.fail(My1ErrorEnum.FAIL);
        }
    }
}
```



