package com.wgluka.worker;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameterList;

/**
 * Created by yukai on 2017/8/6.
 */
public class ParameterAdder {
    public void addParameter(String text, PsiMethod method, Editor editor) {
        if (method == null || isEmpty(text)) {
            return;
        }

        int targetPos = getTarget(method);
        ParRunnable adder = new ParRunnable(text, targetPos, editor);

        Project project = editor.getProject();
        WriteCommandAction.runWriteCommandAction(project, adder);
    }

    private int getTarget(PsiMethod method) {
        PsiParameterList parameterList = method.getParameterList();
        TextRange parameterRnage = parameterList.getTextRange();

        return parameterRnage.getEndOffset() - 1;
    }

    private boolean isEmpty(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        return false;
    }
}
