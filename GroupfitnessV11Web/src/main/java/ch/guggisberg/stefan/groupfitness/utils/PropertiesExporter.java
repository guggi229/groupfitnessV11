/*
 * Copyright 2012 - 2017 by PostFinance Ltd - All rights reserved
 */
package ch.guggisberg.stefan.groupfitness.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;


/**
 * Properties für die ganze APP

 *
 */
public final class PropertiesExporter {

	private static final String CONFIG_FILE = "app.properties";
	private static final PropertiesConfiguration CONFIG;
	private static final PropertiesExporter INSTANCE = new PropertiesExporter();

	// Properties
	private static final String PROPERTY_EMAIL_SENDER = "emailsender";
	private static final String PROPERTY_IMAGE_PATH_COURS = "image.path.cours";
	private static final String PROPERTY_IMAGE_PATH_AVATAR = "image.path.avatar";
	private static final String PROPERTY_IMAGE_SIZE_COURS = "image.size.cours";
	private static final String PROPERTY_IMAGE_SIZE_AVATAR = "image.size.avatar";
	private static final String PROPERTY_IMAGE_PATH_EMPTY_AVATAR = "image.path.empty.avatar";

	static {
		CONFIG = new PropertiesConfiguration();
		CONFIG.setListDelimiter('|');
		CONFIG.setFileName(CONFIG_FILE);
		CONFIG.setReloadingStrategy(new FileChangedReloadingStrategy());
		try {
			CONFIG.load();
		} catch (ConfigurationException ce) {
			throw new ExceptionInInitializerError(ce);
		}
	}

	private PropertiesExporter() {
		// sollte nicht instanziert werden
	}

	public static PropertiesExporter getInstance() {
		return INSTANCE;
	}

	public static String getPropertyEmailSender() {
		return CONFIG.getString(PROPERTY_EMAIL_SENDER);
	}

	public static String getPropertyImagePathCours() {
		return CONFIG.getString(PROPERTY_IMAGE_PATH_COURS);
	}

	public static String getPropertyImagePathAvatar() {
		return CONFIG.getString(PROPERTY_IMAGE_PATH_AVATAR);
	}

	public static String getPropertyImagePathEmptyAvatar() {
		return CONFIG.getString(PROPERTY_IMAGE_PATH_EMPTY_AVATAR);
	}

	public static Integer getPropertyImageSizeCours() {
		return Integer.parseInt(CONFIG.getString(PROPERTY_IMAGE_SIZE_COURS));
	}

	public static Integer getPropertyImageSizeAvatar() {
		return Integer.parseInt(CONFIG.getString(PROPERTY_IMAGE_SIZE_AVATAR));
	}
	
	


}
