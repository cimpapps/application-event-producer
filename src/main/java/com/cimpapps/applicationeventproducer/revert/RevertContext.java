package com.cimpapps.applicationeventproducer.revert;

import org.springframework.util.CollectionUtils;

import java.util.Stack;

public class RevertContext {

    ThreadLocal<Stack<Runnable>> localStackOfMethods = new ThreadLocal<>();

    public void addRevertMethod(Runnable revertMethod) {
        Stack<Runnable> runnables = localStackOfMethods.get();
        if (runnables == null) {
            runnables = new Stack<>();
        }
        runnables.add(revertMethod);
        localStackOfMethods.set(runnables);
    }

    void revert() {
        Stack<Runnable> runnables = localStackOfMethods.get();
        while (!CollectionUtils.isEmpty(runnables)) {
            runnables.pop().run();
        }
        clear();
    }

    private void clear() {
        localStackOfMethods.remove();
    }
}
