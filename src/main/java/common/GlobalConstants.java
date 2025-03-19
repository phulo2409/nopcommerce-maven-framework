package common;

public class GlobalConstants {

    public static final long SHORT_TIMEOUT = 15;
    public static final long LONG_TIMEOUT = 30;
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    private static final String SEPARATOR = System.getProperty("file.separator");
    public static final String DATA_TEST_PATH = PROJECT_PATH + SEPARATOR + "dataTest" + SEPARATOR;

    public final static String ADMIN_EMAIL = "admin@yourstore.com";
    public final static String ADMIN_PASSWORD= "admin";
}
