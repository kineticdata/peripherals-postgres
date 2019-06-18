package com.kineticdata.bridgehub.adapter.sql.postgres;

import com.kineticdata.bridgehub.adapter.BridgeError;
import com.kineticdata.bridgehub.adapter.sql.SqlAdapter;
import com.kineticdata.commons.v1.config.ConfigurableProperty;
import com.kineticdata.commons.v1.config.ConfigurablePropertyMap;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

public class PostgresAdapter extends SqlAdapter {
    /*----------------------------------------------------------------------------------------------
     * PROPERTIES
     *--------------------------------------------------------------------------------------------*/
    
    /** Defines the adapter display name. */
    public static final String NAME = "Postgres Bridge";
    
    /** Specify the adapter class and ensure it is loaded **/
    public static final String ADAPTER_CLASS = "org.postgresql.Driver";

    /** Defines the logger */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PostgresAdapter.class);

    /** Adapter version constant. */
    public static String VERSION;
    /** Load the properties version from the version.properties file. */
    static {
        try {
            java.util.Properties properties = new java.util.Properties();
            properties.load(PostgresAdapter.class.getResourceAsStream("/"+PostgresAdapter.class.getName()+".version"));
            VERSION = properties.getProperty("version");
        } catch (IOException e) {
            logger.warn("Unable to load "+PostgresAdapter.class.getName()+" version properties.", e);
            VERSION = "Unknown";
        }
    }

    /** Defines the collection of property names for the adapter. */
    public static class Properties {
        public static final String USERNAME = "Username";
        public static final String PASSWORD = "Password";
        public static final String SERVER = "Server";
        public static final String PORT = "Port";
        public static final String DATABASE_NAME = "Database Name";
        public static final String APPEND_CONNECTION = "Additional JDBC string";
    }

    private final ConfigurablePropertyMap properties = new ConfigurablePropertyMap(
            new ConfigurableProperty(Properties.USERNAME).setIsRequired(true),
            new ConfigurableProperty(Properties.PASSWORD).setIsRequired(true).setIsSensitive(true),
            new ConfigurableProperty(Properties.SERVER).setIsRequired(true).setValue("127.0.0.1"),
            new ConfigurableProperty(Properties.PORT).setIsRequired(true).setValue("5432"),
            new ConfigurableProperty(Properties.DATABASE_NAME).setIsRequired(true),
            new ConfigurableProperty(Properties.APPEND_CONNECTION).setIsRequired(false)
    );

    /*---------------------------------------------------------------------------------------------
     * SETUP METHODS
     *-------------------------------------------------------------------------------------------*/
    @Override
    public String getName() {
        return NAME;
    }
    
    @Override
    public String getVersion() {
       return  VERSION;
    }
    
    @Override
    public ConfigurablePropertyMap getProperties() {
        return properties;
    }
    
    @Override
    public void setProperties(Map<String,String> parameters) {
        properties.setValues(parameters);
    }

    @Override
    public void initialize() throws BridgeError {
        super.initalize (
            ADAPTER_CLASS,
            "jdbc:postgresql://"+
                properties.getValue(Properties.SERVER)+":"+
                properties.getValue(Properties.PORT)+"/"+
                properties.getValue(Properties.DATABASE_NAME)+
                (StringUtils.isEmpty(properties.getValue(Properties.APPEND_CONNECTION)) ? 
                    "" : "?" + properties.getValue(Properties.APPEND_CONNECTION)),
            properties.getValue(Properties.USERNAME),
            properties.getValue(Properties.PASSWORD)
        );
    }

}