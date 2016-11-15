package com.brainbeanapps.core.reactive;

import android.os.Process;

import com.brainbeanapps.core.reactive.RxSchedulers;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rosty on 10/18/2016.
 */

public class BoilerplateRxSchedulers implements RxSchedulers {

    private static final String THREAD_NAME          = "db-thread-pool";
    private static final long   DB_THREAD_KEEP_ALIVE = 10L;
    private static final int    DB_CORE_POOL_SIZE    = 1;
    private static final int    DB_MAX_POOL_SIZE     = 1;

    private final Scheduler db;

    @Inject public BoilerplateRxSchedulers() {

        ThreadPoolExecutor dbExecutor = new ThreadPoolExecutor(
                DB_CORE_POOL_SIZE, DB_MAX_POOL_SIZE, DB_THREAD_KEEP_ALIVE, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new PriorityThreadFactory(THREAD_NAME, Process.THREAD_PRIORITY_BACKGROUND));

        dbExecutor.allowCoreThreadTimeOut(true);
        db = Schedulers.from(dbExecutor);
    }

    /**
     * @return Scheduler that have to bes used for DB transaction
     */
    @Override public Scheduler db() {
        return db;
    }

    /**
     * @return Scheduler that have to bes used to interact with UI components
     */
    @Override public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    /**
     * @return Scheduler that can be event-loops, processing callbacks
     *          and other computational work.
     */
    @Override public Scheduler computation() {
        return Schedulers.computation();
    }

    /**
     * @return Scheduler can be used for asynchronously performing blocking IO.
     */
    @Override public Scheduler io() {
        return Schedulers.io();
    }

    class PriorityThreadFactory implements ThreadFactory {

        private final AtomicInteger number = new AtomicInteger();

        private final String name;
        private final int    threadPriority;

        public PriorityThreadFactory(String name, int threadPriority) {
            this.name = name;
            this.threadPriority = threadPriority;
        }

        @Override public Thread newThread(Runnable r) {

            String name = this.name
                    .concat("-")
                    .concat(String.valueOf(number.getAndIncrement()));

            return new Thread(r, name) {

                @Override public void run() {
                    Process.setThreadPriority(threadPriority);
                    super.run();
                }
            };
        }
    }
}
