package com.github.develop.assistant;

import com.github.develop.assistant.ClipboardContext;
import com.github.develop.assistant.HotKeyFunction;

/**
 * Created by Administrator on 2017/1/24.
 */
public abstract class VariableBaseFunction implements HotKeyFunction {


    @Override
    public void event(ClipboardContext clipboardContext) {
        String text = clipboardContext.getSystemClipboardTextContent();
        if(text != null && !text.isEmpty()) {
            String newText = dealText(text);
            clipboardContext.setSystemClipboardTextContent(newText);
        }
    }

    protected abstract String dealText(String variable);
}
