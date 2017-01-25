package com.github.develop.assistant;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ClipboardContext {

    private Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public void setSystemClipboardTextContent(String refContent){
        String vc = refContent.trim();
        StringSelection ss = new StringSelection(vc);

        systemClipboard.setContents(ss,null);
    }

    public String getSystemClipboardTextContent(){
        Transferable transferable = systemClipboard.getContents("unknown");
        if(transferable != null) {
            try {
                if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return (String) transferable.getTransferData(DataFlavor.stringFlavor);
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
