package com.wgluka.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.wgluka.utils.StrDisposer;
import com.wgluka.utils.impl.StrDisopserImpl;
import com.wgluka.worker.ParameterAdder;

/**
 * Created by yukai on 2017/8/6.
 */
public class StealthAction extends AnAction {

    private StrDisposer disposer;
    private ParameterAdder adder;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        Project project = e.getProject();
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        if (project == null || editor == null) {
            return;
        }

        addParameter(editor, psiFile);

    }

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        boolean visible = project != null && editor != null;
        e.getPresentation().setVisible(visible);
    }

    private void addParameter(Editor editor, PsiFile psiFile) {
        init();

        String parameters = disposer.getString(editor);
        if (parameters == null || parameters.isEmpty()) {
            return;
        }

        PsiMethod method = getCurrentMethod(psiFile, editor);
        if (method == null) {
            return;
        }

        adder.addParameter(parameters, method, editor);
    }

    private synchronized void init() {
        if (disposer == null) {
            disposer = new StrDisopserImpl();
        }
        if (adder == null) {
            adder = new ParameterAdder();
        }
    }

    private PsiMethod getCurrentMethod(PsiFile psiFile, Editor editor) {
        if (psiFile == null || editor == null) {
            return null;
        }

        CaretModel currentPos = editor.getCaretModel();
        int offset = currentPos.getOffset();
        PsiElement element = psiFile.findElementAt(offset);
        return PsiTreeUtil.getParentOfType(element, PsiMethod.class);
    }
}
