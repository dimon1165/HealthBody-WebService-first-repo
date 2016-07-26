package edu.softserveinc.healthbody.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Log4jWrapper implements ILogger {
	private static volatile Log4jWrapper instance;
	private final String PICTURE_PATH = "Path to Picture ";
	private Logger logger;

	// private Log4jWrapper() {
	// this.logger = LoggerFactory.getLogger(Log4jWrapper.class);
	// }
	//
	// public static Log4jWrapper get() {
	// if (instance == null) {
	// synchronized (Log4jWrapper.class) {
	// if (instance == null) {
	// instance = new Log4jWrapper();
	// }
	// }
	// }
	// return instance;
	// }

	public Log4jWrapper(Logger logger) {
		this.logger = logger;
	}
	
	public static Logger get() {
	    final Throwable t = new Throwable();
	    final StackTraceElement methodCaller = t.getStackTrace()[1];
	    final Logger logger = LoggerFactory.getLogger(methodCaller.getClassName());
	    return logger;
	}

	@Override
	public void error(final String message) {
		logger.error(message);
	}

	@Override
	public void warning(final String message) {
		logger.warn(message);
	}

	@Override
	public void info(final String message) {
		logger.info(message);
	}

	// public void debug(String message) {
	// logger.debug(message);
	// }

	@Override
	public void insertScreenShot(final String fileNamePath) {
		logger.error(PICTURE_PATH + fileNamePath);
	}

}