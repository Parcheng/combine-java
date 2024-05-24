$combine.element.register("SYSTEM.CONTENT", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildContent(templateConfig, settings, buildData) {
        const body = [];
        if (!settings) {
            return body;
        }

        if (settings.top) {
            body.push(buildImg(settings.top, templateConfig.top, templateConfig.topImg, buildData));
        }

        if (settings.left) {
            body.push(buildImg(settings.left, templateConfig.left, templateConfig.leftImg, buildData));
        }

        if (settings.content) {
            const contentBody = [];
            const title = dataFns.parseVariable(settings.content.title, buildData);
            contentBody.push(domFns.build(templateConfig.bodyTitle, title));

            if (settings.content.text) {
                let text = dataFns.parseVariable(settings.content.text, buildData);
                if (text) {
                    text = text instanceof Array ? text : [text];
                    for (let i = 0; i < text.length; i++) {
                        const textLines = text[i] instanceof Array ? text[i] : [text[i]];
                        for (let j = 0; j < textLines.length; j++) {
                            const textLine = textLines[j];
                            contentBody.push(domFns.build(templateConfig.bodyContent, textLine));
                        }
                    }
                }
            }

            const bodyDom = domFns.build(templateConfig.body, contentBody);
            bodyDom.style.width = dataFns.parseVariable(settings.content.size, buildData)  + "%";
            body.push(bodyDom);
        }

        if (settings.right) {
            body.push(buildImg(settings.right, templateConfig.right, templateConfig.rightImg, buildData));
        }

        return body;
    }

    function buildImg(imgSettings, template, imgTemplate, buildData) {
        const imgDom = domFns.build(imgTemplate, null);
        const imgSrc = dataFns.parseVariable(imgSettings.img, buildData);
        imgDom.setAttribute("src", imgSrc);

        const dom = domFns.build(template, imgDom);
        dom.style.width = imgSettings.size  + "%";
        if (imgSettings.height) {
            dom.style.height = imgSettings.height  + "px";
        }
        return dom;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildContent(instance.template, instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildContent(instance.template, instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());