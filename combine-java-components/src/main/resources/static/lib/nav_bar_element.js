$combineWebUI.element.register("NAV_BAR", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;
    const elementFns = $combineWebUI.element;
    const triggerFns = $combineWebUI.trigger;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildNavBar(config, buildData) {
        const body = [];
        body.push(buildBrand(config, buildData));
        body.push(buildBody(config, buildData));
        return domFns.build(config.navBar, body);
    }

    function buildBrand(config, buildData) {
        const settings = config.settings;
        const body = [];
        if (settings.brandImg) {
            const imgDom = domFns.build(config.brandImgContent, null);
            imgDom.src = dataFns.parseVariable(settings.brandImg, buildData);
            body.push(imgDom);
        }
        if (settings.brandText) {
            const text = dataFns.parseVariable(settings.brandText, buildData);
            body.push(domFns.build(config.brandTextContent, text));
        }

        const brandContentDom = domFns.build(config.brandContent, body);
        return domFns.build(config.brand, brandContentDom);
    }

    function buildBody(config, buildData) {
        const body = [];
        body.push(buildBodyNav(config, buildData));
        body.push(buildbodyRight(config, buildData));
        return domFns.build(config.body, body);
    }

    function buildBodyNav(config, buildData) {
        return domFns.build(config.bodyNav, buildBodyNavItems(config, buildData));
    }

    function buildBodyNavItems(config, buildData, level, checkedIndex) {
        const body = [];
        const navSettings = config.settings.nav;
        if (!navSettings || !navSettings.text) {
            return body;
        }

        const text = navSettings.text;
        const children = navSettings.children;

        level = level ? level : 0;
        const sourceBuildData = buildData;
        buildData = buildData ? buildData : [];
        buildData = buildData instanceof Array ? buildData : (children ? buildData[children] : null);
        if (!buildData || buildData.length == 0) {
            return body;
        }

        let isCheckTrigger = false;
        if (level == 0 && (checkedIndex == undefined || checkedIndex == null)) {
            if (config.settings.defaultNavs) {
                const defaultNavs = config.settings.defaultNavs;
                for (let i = 0; i < defaultNavs.length; i++) {
                    const defaultNav = defaultNavs[i];
                    let index = defaultNav.index ? defaultNav.index : 0;
                    index = index >= 0 ? index : (buildData.length + index);

                    const defaultData = {
                        $isDefaultNav: true,
                        $trigger: defaultNav.trigger,
                        $text: dataFns.parseVariable(defaultNav.text, buildData),
                        $children: dataFns.parseVariable(defaultNav.children, buildData)
                    };
                    if (buildData.length >= index && index >= 0) {
                        buildData.splice(index, 0, defaultData);
                    } else {
                        buildData.push(defaultData);
                    }
                }
            }

            isCheckTrigger = true; // 只有初始化才能触发
            checkedIndex = config.settings.defaultChecked;
            data[config.id] = buildData;
        }

        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];
            const isChecked = checkedIndex == i;
            let itemBody;
            if (currData.$isDefaultNav) {
                itemBody = buildBodyNavItem(config, sourceBuildData, currData.$trigger, currData.$text, currData.$children, level, i, isChecked, isCheckTrigger);
            } else {
                itemBody = buildBodyNavItem(config, currData, navSettings.trigger, text, children, level, i, isChecked, isCheckTrigger);
            }
            body.push(domFns.build(config.bodyNavItem, itemBody));
        }

        return body;
    }

    function buildBodyNavItem(config, currData, trigger, text, children, level, index, checked, isCheckTrigger) {
        const itemBody = [];

        text = dataFns.parseVariable(text, currData);
        const textDom = domFns.build(checked ? config.bodyNavItemTextActive : config.bodyNavItemText, text);
        triggerFns.build(trigger, textDom, currData);
        if (checked && isCheckTrigger) {
            triggerFns.trigger(trigger, textDom);
        }
        if (level == 0) {
            domFns.appendProtity(textDom, "onclick", elementFns.buildCallFnCode(config.id, "checked", index));
        }
        itemBody.push(textDom);

        children = dataFns.parseVariable(children, currData);
        if (children) {
            itemBody.push(domFns.build(config.bodyNavItemChildren, buildBodyNavItems(config, currData, level + 1)));
        }

        return itemBody;
    }

    function buildbodyRight(config, buildData) {
        const body = [];
        const buttons = config.settings.buttons;
        if (buttons && buttons instanceof Array) {
            for (let i = 0; i < config.settings.buttons.length; i++) {
                const buttonConfig = config.settings.buttons[i];
                const text = dataFns.parseVariable(buttonConfig.text, buildData);
                const buttonDom = domFns.build(config.bodyRightItem, domFns.build(config.bodyRightItemButton, text));
                triggerFns.build(buttonConfig.trigger, buttonDom, buildData);
                body.push(buttonDom);
            }
        }
        return domFns.build(config.bodyRight, body);
    }

    return {
        build: function build(config, parentData) {
            config = init(config, parentData);
            return domFns.build(config.external, buildNavBar(config, parentData));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const bodyDom = externalDom.children[0].children[1]
                domFns.replace(bodyDom, buildBody(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            checked: function (config, checkIndex) {
                const elementData = data[config.id];
                const externalDom = document.getElementById(config.id);
                if (externalDom && elementData) {
                    const navBodyDom = externalDom.children[0].children[1].children[0];
                    const navItemDoms = buildBodyNavItems(config, elementData, 0, checkIndex);
                    domFns.setBody(navBodyDom, navItemDoms);
                }
            }
        }
    }
})());