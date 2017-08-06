package com.wgluka.utils;

import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiParameterList;

/**
 * Created by yukai on 2017/8/6.
 */
public interface StrDisposer {
    String getString(Editor editor, PsiParameterList parameterList);

    String getOriString(Editor editor);
}
