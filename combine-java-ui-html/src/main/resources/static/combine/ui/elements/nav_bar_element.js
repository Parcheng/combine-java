$combine.element.register("SYSTEM.NAV_BAR", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;
    const elementFns = $combine.element;
    const triggerFns = $combine.trigger;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildNavBar(instance, buildData) {
        const body = [];
        body.push(buildBrand(instance, buildData));
        body.push(buildBody(instance, buildData));
        body.push(buildRight(instance, buildData));
        return domFns.build(instance.template.navBar, body);
    }

    function buildBrand(instance, buildData) {
        const body = [];
        if (instance.brandImg) {
            const imgDom = domFns.build(instance.template.brandImgContent, null);
            imgDom.src = dataFns.parseVariable(instance.brandImg, buildData);
            body.push(imgDom);
        }
        if (instance.brandText) {
            const text = dataFns.parseVariable(instance.brandText, buildData);
            body.push(domFns.build(instance.template.brandTextContent, text));
        }

        const brandContentDom = domFns.build(instance.template.brandContent, body);
        return domFns.build(instance.template.brand, brandContentDom);
    }

    function buildBody(instance, buildData) {
        return domFns.build(instance.template.body, buildBodyNav(instance, buildData));
    }

    function buildBodyNav(instance, buildData) {
        return domFns.build(instance.template.bodyNav, buildBodyNavItems(instance, buildData));
    }

    function buildBodyNavItems(instance, buildData, checkedIndex) {
        const body = [];
        const navSettings = instance.nav;
        if (!navSettings || !navSettings.text) {
            return body;
        }

        const text = navSettings.text;

        const sourceBuildData = buildData;
        buildData = buildData ? buildData : [];
        if (!buildData || buildData.length === 0) {
            return body;
        }

        // 防止每次选则使都生成默认菜单
        if (checkedIndex === null || checkedIndex === undefined) {
            if (instance.defaultNavs) {
                const defaultNavs = instance.defaultNavs;
                for (let i = 0; i < defaultNavs.length; i++) {
                    const defaultNav = defaultNavs[i];
                    let index = defaultNav.index ? defaultNav.index : 0;
                    index = index >= 0 ? index : (buildData.length + index);

                    const defaultData = {
                        $isDefaultNav: true,
                        $triggers: defaultNav.triggers,
                        $text: dataFns.parseVariable(defaultNav.text, buildData)
                    };
                    if (buildData.length >= index && index >= 0) {
                        buildData.splice(index, 0, defaultData);
                    } else {
                        buildData.push(defaultData);
                    }
                }
            }

            checkedIndex = instance.defaultChecked;
            data[instance.id] = buildData;
        }

        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];
            const isChecked = checkedIndex == i;
            let itemBody;
            if (currData.$isDefaultNav) {
                itemBody = buildBodyNavItem(instance, sourceBuildData, currData.$triggers, currData.$text, i, isChecked);
            } else {
                itemBody = buildBodyNavItem(instance, currData, navSettings.triggers, text, i, isChecked);
            }
            body.push(domFns.build(instance.template.bodyNavItem, itemBody));
        }

        return body;
    }

    function buildBodyNavItem(instance, currData, triggers, text, index, checked) {
        const itemBody = [];

        text = dataFns.parseVariable(text, currData);
        const textDom = domFns.build(checked ? instance.template.navActive : instance.template.bodyNavItemText, text);
        triggerFns.build(triggers, textDom, currData);
        if (checked) {
            triggerFns.trigger(triggers, textDom);
        }
        domFns.appendProtity(textDom, "onclick", elementFns.buildCallFnCode(instance.id, "checked", index));
        itemBody.push(textDom);

        return itemBody;
    }

    function buildRight(instance, buildData) {
        const body = [];
        const opts = instance.opts;
        if (opts && opts instanceof Array) {
            for (let i = 0; i < opts.length; i++) {
                const buttonConfig = opts[i];
                const text = dataFns.parseVariable(buttonConfig.text, buildData);
                const buttonDom = domFns.build(instance.template.rightOptItem, domFns.build(instance.template.rightOptItemText, text));
                triggerFns.build(buttonConfig.triggers, buttonDom, buildData);
                body.push(buttonDom);
            }
        }

        const rightOpts = domFns.build(instance.template.rightOpts, body);
        return domFns.build(instance.template.right, rightOpts);
    }

    return {
        build: function build(instance, parentData) {
            instance = init(instance, parentData);
            return domFns.build(instance.template.external, buildNavBar(instance, parentData));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const bodyDom = externalDom.children[0].children[1]
                domFns.replace(bodyDom, buildBody(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            checked: function (instance, checkIndex) {
                const elementData = data[instance.id];
                const externalDom = document.getElementById(instance.id);
                if (externalDom && elementData) {
                    const navBodyDom = externalDom.children[0].children[1].children[0];
                    const navItemDoms = buildBodyNavItems(instance, elementData, checkIndex);
                    domFns.setBody(navBodyDom, navItemDoms);
                }
            }
        }
    }
})());