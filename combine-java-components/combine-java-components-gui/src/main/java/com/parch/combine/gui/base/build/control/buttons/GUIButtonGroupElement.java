package com.parch.combine.gui.base.build.control.buttons;

import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.style.ElementConfig;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIButtonGroupElement extends AbstractGUIComponentElement<GUIButtonGroupElementTemplate, GUIButtonGroupElement.Config, GUIButtonGroupElement.ItemConfig[]> {

    private JPanel buttonGroup = null;
    private Map<String,EventConfig > eventMap;

    private static String TEXT_FILE_NAME = "text";
    private static String EVENT_KEYS_FILE_NAME = "eventKeys";

    public GUIButtonGroupElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIButtonGroupElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "buttons", template, config, GUIButtonGroupElementTemplate.class);

        if (config.events != null) {
            eventMap = new HashMap<>(16);
            for (EventConfig event : config.events) {
                eventMap.put(event.getEventKey(), event);
            }
        }
    }

    @Override
    public JComponent build() {
        this.buttonGroup = new JPanel();
        super.loadTemplates(buttonGroup, this.template.getExternal());
        this.buildButtons();
        return buttonGroup;
    }

    public void buildButtons() {
        if (this.value == null || this.buttonGroup == null) {
            return;
        }

        for (ItemConfig itemConfig : this.value) {
            JButton button = new JButton();
            button.setText(itemConfig.value);

            if (this.eventMap != null && itemConfig.eventKeys != null && itemConfig.eventKeys.length > 0) {
                EventConfig[] events = new EventConfig[itemConfig.eventKeys.length];
                for (int i = 0; i < itemConfig.eventKeys.length; i++) {
                    events[i] = this.eventMap.get(itemConfig.eventKeys[i]);
                }
                super.registerEvents(button, events);
            }

            super.addSubComponent(this.buttonGroup, button, this.template.getButton());
            super.loadFancyTemplates(button, itemConfig.type, this.template.getButtonTypes());
        }
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        this.value = null;
        if (data instanceof Collection) {
            List<ItemConfig> configList = new ArrayList<>();
            for (Object dataItem : (Collection<?>) data) {
                ItemConfig itemConfig = buildValueItem(dataItem);
                if (itemConfig != null) {
                    configList.add(itemConfig);
                }
            }
            if (configList.size() > 0) {
                this.value = configList.toArray(new ItemConfig[0]);
            }
        } else {
            ItemConfig itemConfig = buildValueItem(data);
            if (itemConfig != null) {
                this.value = new ItemConfig[]{buildValueItem(data)};
            }
        }

        this.buttonGroup.removeAll();
        this.buildButtons();
        return true;
    }

    @SuppressWarnings("unchecked")
    private ItemConfig buildValueItem(Object data) {
        if (data == null) {
            return null;
        }

        if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            ItemConfig itemConfig = new ItemConfig();

            String text = (String) mapData.get(TEXT_FILE_NAME);
            itemConfig.value = text == null ? this.config.defaultText : text;

            Object eventKeys = mapData.get(EVENT_KEYS_FILE_NAME);
            if (eventKeys != null) {
                if (data instanceof Collection) {
                    List<String> eventKeyList = new ArrayList<>();
                    for (Object dataItem : (Collection<?>) data) {
                        if (dataItem != null) {
                            eventKeyList.add(dataItem.toString());
                        }
                    }
                    itemConfig.eventKeys = eventKeyList.toArray(new String[0]);
                } else {
                    itemConfig.eventKeys = new String[]{eventKeys.toString()};
                }
            }

            return itemConfig;
        } else {
            ItemConfig itemConfig = new ItemConfig();
            itemConfig.value = data.toString();
            return itemConfig;
        }
    }

    @Override
    public Object getValue() {
        List<Map<String, Object>> result = new ArrayList<>();
        if (this.value != null) {
            for (ItemConfig item : this.value) {
                Map<String, Object> resultItem = new HashMap<>(8);
                resultItem.put(TEXT_FILE_NAME, item.value);
                resultItem.put(EVENT_KEYS_FILE_NAME, Arrays.asList(item.eventKeys));
                result.add(resultItem);
            }
        }

        return result;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIButtonGroupElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<ItemConfig[]> {
        public String defaultText;
        public EventConfig[] events;
    }

    public static class ItemConfig {
        public String value;
        public String type;
        public String[] eventKeys;
    }
}
