package com.wgluka.worker;


import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;

/**
 * Created by yukai on 2017/8/6.
 */
public class ParRunnable implements Runnable {

    private String text;
    private Editor editor;
    private int pos;

    public ParRunnable(String text, int pos, Editor editor) {
        this.text = text;
        this.pos = pos;
        this.editor = editor;
    }

    @Override
    public void run() {
        Document document = editor.getDocument();
        document.insertString(pos, text);

        CaretModel model = editor.getCaretModel();
        int start = model.getVisualLineStart();
        int end = model.getVisualLineEnd();
        document.deleteString(start, end);
    }
}
