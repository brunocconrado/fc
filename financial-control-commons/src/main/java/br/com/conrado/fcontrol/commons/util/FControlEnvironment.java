package br.com.conrado.fcontrol.commons.util;

public class FControlEnvironment {

    /* chaves do environment.properties */

    public static final String FLUXOAV_ENV = "fluxoav.env";
    public static final String FLUXOAV_CONTEXT = "fluxoav.web.context";
    public static final String FLUXOAV_VERSION = "fluxoav.web.version";
    public static final String FLUXOAV_DEPLOYURL = "fluxoav.deploy.url";
    public static final String FLUXOAV_LOGDIR = "fluxoav.logdir";

    public static final String JNDI_DS = "java:comp/env/jdbc/fluxoavdb";

    public static final String DEV = "dev";
    public static final String QA = "qa";
    public static final String PROD = "prod";
    public static final String LOCAL = "local";

    public static final String ACTIVE_PROFILES = "spring.profiles.active";

    public static final String AV_CACHE = "av";
    public static final String AV_CACHE_FALLBACK = AV_CACHE + "_fallback";

    public static final String OI_PROTOCOL_WS_URL = "oi.protocol.ws.url";
    public static final String OI_BRM_PROVISIONING_WS_URL = "oi.brm.provisioning.ws.url";
    public static final String OI_BRM_WS_URL = "oi.brm.ws.url";

    public static final String MAIL_QUEUE_URL = "mail.queue.url";
    public static final String MAIL_QUEUE_NAME = "mail.queue.name";
    public static final String MAIL_QUEUE_PARAM = "mail.queue.param";
    public static final String MAIL_QUEUE_AV_DEFAULT_ATTR = "mail.queue.av.default.attr";

    public static final String MAIL_SERVICE_URL = "mail.service.url";

    public static final String DEV_CUSTOMER_CPF = "dev.customer.cpf";
    public static final String DEV_CUSTOMER_VELOX = "dev.customer.velox";

    public static final String BLACKLIST_AMOUNT_INSTALATION = "blacklist%s.amount.instalation";
    public static final String BLACKLIST_AMOUNT_DAYS_IN = "blacklist%s.amount.days.in";

    public static final String APPLICATION_LANGUAGE_UNSET = "pt";
    public static final String APPLICATION_LANGUAGE = "application.language";

    public static final String APPLICATION_COUNTRY_UNSET = "BR";
    public static final String APPLICATION_COUNTRY = "application.country";

    public static final String APPLICATION_ENCODING_DEFAULT_UNSET = "UTF-8";
    public static final String APPLICATION_ENCODING_DEFAULT = "application.encoding.default";

    public static final String APPCODE_DEFAULT = "DLM";
    public static final String BRAZIL_COUNTRY_CODE = "55";
    
    public static final String SERVICE_PERMITTED_ANTIVIRUS = "service.permitted.antivirus";
    public static final String SERVICE_PERMITTED_BACKUP = "service.permitted.backup";

    public static final String MOBILE_REDIRECT = "mobile.redirect";

    public static final String BRM_ERROR_CODES = "brm.error.codes";

    /* Mongo properties */

    public static final String MONGO_HOST = "mongo.host";
    public static final String MONGO_PORT = "mongo.port";
    public static final String MONGO_DB = "mongo.db";
    public static final String MONGO_TIMEOUT = "mongo.connection.timeout";
    public static final String MONGO_MAX_WAIT_TIME = "mongo.max.wait.time";

    /* chaves para uso via @Value (spring) */

    public static final String VALUE_APPCODE = "${service.av.appcode}";
    public static final String VALUE_ANTIVIRUSID = "${service.permitted.antivirus}";
    public static final String VALUE_BACKUPID = "${service.permitted.backup}";
    public static final String VALUE_AV_TDU = "${service.poid.antivirus.tdu}";
    public static final String VALUE_BACKUP_TDU = "${service.poid.backup.tdu}";
    public static final String VALUE_AV_URL = "${service.av.url}";
    public static final String VALUE_AV_RETURNURL = "${service.av.returnurl}";
    public static final String VALUE_AV_FORM_TARGET = "${service.av.form.target}";
    public static final String VALUE_AV_AFFID = "${service.av.affid}";

    public static final String VALUE_DB_VERSION = "${fluxoav.db.version}";

    /* BRM Health Check */

    public static final String BRM_CPF_HEALTH_CHECK = "${brm.cpf.health.check}";
    public static final String BRM_LOGIN_HEALTH_CHECK = "${brm.login.health.check}";
    public static final String BRM_POID_HEALTH_CHECK = "${brm.poid.health.check}";

}
