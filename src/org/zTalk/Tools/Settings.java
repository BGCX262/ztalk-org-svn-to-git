package org.zTalk.Tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zTalk.protocols.icq.core.OscarConfiguration;

public class Settings {
	private static Log log = LogFactory.getLog(OscarConfiguration.class);
	public static String nameFileSettings = "settings.xml";

	public static boolean SaveSettings(Properties settings) {

		try {
			FileOutputStream out;
			out = new FileOutputStream(nameFileSettings);
			settings.storeToXML(out, "last updated - " + new Date().toString());
			return true;

		} catch (FileNotFoundException ex) {
			log.error(ex.getMessage(), ex);
			return false;
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
			return false;
		}
	}

	public static boolean LoadSettings(Properties settings) {

		try {

			settings.loadFromXML(new FileInputStream(nameFileSettings));
			return true;

		} catch (FileNotFoundException ex) {
			log.error(ex.getMessage(), ex);
			return false;
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
			return false;
		}

	}

}
