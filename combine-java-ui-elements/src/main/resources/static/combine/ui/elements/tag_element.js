$combineWebUI.element.register("SYSTEM.TAG", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildTags(instance, buildData) {
        const body = [];
        const itemConfig = instance.template[instance.tagType];
        if (!buildData || !itemConfig) {
            return body;
        }

        data[instance.id] = [];
        buildData = buildData instanceof Array ? buildData : [buildData];
        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];
            const text = dataFns.parseVariable(instance.text, currData);
            body.push(domFns.build(itemConfig, text));
            data[instance.id].push(text);
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildTags(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTags(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());