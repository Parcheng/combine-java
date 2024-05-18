$combineWebUI.element.register("SYSTEM.INPUT", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const toolFns = $combineWebUI.tools;

    function init(instance, parentData) {
        return instance;
    }

    function buildControls(instance, buildData) {
        let body = [];
        
        if (instance.beforeText) {
            body.push(buildAddon(instance, instance.beforeText, buildData));
        }

        const type = instance.inputType ? instance.inputType.toLowerCase() : "text";
        body.push(buildInput(instance, type, buildData));

        if (instance.afterText) {
            body.push(buildAddon(instance, instance.afterText, buildData));
        }

        return body;
    }


    function buildInput(instance, type, buildData) {
        const inputDom = domFns.build(instance.template.input, null);
        inputDom.type = type;

        const key = dataFns.parseVariableText(instance.key, buildData);
        if (key) {
            inputDom.name = key;
        }
        if (instance.value) {
            const val = dataFns.parseVariable(instance.value, buildData);
            if (val) {
                inputDom.value = val;
            }
        }
        return inputDom;
    }


    function buildAddon(instance, text, buildData) {
        const value = dataFns.parseVariable(text, buildData);
        const addonDom = domFns.build(instance.template.addon, value);
        return addonDom;
    }

    function hashPassword(password) {
        const salt = crypto.randomBytes(16).toString('base64'); // 生成盐
        const hash = crypto.pbkdf2Sync(password, salt, 100000, 64, 'sha512'); // 密码散列
        return `${salt}$${hash}`; // 返回合并的盐和散列
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            const buttons = buildControls(instance, data);
            const externalDom = domFns.build(instance.template.external, buttons);
            return externalDom
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildControls(config, parentData));
        },
        getData: function getData(id) {
            let externalDom = document.getElementById(id);
            if (externalDom && externalDom.children) {
                for (let i = 0; i < externalDom.children.length; i++) {
                    const inputDom = externalDom.children[i];
                    if (inputDom.tagName == 'INPUT') {
                        const inputType = inputDom.getAttribute("type");
                        if (inputType == "file") {
                            return inputDom.files[0];
                        } else if (inputType == "password"){
                            return toolFns.md5(inputDom.value);
                        } else {
                            return inputDom.value;
                        }
                    } else {
                        return inputDom.innerText;
                    }
                }
            }
            return null;
        }
    }
})());