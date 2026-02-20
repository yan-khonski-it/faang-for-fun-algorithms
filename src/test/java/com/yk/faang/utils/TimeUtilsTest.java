package com.yk.faang.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class TimeUtilsTest {

    @Test
    void testWithTimerMsRunnable() {
        AtomicBoolean ran = new AtomicBoolean(false);
        TimeUtils.withTimerMs(() -> ran.set(true), "test runnable");
        assertThat(ran.get()).isTrue();
    }

    @Test
    void testWithTimerMsCallable() {
        String result = TimeUtils.withTimerMs(() -> "success", "test callable");
        assertThat(result).isEqualTo("success");
    }

    @Test
    void testWithTimerNsRunnable() {
        AtomicBoolean ran = new AtomicBoolean(false);
        TimeUtils.withTimerNs(() -> ran.set(true), "test runnable ns");
        assertThat(ran.get()).isTrue();
    }

    @Test
    void testWithTimerNsCallable() {
        String result = TimeUtils.withTimerNs(() -> "success", "test callable ns");
        assertThat(result).isEqualTo("success");
    }

    @Test
    void testWithTimerMsRunnableException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            TimeUtils.withTimerMs((Runnable) () -> {
                throw new RuntimeException("inner error");
            }, "exception test")
        );
        assertThat(exception).hasMessageContaining("Failed to run runnable");
        assertThat(exception).hasRootCauseMessage("inner error");
    }

    @Test
    void testWithTimerMsCallableException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            TimeUtils.withTimerMs((Callable<String>) () -> {
                throw new Exception("inner error");
            }, "exception test")
        );
        assertThat(exception).hasMessageContaining("Failed to call callable");
        assertThat(exception).hasRootCauseMessage("inner error");
    }
}
