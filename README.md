# 介绍
**combine-java** 是一个轻量级低代码框架，在框架中一切的功能皆为组件，您可以自由将各种组件组合在一起进行编排，实现自己的业务功能。<br>
**推荐JDK版本：11+**<br>
<br>

**工程结构：**<br>
**combine-java-core**：核心模块，所有的核心功能都在这里<br>
**combine-java-components**：系统内置组模块 - POM工程<br>
**combine-java-ui-html**：系统自带的UI页面组件模块，封装了一些公共UI元素组件<br>
**combine-java-starter**：快速使用模块，包含：核心模块、所有系统组件、页面组件模块，通过 POM 引入后即可使用全部功能<br>
**combine-java-spring-web**：内置了 SpringBoot 的快速使用模块，项目直接通过 pom 引入后即可快速使用。该工程可以直接启动，仅提供了 API 相关的页面和接口<br>
<br>
**系统内置组模块（combine-java-components）包含：**<br>
**combine-java-components-base**: 系统内置组件 - 基础组件包<br>
**combine-java-components-gui**: 系统内置组件 - SWING视图组件包<br>
**combine-java-components-mysql**: 系统内置组件 - MySql数据库组件包<br>
**combine-java-components-redis**: 系统内置组件 - Redis组件包<br>
**combine-java-components-rocketmq**: 系统内置组件 - RocketMQ组件包<br>
<br>

**本地启动并访问 API：**<br>
启动 combine-java-spring-web 工程<br>
组件的API地址：http://127.0.0.1:8888/combine/page/api<br>
UI的API地址：http://127.0.0.1:8888/combine/page/ui-api<br>
<br>

### 流程（FLOW）
**流程**：每个**流程**都有一个KEY（可以理解为请求接口的地址），并由多个**组件**组成（通过编排组件的执行过程来实现接口的业务逻辑，并返回结果）<br>
**组件**：业务功能的最小配置单元，基于输入数据来实现某个功能并将结果输出，它由初始化配置类、逻辑配置类、组件实现类组成<br>
<br>
通过引入 combine-java-components 包，可以使用很多系统内置的公共**组件**<br>
也可以通过 combine-java-core 核心包提供 SPI 机制自定义实现自己的**组件**<br>
<br>
**组件（component）包含三部分：**<br>
**初始化配置**：相当于同类型组件的全局配置，如：MySql 执行组件的数据库连接配置就是初始化配置<br>
**逻辑配置**：相当于组件独立配置，会直接影响组件的执行结果，如：MySql 执行组件要执行的具体SQL和增删改查就是逻辑配置<br>
**组件执行结果**：是组件直接完成后输出的结果<br>
<br>

### 页面（PAGE）
**页面**：每个**页面**都有一个KEY（可以理解为页面路径/名称），并由多个**UI元素组**组成<br>
**UI元素组**：每个**元素组**都有一个ID（用于在页面中引用），和一个**UI元素**的集合，用于组装多个**UI元素**组成一个完整的页面模块<br>
**UI元素**：组成页面的最小配置单元，它由元素配置类、元素模板配置类、元素功能的JS实现文件，元素模板配置Json文件组成
<br>
通过引入 combine-java-ui-elements 包，可以使用很多系统内置的公共**UI元素**<br>
也可以通过 combine-java-core 核心包提供 SPI 机制自定义实现自己的**UI元素**<br>
<br>
**UI组件（element）包含四部分：**<br>
元素配置：页面元素的配置<br>
元素模板配置：页面元素的模板配置，主要用于配置HTML标签的样式和属性等<br>
元素数据加载配置：用于**UI组件**加载外部数据（如：调用接口，JS函数等）<br>
事件触发配置：**UI组件**中可以包含多种事件触发配置，用于配置触发的事件（如：按钮点击等）和触发后要做的事（如：调用接口、调用函数、刷新页面的某个模块等）<br>
<br>

# 如何使用？
通过 POM 引用 combine-java-spring-web<br>
```$xml
<dependency>
    <artifactId>combine-java-spring-web</artifactId>
    <groupId>com.parch.combine</groupId>
    <version>0.10.0</version>
</dependency>
```

