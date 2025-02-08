package com.parch.combine.core.component.settings.helper;

import com.parch.combine.core.component.settings.config.IOptionSetting;

import java.lang.reflect.Method;

public class FieldSelectHelper {

    private FieldSelectHelper() {}

    public static IOptionSetting[] getOptions(Class<? extends IOptionSetting> enumClass) {
        try {
            Method method = enumClass.getDeclaredMethod("values");
            method.setAccessible(true);
            return (IOptionSetting[]) method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean checkOptions(Class<? extends IOptionSetting> enumClass, String data) {
        if (data == null) {
            return false;
        }

        IOptionSetting[] options = getOptions(enumClass);
        if (options != null) {
            for (IOptionSetting option : options) {
                if (option.isValid() && option.getKey().toLowerCase().equals(data.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }
}
