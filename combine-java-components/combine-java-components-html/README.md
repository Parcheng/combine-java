# 自定义HTML元素组件
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