package com.brainbeanapps.core.log;

import timber.log.Timber;

public abstract class BaseTree extends Timber.DebugTree {
    private final int logLevel;

    public BaseTree(int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    protected boolean isLoggable(int priority) {
        return priority >= logLevel;
    }
}
