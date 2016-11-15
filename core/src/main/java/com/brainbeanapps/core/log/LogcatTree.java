package com.brainbeanapps.core.log;

import android.util.Log;

public final class LogcatTree extends BaseTree {

    public LogcatTree(int logLevel) {
        super(logLevel);
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        switch (priority) {

            case Log.VERBOSE:
                Log.v(tag, message, t);
                break;

            case Log.DEBUG:
                Log.d(tag, message, t);
                break;

            case Log.INFO:
                Log.i(tag, message, t);
                break;

            case Log.WARN:
                Log.w(tag, message, t);
                break;

            case Log.ERROR:
                Log.e(tag, message, t);
                break;
        }
    }
}
