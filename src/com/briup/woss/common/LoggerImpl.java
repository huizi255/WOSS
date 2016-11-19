package com.briup.woss.common;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class LoggerImpl implements Logger{
	private org.apache.log4j.Logger logger;
	private String log_properties;
	public LoggerImpl() {
		
	}

	@Override
	public void init(Properties properties) {
		log_properties = properties.getProperty("log-properties");
	}

	@Override
	public void debug(String msg) {
		PropertyConfigurator.configure(log_properties);
		logger = org.apache.log4j.Logger.getRootLogger();
		logger.debug(msg);
		
	}

	@Override
	public void error(String msg) {
		PropertyConfigurator.configure(log_properties);
		logger = org.apache.log4j.Logger.getRootLogger();
		logger.error(msg);
	}

	@Override
	public void fatal(String msg) {
		PropertyConfigurator.configure(log_properties);
		logger = org.apache.log4j.Logger.getRootLogger();
		logger.fatal(msg);
	}

	@Override
	public void info(String msg) {
		PropertyConfigurator.configure(log_properties);
		logger = org.apache.log4j.Logger.getRootLogger();
		logger.info(msg);
	}

	@Override
	public void warn(String msg) {
		PropertyConfigurator.configure(log_properties);
		logger = org.apache.log4j.Logger.getRootLogger();
		logger.warn(msg);
	}

}
