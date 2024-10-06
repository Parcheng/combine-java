$combine.element.register("SYSTEM.AUDIO", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildAudio(instance, buildData) {
        const templateConfig = instance.template;
        const src = data[instance.id] = dataFns.parseVariable(instance.src, buildData);
        const text = dataFns.parseVariable(instance.text, buildData);

        const body = [];
        const mp3 = domFns.build(templateConfig.mp3, null);
        mp3.setAttribute("src", src);
        body.push(mp3);

        const ogg = domFns.build(templateConfig.ogg, null);
        ogg.setAttribute("src", src);
        body.push(ogg);

        body.push(domFns.build(templateConfig.content, instance.text ? text : templateConfig.content.text));
        return domFns.build(templateConfig.audio, body);
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildAudio(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildAudio(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());