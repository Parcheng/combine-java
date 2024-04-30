package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.TitleSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class TitleElementEntity extends ElementEntity<TitleSettings> {

    private ElementDomConfig external;

    private ElementDomConfig h1;

    private ElementDomConfig h2;

    private ElementDomConfig h3;

    private ElementDomConfig h4;

    private ElementDomConfig h5;

    private ElementDomConfig h6;

    private ElementDomConfig top;

    private ElementDomConfig bottom;

    public TitleElementEntity() {
        super(ElementTypeEnum.TITLE);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getH1() {
        return h1;
    }

    public void setH1(ElementDomConfig h1) {
        this.h1 = h1;
    }

    public ElementDomConfig getH2() {
        return h2;
    }

    public void setH2(ElementDomConfig h2) {
        this.h2 = h2;
    }

    public ElementDomConfig getH3() {
        return h3;
    }

    public void setH3(ElementDomConfig h3) {
        this.h3 = h3;
    }

    public ElementDomConfig getH4() {
        return h4;
    }

    public void setH4(ElementDomConfig h4) {
        this.h4 = h4;
    }

    public ElementDomConfig getH5() {
        return h5;
    }

    public void setH5(ElementDomConfig h5) {
        this.h5 = h5;
    }

    public ElementDomConfig getH6() {
        return h6;
    }

    public void setH6(ElementDomConfig h6) {
        this.h6 = h6;
    }

    public ElementDomConfig getTop() {
        return top;
    }

    public void setTop(ElementDomConfig top) {
        this.top = top;
    }

    public ElementDomConfig getBottom() {
        return bottom;
    }

    public void setBottom(ElementDomConfig bottom) {
        this.bottom = bottom;
    }
}
