$combineWebUI.element.register("AUDIO", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildAudio(config, buildData) {
        const src = data[config.id] = dataFns.parseVariable(config.settings.src, buildData);
        const text = dataFns.parseVariable(config.settings.text, buildData);

        const body = [];
        const mp3 = domFns.build(config.mp3, null);
        mp3.setAttribute("src", src);
        body.push(mp3);

        const ogg = domFns.build(config.ogg, null);
        ogg.setAttribute("src", src);
        body.push(ogg);

        body.push(domFns.build(config.content, config.settings.text ? text : config.content.text));
        return domFns.build(config.audio, body);
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildAudio(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildAudio(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());