<br>编写一个组件Service和一个UI页面的Service，继承核心提供的 AbstractCombineWebService：<br>
```$java
@Service
public class CombineJavaService extends AbstractCombineJavaService {
    public CombineWebService() {
        // 这里传入传入全局配置的JSON文件（配置项可参考API）
        super("config.json");
    }

    // ... 可以写一些自定义方法 ...
}

@Service
public class CombineJavaPageService extends AbstractCombineJavaUIService {
    public CombineWebService() {
        // 这里传入传入全局配置的JSON文件（配置项可参考API）
        super("ui_config.json");
    }

    // ... 可以写一些自定义方法 ...
}
```

<br>编写一个 Controller<br>
其中 call 方法是常规接口，uploadAndCall 方法是包含文件上传的接口，page 方法是访问页面<br>
```$java
@RestController
@RequestMapping("/")
public class CombineTestController {

    @Autowired
    private CombineJavaService combineWebService;

    @Autowired
    private CombineJavaPageService combineJavaPageService;

    @PostMapping("flow/{domain}/{function}")
    public FlowResult call(@RequestBody Map<String, Object> params, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) {
        return combineWebService.call(params, domain, function, request, response);
    }

    @PostMapping("file-flow/{domain}/{function}")
    public FlowResult uploadAndCall(@RequestParam("params") String paramJson, @RequestParam("file") MultipartFile file, @PathVariable(name = "domain") String domain, @PathVariable(name = "function") String function, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return combineWebService.uploadAndCall(paramJson, file, domain, function, request, response);
    }

    @GetMapping("page/{pageKey}")
    public String page(@PathVariable(name = "pageKey") String pageKey) {
        return combineJavaPageService.getPage(pageKey);
    }
}
```

<br>编写配置文件 config.json 放在 resource 根目录下：<br>
```$json
{
  "initConfigs": [
    "configs/api_flow.json",    -- 系统内置的流程配置API（可以不引入）
    "configs/ui_api_flow.json"  -- 系统内置的UI配置API（可以不引入）
    "configs/my_business.json"  -- 自己的业务流程配置（可以根据功能模块搞成多个文件）
  ],
  "initFlows": [
    "$init"   -- 自己的业务功能需要项目启动时执行的流程（如：建MySql表，初始化缓存等）
  ],
  "openRegisterConfig": false,  -- 是否开放注册，true 表示可以在项目运行期间动态注册流程
  "requestIdKey": "requestID",  -- 全局 requestId 的 key，方便在日志中进行功能的全链路追踪
  "printComponentResult": true  -- 是否打印组件的执行结果
  -- 其他配置详见API
}
```
编写流程配置文件 configs/my_business.json 放在 resource 根目录下：<br>
通过在该文件编写组件配置，来实现自己的业务功能，详见API<br>

<br>编写 UI 配置文件 ui_config.json 放在 resource 根目录下：<br>
```$json
{
  "configs": [
    "configs/api_page.json",    -- 系统内置的流程配置API（可以不引入）
    "configs/ui_api_page.json"  -- 系统内置的UI配置API（可以不引入）
    "configs/my_page.json"      -- 自己的页面配置（可以根据功能模块搞成多个文件）
  ],
  "baseUrl": "http://127.0.0.1:8888/combine",    -- 根URL
  "systemUrl": "http://127.0.0.1:8888/combine"   -- 系统文件加载路径的根URL
}
```
编写UI配置文件 configs/my_page.json 放在 resource 根目录下：<br>
通过在该文件编写组件配置，来实现自己的业务功能，详见API<br>
<br>

# 自定义组件
通过 SPI 加载<br>
在 resource/META_INFO/services 下创建文件 com.parch.combine.core.component.spi.AbstractGetComponents<br>
```
com.test.components.GetMyComponents
```

<br>创建 GetMyComponents 类：<br>
提示：这里创建的是组件包，一个组件包里会包含多个组件，系统会在加载时会自动扫描 GetMyComponents 类的所在包，获取该包下的所有组件<br>
注意：这个类必须要继承 AbsGetComponents<br>
```
public class GetMyComponents extends AbsGetComponents {
    public GetMyComponents() {
        super("my", "我的组件包", GetMyComponents.class);
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
    public FlowResult execute() {
        My1LogicConfig logicConfig = getLogicConfig();
        My1InitConfig initConfig = getInitConfig();

        try {
            ... ...
            return FlowResult.success(result);
        } catch (Exception e) {
            ComponentErrorHandler.print(My1ErrorEnum.FAIL, e);
            return FlowResult.fail(My1ErrorEnum.FAIL);
        }
    }
}
```

