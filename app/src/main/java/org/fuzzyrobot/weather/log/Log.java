package org.fuzzyrobot.weather.log;


import org.fuzzyrobot.weather.BuildConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Logging that adds class & method name automatically, with no need for a TAG
 */
public class Log {
    public static final boolean LOG_ENABLED = BuildConfig.DEBUG;

    protected static final List<String> CLASSNAMES_TO_IGNORE = new ArrayList<String>();

    static {
        CLASSNAMES_TO_IGNORE.add("java.lang.Thread");
        CLASSNAMES_TO_IGNORE.add("dalvik.system.VMStack");
        CLASSNAMES_TO_IGNORE.add(Log.class.getName());
    }

    public static void enter() {
        if (LOG_ENABLED) d("{");
    }

    public static void exit() {
        if (LOG_ENABLED) d("}");
    }

    public static void enter(Object o) {
        if (LOG_ENABLED) d(o, "{");
    }

    public static void exit(Object o) {
        if (LOG_ENABLED) d(o, "}");
    }

    public static void d() {
        if (LOG_ENABLED) d("-");
    }

    public static void d(Object obj) {
        if (LOG_ENABLED) d(String.valueOf(obj));
    }

    public static void d(Object msg, Object obj) {
        if (LOG_ENABLED) d(msg + " : " + obj);
    }

    public static void d(Object msg, Object obj, Object o2) {
        if (LOG_ENABLED) d(msg + " : " + obj + " : " + o2);
    }

    public static void d(Object msg, Object obj, Object o2, Object o3) {
        if (LOG_ENABLED) d(msg + " : " + obj + " : " + o2 + " : " + o3);
    }

    public static void d(Object msg, Object obj, Object o2, Object o3, Object o4) {
        if (LOG_ENABLED) d(msg + " : " + obj + " : " + o2 + " : " + o3 + " : " + o4);
    }

    public static void d(Object msg, Object obj, Object o2, Object o3, Object o4, Object o5) {
        if (LOG_ENABLED) d(msg + " : " + obj + " : " + o2 + " : " + o3 + " : " + o4 + " : " + o5);
    }

    public static void d(Object msg, Object obj, Object o2, Object o3, Object o4, Object o5, Object o6) {
        if (LOG_ENABLED) d(msg + " : " + obj + " : " + o2 + " : " + o3 + " : " + o4 + " : " + o5 + " ; " + o6);
    }

    public static int d(String msg) {
        if (!LOG_ENABLED) {
            return 0;
        }
        String caller = getCaller();
        if (caller != null) {
            return d(caller, msg);
        }
        return 0;
    }

    public static int e(Object object, String msg) {
        if (object != null) {
            return e(object.getClass().getName(), msg);
        }
        return 0;
    }

    public static int e(String msg) {
        String caller = getCaller();
        if (caller != null) {
            return e(caller, msg);
        }
        return 0;
    }

    public static void e(Object obj) {
        e(String.valueOf(obj));
    }


    public static String getCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null) {
            for (final StackTraceElement stack : stackTrace) {
                String className = stack.getClassName();
                if (className != null && !CLASSNAMES_TO_IGNORE.contains(className)) {
                    String simpleName = className.substring(className.lastIndexOf('.') + 1);
                    return simpleName + "." + stack.getMethodName();
                }
            }
        }
        return null;
    }

    public static int d(String tag, String msg) {
        if (!LOG_ENABLED) {
            return 0;
        }
        android.util.Log.d("dm." + tag, String.valueOf(msg));
        return 0;
    }

    public static int e(String tag, String msg) {
        if (!LOG_ENABLED) {
            return 0;
        }
        android.util.Log.e("dm." + tag, msg);
        return 0;
    }

}
