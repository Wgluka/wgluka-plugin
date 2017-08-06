package com.wgluka.utils.impl;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.wgluka.utils.StrDisposer;

/**
 * Created by yukai on 2017/8/6.
 */
public class StrDisopserImpl implements StrDisposer {

    public String getString(Editor editor) {
        String text = getOriString(editor);
        if (text == null || text.isEmpty()) {
            return null;
        }

        String parameters = dispose(text);
        return parameters;
    }

    private String dispose(String str) {
        return str;
    }

    public String getOriString(Editor editor) {
        CaretModel currentPos = editor.getCaretModel();

        int startOffset = currentPos.getVisualLineStart();
        int endOffset = currentPos.getVisualLineEnd();
        TextRange textRange = new TextRange(startOffset, endOffset);

        Document document = editor.getDocument();
        return document.getText(textRange);
    }
}
