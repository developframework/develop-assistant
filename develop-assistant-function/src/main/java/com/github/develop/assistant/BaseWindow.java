package com.github.develop.assistant;

import javax.swing.*;
import java.awt.*;

/**
 * 基窗口
 */
public class BaseWindow extends JFrame{

    protected Application application;
    protected static int screenWidth;
    protected static int screenHeight;

    static {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
    }

    public BaseWindow(Application application, String title, int width, int height, boolean isAlignCenter) {
        this.application = application;
        this.setTitle(title);
        this.setSize(percentWidth(width), percentHeight(height));
        if (isAlignCenter) {
            //设置窗体屏幕居中
            this.setLocation(screenWidth / 2 - this.getWidth() / 2, screenHeight / 2 - this.getHeight() / 2);
        }
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * 宽百分值
     * @param value
     * @return
     */
    protected int percentWidth(int value) {
        return value * screenWidth / 100;
    }

    /**
     * 高百分值
     * @param value
     * @return
     */
    protected int percentHeight(int value) {
        return value * screenHeight / 100;
    }

    /**
     * 百分尺寸
     * @param width
     * @param height
     * @return
     */
    protected Dimension percentDimension(int width, int height) {
        return new Dimension(percentWidth(width), percentHeight(height));
    }
}
