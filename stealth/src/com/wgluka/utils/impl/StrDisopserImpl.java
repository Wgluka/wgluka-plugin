package com.wgluka.utils.impl;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiParameterList;
import com.wgluka.utils.StrDisposer;

/**
 * Created by yukai on 2017/8/6.
 */
public class StrDisopserImpl implements StrDisposer {

    public String getString(Editor editor, PsiParameterList parameterList) {
        String text = getOriString(editor);
        if (text == null || text.isEmpty()) {
            return null;
        }

        String parameters = dispose(text, parameterList);
        return parameters;
    }

    private String dispose(String str, PsiParameterList parameterList) {
        String result = str.trim();
        if (result.isEmpty()) {
            return null;
        }

        boolean isNulPar = false;
        if (parameterList.getParametersCount() == 0) {
            isNulPar = true;
        }

        boolean isStart = result.startsWith(",");


        if (!isNulPar && !isStart) {
            result = "," + result;
        }

        if (isNulPar && isStart) {
            result = result.substring(1);
        }

        return result;
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
