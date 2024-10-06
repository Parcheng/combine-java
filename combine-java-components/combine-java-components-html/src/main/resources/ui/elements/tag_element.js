$combine.element.register("SYSTEM.TAG", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildTags(instance, buildData) {
        const body = [];
        if (!buildData) {
            return body;
        }

        let sizeStyle = getStyleBySize(instance.size);
        if (sizeStyle) {
            sizeStyle = " c-" + sizeStyle;
        }

        data[instance.id] = [];
        buildData = buildData instanceof Array ? buildData : [buildData];
        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];
            const tagType = dataFns.parseVariable(instance.tagType, currData);
            const itemConfig = instance.template[tagType];
            if (!itemConfig) {
                continue;
            }

            const text = dataFns.parseVariable(instance.text, currData);
            itemConfig.properties.class = itemConfig.properties.class ? (itemConfig.properties.class + sizeStyle) : sizeStyle;

            body.push(domFns.build(itemConfig, text));
            data[instance.id].push(text);
        }

        return body;
    }

    function getStyleBySize(size) {
        size = size ? size : 2;
        if (size <= 1) {
            return "xs"
        } else if (size <= 2) {
            return "small"
        } else if (size <= 3) {
            return "medium"
        } else {
            return "large"
        }
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