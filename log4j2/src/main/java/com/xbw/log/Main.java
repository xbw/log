package com.xbw.log;

import com.xbw.log.jboss.JLog;
import com.xbw.log.jboss.JLogging;
import com.xbw.log.jcl.JCL;
import com.xbw.log.jul.JUL;
import com.xbw.log.log4j.Log4j;
import com.xbw.log.log4j2.Log4j2;
import com.xbw.log.slf4j.Slf4j;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {
    static {
//        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");

        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        SLF4JBridgeHandler.install();
    }

    public static void main(String[] args) {
        JCL.log();
        JUL.log();
        // jul use jul-to-slf4j,and jboss log --(jboss-logbridge)--> log4j --log4j-1.2-api--> log4j2
        JLog.log();
        JLogging.log();
        Log4j.log();
        Log4j2.log();
        Slf4j.log();
    }
}
