package com.example.selenium.spree.support;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.LogbackException;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class LogbackUtils {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogbackUtils.class);

  public static void initLogback(Level level) {

    // Reroute java.util.Logger to SLF4J
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    ILoggerFactory factory = LoggerFactory.getILoggerFactory();
    if (factory instanceof LoggerContext == false) {
      throw new LogbackException("Expected LOGBACK binding with SLF4J, but another log system has taken the place: " + factory.getClass().getSimpleName());
    }

    LoggerContext context = (LoggerContext)factory;

    PatternLayoutEncoder ple = new PatternLayoutEncoder();
    String pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";
    ple.setPattern(pattern);
    ple.setContext(context);
    ple.start();

    ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
    appender.setName("STDOUT");
    appender.setContext(context);
    appender.setEncoder(ple);

    Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    root.setLevel(level);
    root.addAppender(appender);

    log.info("Using default logback config: {} ({})", level, pattern);
  }

  public static void setLevel(Class<?> clazz, Level level) {
    Logger root = (Logger)LoggerFactory.getLogger(clazz);
    root.setLevel(level);
  }
}
