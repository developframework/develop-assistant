package com.github.develop.assistant.window.settings;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.BaseWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * 设置面板
 */
public class SettingsWindow extends BaseWindow {

    public SettingsWindow(Application application) {
        super(application, "Settings", 50, 50, true);

        JTable table = new JTable(new HotKeysTableModel(application.hotKeys()));
        table.setRowHeight(percentHeight(3));
        TableColumnModel tcm = table.getColumnModel();
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(0).setMaxWidth(percentWidth(5));
        tcm.getColumn(1).setMaxWidth(percentWidth(10));
        tcm.getColumn(0).setCellRenderer(render);
        tcm.getColumn(1).setCellRenderer(render);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setContentPane(scrollPane);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
