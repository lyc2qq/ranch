package org.lpw.ranch.async;

import java.util.concurrent.Callable;

/**
 * @author lpw
 */
public interface Callabler extends Callable<String> {
    /**
     * 设置执行实例。
     *
     * @param callable 执行实例。
     * @return 当前实例。
     */
    Callabler set(Callable<String> callable);
}
