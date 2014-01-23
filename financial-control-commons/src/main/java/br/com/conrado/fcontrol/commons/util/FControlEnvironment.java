package br.com.conrado.fcontrol.commons.util;

public class FControlEnvironment {

    /* chaves do environment.properties */
    
    //APLICATION CONFIG
    public static final String APPLICATION_LANGUAGE = "application.language";
    
    public static final String APPLICATION_LANGUAGE_UNSET = "pt";
    public static final String APPLICATION_COUNTRY_UNSET = "BR";
    public static final String APPLICATION_COUNTRY = "application.country";

    public static final String APPLICATION_ENCODING_DEFAULT_UNSET = "UTF-8";
    public static final String APPLICATION_ENCODING_DEFAULT = "application.encoding.default";
    
    public static final String APLICATION_ENV = "aplication.env";
    public static final String APLICATION_CONTEXT = "aplication.context";
    public static final String APLICATION_VERSION = "aplication.version";
    public static final String APLICATION_DEPLOYURL = "aplication.deploy.url";
    public static final String APLICATION_LOGDIR = "aplication.logdir";

    public static final String ACTIVE_PROFILES = "spring.profiles.active";
    
    public static final String PROFILE_LOCAL = "local";
    public static final String PROFILE_DEV = "dev";
    public static final String PROFILE_QA = "qa";
    public static final String PROFILE_PROD = "prod";

    /* Mongo properties */
    public static final String MONGO_HOST = "mongo.host";
    public static final String MONGO_PORT = "mongo.port";
    public static final String MONGO_DB = "mongo.db";
    public static final String MONGO_TIMEOUT = "mongo.connection.timeout";
    public static final String MONGO_MAX_WAIT_TIME = "mongo.max.wait.time";
    
}
