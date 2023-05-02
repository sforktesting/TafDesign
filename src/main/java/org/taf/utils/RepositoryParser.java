package org.taf.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RepositoryParser {
    private final Properties propertyFile = new Properties();

    private static final Logger LOGGER = LogManager.getLogger();

    public RepositoryParser(String fileName) {

        try (FileInputStream stream = new FileInputStream(fileName)) {
            propertyFile.load(stream);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        LOGGER.info(propertyFile);

    }

    public String getBy(String locatorName) {
        return propertyFile.getProperty(locatorName);
    }
}
