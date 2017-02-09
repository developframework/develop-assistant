package com.github.develop.assistant;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FunctionWrapper {

    private List<HotKeyFunction> functions = new ArrayList<>();

    private List<SettingsTab> settingsTabs = new ArrayList<>();
}
