package com.jpyl.music.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 16/4/11.
 */
public class LogUtils {

    static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void debug(String msg)
    {
        logger.info(msg);
    }
    public static void log(String msg)
    {
        //System.out.println(msg);

    }
}

