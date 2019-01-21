package com.example.samplewords.dbg;

import android.util.Log;

import com.example.samplewords.BuildConfig;
import com.example.samplewords.util.function.BiConsumer;

public class Dbg {
    public static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String EXCEPTION_TAG = "EXCEPTION";
    private static final String TAG_PREFIX = "samplewordslog";
    private static final String TMP_TAG = "mytestlog";

    public static void v(String tag, String msg, Object... args) {
        log(Log::v, tag, msg, args);
    }

    public static void d(String tag, String msg, Object... args) {
        log(Log::d, tag, msg, args);
    }

    public static void i(String tag, String msg, Object... args) {
        log(Log::i, tag, msg, args);
    }

    public static void w(String tag, String msg, Object... args) {
        log(Log::w, tag, msg, args);
    }

    public static void exception(Throwable throwable) {
        e(EXCEPTION_TAG, Log.getStackTraceString(throwable));
    }

    public static void e(String tag, String msg, Object... args) {
        log(Log::e, tag, msg, args);
    }

    public static void tmp(String msg, Object... args) {
        log(Log::d, TMP_TAG, msg, args);
    }

    private static void log(BiConsumer<String, String> logger, String tag,
                            String msg, Object... args) {
        String resMsg = String.format(msg, args);
        String resTag = String.format("%s.%s", TAG_PREFIX, tag);
        logger.accept(resTag, resMsg);
    }

}
