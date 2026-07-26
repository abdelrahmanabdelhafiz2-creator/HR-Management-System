package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * مسؤولة عن فتح الاتصال بقاعدة البيانات.
 *
 * التغييرات الأمنية اللي اتعملت هنا:
 *   1) بيانات الاتصال (URL / Username / Password) بقت في db.properties
 *      مش مكتوبة جوه الكود مباشرة (Hardcoded Credentials = مشكلة أمان).
 *   2) ممكن كمان نتحكم فيهم عن طريق Environment Variables وقت الـ Deployment
 *      (DB_URL, DB_USERNAME, DB_PASSWORD) من غير ما نلمس الكود خالص.
 *   3) تحميل الـ Driver بيحصل مرة واحدة بس (Static Block) مش مع كل اتصال.
 */
public final class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());

    private static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String url;
    private static final String username;
    private static final String password;

    static {
        Properties props = loadProperties();

        url = getConfig(props, "db.url", "DB_URL",
                "jdbc:mysql://localhost:3306/company_db?useSSL=false&serverTimezone=UTC");
        username = getConfig(props, "db.username", "DB_USERNAME", "root");
        password = getConfig(props, "db.password", "DB_PASSWORD", "");

        try {
            Class.forName(DEFAULT_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL Driver Not Found on classpath", e);
        }
    }

    private DBConnection() {
    }

    private static Properties loadProperties() {
        Properties props = new Properties();

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                LOGGER.warning("db.properties not found on classpath, falling back to defaults/env vars.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not read db.properties, falling back to defaults/env vars.", e);
        }

        return props;
    }

    /**
     * بيدور على القيمة بالترتيب: Environment Variable -> db.properties -> Default.
     * كده لو النظام شغال على سيرفر حقيقي تقدر تحط القيم في الـ Environment
     * من غير ما تسيب أي سر (secret) جوه الكود أو الملفات.
     */
    private static String getConfig(Properties props, String propertyKey, String envKey, String defaultValue) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        String propValue = props.getProperty(propertyKey);
        if (propValue != null && !propValue.isEmpty()) {
            return propValue;
        }

        return defaultValue;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
