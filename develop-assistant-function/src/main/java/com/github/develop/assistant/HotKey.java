package com.github.develop.assistant;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 热键
 */
@Data
@AllArgsConstructor
public class HotKey {

    private int identifier;

    private int modifier;

    private char keycode;

    private String description;
}
