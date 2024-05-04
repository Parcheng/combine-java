$combineWebUI.element.register("VIDEO", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildVideo(config, buildData) {
        const src = data[config.id] = dataFns.parseVariable(config.settings.src, buildData);
        const text = dataFns.parseVariable(config.settings.text, buildData);

        const body = [];
        const mp4 = domFns.build(config.mp4, null);
        mp4.setAttribute("src", src);
        body.push(mp4);

        const ogg = domFns.build(config.ogg, null);
        ogg.setAttribute("src", src);
        body.push(ogg);

        const webm = domFns.build(config.webm, null);
        webm.setAttribute("src", src);
        body.push(webm);

        body.push(domFns.build(config.content, config.settings.text ? text : config.content.text));
        return domFns.build(config.video, body);
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildVideo(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildVideo(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());