package com.parch.combine.gui.core.call.option;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;

public interface IGUIOptionHandler {

    String getId();

    boolean setOptions(GUIControlOptionConfig[] options);

    boolean addOption(GUIControlOptionConfig option);

    boolean cleanOptions();
}
