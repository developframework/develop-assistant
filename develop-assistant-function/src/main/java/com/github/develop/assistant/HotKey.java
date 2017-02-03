package com.github.develop.assistant;

import com.melloware.jintellitype.JIntellitype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 热键
 */
@Getter
@Setter
@AllArgsConstructor
public class HotKey {

    private int identifier;

    private int modifier;

    private char keycode;

    private String description;

    @Override
    public String toString() {
        switch(modifier) {
            case JIntellitype.MOD_ALT : return "ALT + " + keycode;
            case JIntellitype.MOD_CONTROL : return "CTRL + " + keycode;
            case JIntellitype.MOD_SHIFT : return "SHIFT + " + keycode;
            case JIntellitype.MOD_WIN : return "WIN + " + keycode;
        }
        return null;
    }
}
