package com.github.develop.assistant.window.settings;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.utils.DimensionUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * 快捷键设置面板
 */
public class HotKeysSettingsPanel extends JPanel{

    public HotKeysSettingsPanel(Application application) {
        this.setLayout(new GridLayout(1, 1));
        JTable table = new JTable(new HotKeysTableModel(application.hotKeys()));
        table.setRowHeight(DimensionUtils.percentHeight(3));
        TableColumnModel tcm = table.getColumnModel();
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(0).setMaxWidth(DimensionUtils.percentWidth(5));
        tcm.getColumn(1).setMaxWidth(DimensionUtils.percentWidth(10));
        tcm.getColumn(0).setCellRenderer(render);
        tcm.getColumn(1).setCellRenderer(render);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
    }
}
