// ID重复检查（是否直接使用 id = xxx 的组件？）
// 引用暂时只允许引block的
// flow-component，flow-item 构建 取值 序列化 封装成对象，可new

var groupSettings = {};
var componentSettings = {};

var config = { init: {}, block: {}, before: {}, after: {}, flow: {} };

// document.getElementsById("group");

var buildTool = {
    groups: function(data) {
        var doms = [];
        for (let index = 0; index < data.length; index++) {
            var itemData = data[i];
            var itemDom = document.createElement("div");
            itemDom.id = "g-" + itemData.key;
            itemDom.className = "item";
            itemDom.textContent = itemData.name;
            doms.push(itemDom);
        }

        return doms;
    },
    components: function(parentKey, data) {
        var doms = [];
        for (let index = 0; index < data.length; index++) {
            var itemData = data[i];
            var itemDom = document.createElement("div");
            itemDom.id = "c-" + parentKey + "." + itemData.key;
            itemDom.className = "item";
            itemDom.textContent = itemData.name;
            doms.push(itemDom);
        }

        return doms;
    },
    componentSettings: function() {
    },
    flowPath: function() {
    },
    flowLine: function() {
    },
    flowSettings: function() {
    }
};