package edu.softserveinc.healthbody.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Log4jWrapper implements ILogger {
	private final String PICTURE_PATH = "Path to Picture ";
	private Logger logger;

	public static Logger get() {
	    return LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName());
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

	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	@Override
	public void insertScreenShot(final String fileNamePath) {
		logger.error(PICTURE_PATH + fileNamePath);
	}

}