<br>创建 My1Component 的初始化配置类和逻辑配置类<br>
```$xslt
public interface My1LogicConfig extends ILogicConfig {

    // 使用 Field 相关注解，可以在访问 API 页面时生成自定义组件的描述信息
    @Field(key = "key", name = "XXXX", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("XXXXXXXXXXXXXXXXXX")
    String key();
}

public interface My1InitConfig extends IInitConfig {
    ... 自定义配置项 ...
}
```
<br>

# 自定义UI元素组件
通过 SPI 加载<br>
在 resource/META_INFO/services 下创建文件 AbsGetUIElements<br>
```
com.test.ui.elements.GetMyUIElements
```

<br>创建 GetMyUIElements 类：<br>
提示：这里创建的是UI元素包，一个UI元素包里会包含多个UI元素，系统会在加载时会自动扫描 GetMyUIElements 类的所在包，获取该包下的所有UI元素<br>
注意：这个类必须要继承 AbsGetUIElements<br>
```
public class GetMyUIElements extends AbsGetUIElements {
    public GetMyUIElements() {
        super("my", "我的UI元素包", GetMyUIElements.class);
    }
}
```

<br>创建自定义的元素配置类： MyElementConfig 类<br>
提示：系统是根据 PageElement 直接来识别组件，该注解定义了元素的类型KEY、元素名称、元素的模板配置类<br>
```
@PageElement(key = "myElement", name = "我的自定义元素", templateClass = AudioElementTemplateConfig.class)
public class MyElementConfig extends ElementConfig<AudioElementTemplateConfig> {

    // 使用 Field 相关注解，可以在访问 API 页面时生成自定义组件的描述信息
    @Field(key = "src", name = "XXXX", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("XXXXXXXXXXXXXXXXXX")
    private String src;

    public AudioElementConfig() {
        // 该UI元素的前端JS地址、CSS地址、模板JSON文件地址，及模板类Class对象
        super("/lib/elements/my_element.js", "/lib/style/my_element.css", "/lib/temolate/my_template.json", MyElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {
        ... 自定义配置初始化逻辑 ... 
    }

    @Override
    protected List<String> checkConfig() {
        ... 自定义配置检查逻辑，返回异常信息 ... 
        return null;
    }

    ... 其他自定义配置项和GET/SET方法 ...
}
```

<br>创建自定义的元素模板配置类： MyElementTemplateConfig 类<br>
```
public class MyElementTemplateConfig extends ElementTemplateConfig {

    // DomConfig 对象时是通用的 DOM 元素配置类，通过 FieldRef 注解可以直接引用系统内置的 DomConfig 属性 API 信息
    @Field(key = "headerDiv", name = "XXXXX", type = FieldTypeEnum.ANY)
    @FieldRef(DomConfig.class)
    private DomConfig headerDiv;

    ... 其他自定义配置项和GET/SET方法 ...
}
```

<br>创建UI元素JS实现： my_element.js<br>
```
$combine.element.register("my.myElement", (function () {
    const domFns = $combine.dom;

    ... ...

    return {
        build: function build(instance, data) {
            ... 根据数据（data）构建该元素的页面DOM ...
            return domFns.build(instance.template.external, ...);
        },
        refresh: function refresh(id, instance, parentData) {
            ... 根据新数据（parentData）刷新页面DOM元素实现 ...
        },
        getData: function getData(id) {
            ... 根据ID获取数据实现 ...
            return null;
        }
    }
})());
```

<br>创建UI元素模板JSON文件： my_template.js<br>
```
{
    "external": {
        "tag": "div",   -- HTML标签
        "class": "",    -- 样式Class配置
        "style": "",    -- 样式配置，格式如：“width:20px;height:20px;”
        "text": "",     -- HTML标签内的文本内容
        "properties": {}   -- HTML标签的属性配置，如: 可以设置 img 标签 src 属性
    },
    "headerDiv": {
        "tag": "div",
        "class": "...",
        "style": "...",
        "properties": { ... }
    },
    ... 其他DOM元素配置 ...
}
```
<br>

# 建议和反馈
您有任何问题请反馈到我的邮箱：p568866418@163.com
感谢支持！
