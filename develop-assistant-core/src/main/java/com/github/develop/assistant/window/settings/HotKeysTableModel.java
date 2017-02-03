package com.github.develop.assistant.window.settings;

import com.github.develop.assistant.HotKey;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * 快捷键表格数据模型
 */
public class HotKeysTableModel implements TableModel{


    private static final String[] HEADER = {"ID", "快捷键", "描述"};
    private List<HotKey> hotKeys;

    public HotKeysTableModel(List<HotKey> hotKeys) {
        this.hotKeys = hotKeys;
    }

    @Override
    public int getRowCount() {
        return hotKeys.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return HEADER[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HotKey hotKey = hotKeys.get(rowIndex);
        switch(columnIndex) {
            case 0: return hotKey.getIdentifier();
            case 1: return hotKey.toString();
            case 2: return hotKey.getDescription();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
