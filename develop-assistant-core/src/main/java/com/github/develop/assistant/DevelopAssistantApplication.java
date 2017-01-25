package com.github.develop.assistant;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class DevelopAssistantApplication {

    private HotKeyManager hotKeyManager;

    public static void main(String[] args) {
        DevelopAssistantApplication app = new DevelopAssistantApplication();
        app.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        String path = System.getProperty("user.dir") + File.separator + "function";
        FunctionLoader loader = FunctionLoaderFactory.functionLoader(path);
        hotKeyManager = new HotKeyManager();
        Set<HotKeyFunction> set = loader.load();
        for(HotKeyFunction function : set) {
            hotKeyManager.addHotKey(function);
        }
        hotKeyManager.register();
        if (SystemTray.isSupported()) {
            new Tray(hotKeyManager);
        }
    }
}
