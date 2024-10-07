$combine.element.register("SYSTEM.VIDEO", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildVideo(instance, buildData) {
        const src = data[instance.id] = dataFns.parseVariable(instance.src, buildData);
        const text = dataFns.parseVariable(instance.text, buildData);

        const body = [];
        const mp4 = domFns.build(instance.template.mp4, null);
        mp4.setAttribute("src", src);
        body.push(mp4);

        const ogg = domFns.build(instance.template.ogg, null);
        ogg.setAttribute("src", src);
        body.push(ogg);

        const webm = domFns.build(instance.template.webm, null);
        webm.setAttribute("src", src);
        body.push(webm);

        body.push(domFns.build(instance.template.content, instance.text ? text : instance.template.content.text));
        return domFns.build(instance.template.video, body);
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildVideo(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildVideo(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());