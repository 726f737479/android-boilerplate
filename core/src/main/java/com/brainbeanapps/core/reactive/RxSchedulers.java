package com.brainbeanapps.core.reactive;

import rx.Scheduler;

public interface RxSchedulers {

    Scheduler db();

    Scheduler main();

    Scheduler computation();

    Scheduler io();
}
