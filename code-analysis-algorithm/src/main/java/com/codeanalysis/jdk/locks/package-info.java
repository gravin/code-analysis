/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

/**
 * Interfaces and classes providing a framework for locking and waiting
 * for conditions that is distinct from built-in synchronization and
 * monitors.  The framework permits much greater flexibility in the use of
 * locks and conditions, at the expense of more awkward syntax.
 *
 * <p>The {@link com.codeanalysis.jdk.locks.Lock} interface supports
 * locking disciplines that differ in semantics (reentrant, fair, etc),
 * and that can be used in non-block-structured contexts including
 * hand-over-hand and lock reordering algorithms.  The main implementation
 * is {@link com.codeanalysis.jdk.locks.ReentrantLock}.
 *
 * <p>The {@link com.codeanalysis.jdk.locks.ReadWriteLock} interface
 * similarly defines locks that may be shared among readers but are
 * exclusive to writers.  Only a single implementation, {@link
 * com.codeanalysis.jdk.locks.ReentrantReadWriteLock}, is provided, since
 * it covers most standard usage contexts.  But programmers may create
 * their own implementations to cover nonstandard requirements.
 *
 * <p>The {@link com.codeanalysis.jdk.locks.Condition} interface
 * describes condition variables that may be associated with Locks.
 * These are similar in usage to the implicit monitors accessed using
 * {@code Object.wait}, but offer extended capabilities.
 * In particular, multiple {@code Condition} objects may be associated
 * with a single {@code Lock}.  To avoid compatibility issues, the
 * names of {@code Condition} methods are different from the
 * corresponding {@code Object} versions.
 *
 * <p>The {@link com.codeanalysis.jdk.locks.AbstractQueuedSynchronizer}
 * class serves as a useful superclass for defining locks and other
 * synchronizers that rely on queuing blocked threads.  The {@link
 * com.codeanalysis.jdk.locks.AbstractQueuedLongSynchronizer} class
 * provides the same functionality but extends support to 64 bits of
 * synchronization state.  Both extend class {@link
 * com.codeanalysis.jdk.locks.AbstractOwnableSynchronizer}, a simple
 * class that helps record the thread currently holding exclusive
 * synchronization.  The {@link com.codeanalysis.jdk.locks.LockSupport}
 * class provides lower-level blocking and unblocking support that is
 * useful for those developers implementing their own customized lock
 * classes.
 *
 * @since 1.5
 */
package com.codeanalysis.jdk.locks;
