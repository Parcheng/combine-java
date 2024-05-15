$combineWebUI.element.register("SYSTEM.THUMBNAIL", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildThumbnail(instance, buildData) {
        const body = [];
        if (!buildData) {
            return body;
        }

        const thisData = data[instance.template.id] = {}
        if (instance.path) {
            const imgDom = domFns.build(instance.template.img, null);
            imgDom.src = thisData.img = dataFns.parseVariable(instance.path, buildData);
            body.push(imgDom);
        }

        const contentBody = [];
        if (instance.title) {
            const title = thisData.title = dataFns.parseVariable(instance.title, buildData);
            contentBody.push(domFns.build(instance.template.contentTitle, title));
        }
        if (instance.text && instance.text.length > 0) {
            thisData.text = [];
            for (let i = 0; i < instance.text.length; i++) {
                const textItem = dataFns.parseVariable(instance.text[i], buildData);
                thisData.text.push(textItem);
                contentBody.push(domFns.build(instance.template.contentText, textItem));
            }
        }
        if (instance.buttons && instance.buttons.length > 0) {
            for (let i = 0; i < instance.buttons.length; i++) {
                const buttonItem = instance.buttons[i];
                const buttonItemText = dataFns.parseVariable(buttonItem.text, buildData);

                const buttonItemDom = domFns.build(instance.template.contentButton, buttonItemText);
                if (buttonItem.trigger) {
                    triggerFns.build(buttonItem.trigger, buttonItemDom, buildData);
                }
                contentBody.push(buttonItemDom);
            }
        }
        if (contentBody.length > 0) {
            body.push(domFns.build(instance.template.content, contentBody));
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildThumbnail(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildThumbnail(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());