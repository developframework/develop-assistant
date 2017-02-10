package com.github.develop.assistant;

import com.github.develop.assistant.function.ExitApplicationFunction;
import com.github.develop.assistant.function.SettingsWindowFunction;
import com.github.develop.assistant.resource.Resource;
import com.github.develop.assistant.resource.ResourceDetector;
import com.github.develop.assistant.resource.ResourceRepository;
import com.github.develop.assistant.window.settings.SettingsWindow;
import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DevelopAssistantApplication implements Application {

    @Getter
    private HotKeyManager hotKeyManager;

    private Tray tray;

    private ResourceRepository resourceRepository;

    private SettingsWindow settingsWindow;

    public DevelopAssistantApplication() {
        ResourceDetector resourceDetector = new ResourceDetector();
        this.resourceRepository = resourceDetector.discovery();
    }

    public static void main(String[] args) {
        DevelopAssistantApplication app = new DevelopAssistantApplication();
        app.start();
    }

    @Override
    public synchronized void start() {
        String path = System.getProperty("user.dir") + File.separator + "function";
        FunctionLoader loader = FunctionLoaderFactory.functionLoader(path);
        hotKeyManager = new HotKeyManager(this);

        List<FunctionHandlers> functionHandlers = loader.load();
        //注册默认的热键
        FunctionHandlers defaultFunctionHandlers = new FunctionHandlers();
        registerDefaultFunctionWrapper(defaultFunctionHandlers);
        functionHandlers.add(defaultFunctionHandlers);

        for (FunctionHandlers functionHandler : functionHandlers) {

            for (HotKeyFunction function : functionHandler.getFunctions()) {
                //注入Application
                if (function instanceof ApplicationAware) {
                    ((ApplicationAware) function).setApplication(this);
                }
                hotKeyManager.registerHotKey(function);
            }

            for(SettingsTab settingsTab : functionHandler.getSettingsTabs()) {
                this.settingsWindow.addTab(settingsTab);
            }
        }


        if (SystemTray.isSupported()) {
            tray = new Tray(this);
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerDefaultFunctionWrapper(FunctionHandlers defaultFunctionHandlers) {
        SettingsWindowFunction settingsWindowFunction = new SettingsWindowFunction();
        this.settingsWindow = settingsWindowFunction.getSettingsWindow();
        defaultFunctionHandlers.getFunctions().add(settingsWindowFunction);
        defaultFunctionHandlers.getFunctions().add(new ExitApplicationFunction());
    }

    @Override
    public synchronized void destroy() {
        hotKeyManager.removeAllHotKeys();
        tray.destroy();
        System.exit(0);
    }

    @Override
    public List<HotKey> hotKeys() {
        return hotKeyManager.getHotKeys();
    }

    @Override
    public List<HotKeyFunction> hotKeyFunctions() {
        return new ArrayList<>(hotKeyManager.getMultipleHotKeyListener().getHotKeyFunctions().values());
    }

    @Override
    public PopupMenu trayPopupMenu() {
        return tray.getPopupMenu();
    }

    @Override
    public Resource getResource(String name) {
        return resourceRepository.get(name);
    }

    @Override
    public Resource putResource(String name, Resource resource) {
        return resourceRepository.put(name, resource);
    }
}
