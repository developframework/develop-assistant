package com.github.develop.assistant;

import com.github.develop.assistant.utils.DimensionUtils;

import javax.swing.*;

/**
 * 基窗口
 */
public class BaseWindow extends JFrame{

    protected Application application;

    public BaseWindow(Application application, String title, int width, int height, boolean isAlignCenter) {
        this.application = application;
        this.setTitle(title);
        this.setSize(DimensionUtils.percentWidth(width), DimensionUtils.percentHeight(height));
        if (isAlignCenter) {
            //设置窗体屏幕居中
            this.setLocation(DimensionUtils.screenWidth / 2 - this.getWidth() / 2, DimensionUtils.screenHeight / 2 - this.getHeight() / 2);
        }
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
