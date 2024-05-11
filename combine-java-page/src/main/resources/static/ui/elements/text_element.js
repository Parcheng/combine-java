$combineWebUI.element.register("TEXT", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildText(config, settings, buildData) {
        const body = [];
        if (!settings) {
            return body;
        }

        if (settings.lines && buildData) {
            buildData = buildData instanceof Array ? buildData : [buildData];
            for (let i = 0; i < buildData.length; i++) {
                const currData = buildData[i];
                const lineDoms = buildLine(config, settings, currData, 0);
                if (lineDoms.length > 0) {
                    body.push(lineDoms);
                }
            }
        }

        if (body.length == 0 && settings.defaultText) {
            body.push(settings.defaultText);
        }

        return body;
    }

    function buildLine(config, settings, data, level) {
        const body = [];
        for (let l = 0; l < settings.lines.length; l++) {
            const lineSettings = settings.lines[l];
            const lineDom = buildLineDom(config, settings.retract, l, level, lineSettings, data);
            if (lineDom) {
                body.push(lineDom);
            }
        }


        if (settings.children && settings.children.length > 0) {
            for (let cs = 0; cs < settings.children.length; cs++) {
                let childrenData = dataFns.parseVariable(settings.children[cs], data);
                childrenData = childrenData instanceof Array ? childrenData : [childrenData];
                for (let cd = 0; cd < childrenData.length; cd++) {
                    const childrenItemData = childrenData[cd];
                    if (childrenItemData) {
                        const childrenitemDom = buildLine(config, settings, childrenItemData, level + 1);
                        if (childrenitemDom && childrenitemDom.length > 0) {
                            body.push(domFns.build(config.children, childrenitemDom));
                        }
                    }
                }
            }
        }

        return body;
    }

    function buildLineDom(config, retract, lineIndex, level, lineSettings, currData) {
        let lineConfig = config.line;
        if (config.lines && config.lines.length > lineIndex) {
            lineConfig = configFns.initElement(config.lines[lineIndex], lineConfig, currData);
        }
        if (config.levels && config.levels.length > level) {
            lineConfig = configFns.initElement(config.levels[level], lineConfig, currData);
        }

        if (lineSettings.data) {
            currData = dataFns.parseVariable(lineSettings.data, currData);
        }

        let textArr = [];
        currData = currData instanceof Array ? currData : [currData];
        for (let i = 0; i < currData.length; i++) {
            let text = dataFns.parseVariable(lineSettings.text, currData[i]);
            if (text) {
                if (text instanceof Array) {
                    for (let j = 0; j < text.length; j++) {
                        textArr.push(text[j]);
                    }
                } else if (text instanceof Object) {
                    textArr.push(JSON.parse(text));
                } else if (text != "") {
                    textArr.push(text);
                }
            }
        }

        if (textArr.length == 0) {
            return null;
        }

        const title = lineSettings.title ? lineSettings.title : "";
        const separator = lineSettings.separator ? lineSettings.separator : "<br/>";
        const retractText = buildSpace(retract);
        const lineDom = domFns.build(lineConfig, null);
        lineDom.innerHTML = retractText + title + textArr.join(separator + retractText);
        return lineDom;
    }

    function buildSpace(retract) {
        let space = "";
        if (!retract) {
            return space;
        }

        for (let index = 0; index < retract.length; index++) {
            space += "\u00A0";
        }
        return space;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildText(config, config.settings, data));
        },
        refresh: function refresh(id, config, parentData) {
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildText(config, config.settings, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());