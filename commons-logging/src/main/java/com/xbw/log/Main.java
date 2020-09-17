package com.xbw.log;

import com.xbw.log.jcl.JCL;
import org.apache.avalon.framework.logger.*;
import org.apache.commons.logging.impl.AvalonLogger;

/**
 * @author xbw
 */
public class Main {
    static {
        // when exist log4j +  System Property
//        System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.Jdk14Logger");

//        System.out.println("logging.configuration = " + Config.jBossLogConfiguration("jboss-logmanager"));

        try {
            org.apache.log4j.BasicConfigurator.configure();
        } catch (NoClassDefFoundError e) {
            // log4j is provided
        }
    }

    /**
     * order by:
     * 0. System Property
     * 1. jboss-logmanager need config
     * 2. log4j2
     * 3. slf4j
     * 4. jboss-logging->log4j
     * 5. logging-jboss
     * 6. log4j
     * 7. jdk
     */
    public static void main(String[] args) {
        JCL.log();

//        avalonConsole();
//        avalonJdk14();
//        avalonLog4J();
//        avalonLogKit();
//        avalonNull();
    }

    /**
     * when commons-logging.properties config
     * org.apache.commons.logging.Log = org.apache.commons.logging.impl.AvalonLogger
     * show Exception in thread "main" java.lang.ExceptionInInitializerError
     */
    private static void avalonConsole() {
        JCL.log(getAvalonLogger(new ConsoleLogger()));
    }

    private static void avalonJdk14() {
        JCL.log(getAvalonLogger(new Jdk14Logger(java.util.logging.Logger.getLogger(JCL.class.getName()))));
    }

    private static void avalonLog4J() {
        try {
            JCL.log(getAvalonLogger(new Log4JLogger(org.apache.log4j.Logger.getLogger(JCL.class))));
        } catch (NoClassDefFoundError e) {
            // log4j is provided
        }
    }

    private static void avalonLogKit() {
        JCL.log(getAvalonLogger(new LogKitLogger(org.apache.log.Hierarchy.getDefaultHierarchy().getLoggerFor(JCL.class.getName()))));
    }

    private static void avalonNull() {
        JCL.log(getAvalonLogger(new NullLogger()));
    }

    private static AvalonLogger getAvalonLogger(org.apache.avalon.framework.logger.Logger logger) {
        return new AvalonLogger(logger);
    }
}
