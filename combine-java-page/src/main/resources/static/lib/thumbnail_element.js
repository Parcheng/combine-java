$combineWebUI.element.register("THUMBNAIL", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildThumbnail(config, buildData) {
        const body = [];
        const settings = config.settings;
        if (!buildData) {
            return body;
        }

        const thisData = data[config.id] = {}
        if (settings.path) {
            const imgDom = domFns.build(config.img, null);
            imgDom.src = thisData.img = dataFns.parseVariable(settings.path, buildData);
            body.push(imgDom);
        }

        const contentBody = [];
        if (settings.title) {
            const title = thisData.title = dataFns.parseVariable(settings.title, buildData);
            contentBody.push(domFns.build(config.contentTitle, title));
        }
        if (settings.text && settings.text.length > 0) {
            thisData.text = [];
            for (let i = 0; i < settings.text.length; i++) {
                const textItem = dataFns.parseVariable(settings.text[i], buildData);
                thisData.text.push(textItem);
                contentBody.push(domFns.build(config.contentText, textItem));
            }
        }
        if (settings.buttons && settings.buttons.length > 0) {
            for (let i = 0; i < settings.buttons.length; i++) {
                const buttonItem = settings.buttons[i];
                const buttonItemText = dataFns.parseVariable(buttonItem.text, buildData);

                const buttonItemDom = domFns.build(config.contentButton, buttonItemText);
                if (buttonItem.trigger) {
                    triggerFns.build(buttonItem.trigger, buttonItemDom, buildData);
                }
                contentBody.push(buttonItemDom);
            }
        }
        if (contentBody.length > 0) {
            body.push(domFns.build(config.content, contentBody));
        }

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildThumbnail(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildThumbnail(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());