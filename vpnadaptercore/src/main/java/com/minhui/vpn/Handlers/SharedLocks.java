package com.minhui.vpn.Handlers;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedLocks {
    public static final ReadWriteLock playerHandlerLock = new ReentrantReadWriteLock();
    public static final ReadWriteLock localPlayerLock = new ReentrantReadWriteLock();

    public static final ReadWriteLock mobsHandlerLock = new ReentrantReadWriteLock();

    public static final ReadWriteLock harvestablesHandlerLock = new ReentrantReadWriteLock();
    public static final ReadWriteLock chestsHandlerLock = new ReentrantReadWriteLock();
}
