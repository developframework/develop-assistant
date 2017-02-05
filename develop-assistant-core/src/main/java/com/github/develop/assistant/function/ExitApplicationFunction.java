package com.github.develop.assistant.function;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.ApplicationAware;
import com.github.develop.assistant.HotKey;
import com.github.develop.assistant.HotKeyFunction;
import com.melloware.jintellitype.JIntellitype;

import java.awt.*;

/**
 * 退出程序热键
 */
public class ExitApplicationFunction implements HotKeyFunction, ApplicationAware {

    private Application application;

    @Override
    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public HotKey hotKey(int identifier) {
        return new HotKey(identifier, JIntellitype.MOD_ALT, 'X', "退出应用");
    }

    @Override
    public void event() {
        application.destroy();
    }

    @Override
    public MenuItem createMenuItem() {
        MenuItem exit = new MenuItem("退出应用");
        exit.addActionListener(event -> application.destroy());
        return exit;
    }

}
