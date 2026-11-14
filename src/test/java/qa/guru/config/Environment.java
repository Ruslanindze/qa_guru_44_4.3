package qa.guru.config;


import java.util.List;
import java.util.Map;

public class Environment {
    public static final String URL_APP = System.getProperty("URL_APP", "https://demoqa.com");

    // Base configs
    public static final boolean IS_REMOTE = Boolean.parseBoolean(System.getProperty("IS_REMOTE", "false"));
    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("HEADLESS", "false"));

    // Remote configs
    public static final String REMOTE_USER = System.getProperty("REMOTE_USER");
    public static final String REMOTE_PASS = System.getProperty("REMOTE_PASS");
    public static final String REMOTE_URL = System.getProperty("REMOTE_URL",
            REMOTE_USER.isEmpty() || REMOTE_PASS.isEmpty() ? "" : "https://%s:%s@selenoid.qa.guru/wd/hub".formatted(REMOTE_USER, REMOTE_PASS));

    // Browser configs
    public static final Map<String, List<String>> MAP_BROWSER_VERSIONS = Map.of(
            "chrome", List.of("148.0", "156.0", "157.0"),
            "firefox", List.of("159.0", "159.0-min", "160.0", "160.0-min")
    );
    public static final String BROWSER = System.getProperty("BROWSER", "chrome");
    public static final String BROWSER_VERSION = System.getProperty("BROWSER_VERSION",
            MAP_BROWSER_VERSIONS.get(BROWSER).getLast());
    public static final String BROWSER_SIZE = System.getProperty("BROWSER_SIZE", "FULL");

    // Attachments
    public static final boolean ADD_PAGE_SOURCE = Boolean.parseBoolean(System.getProperty("ADD_PAGE_SOURCE", "false"));
    public static final boolean ADD_SCREENSHOT = Boolean.parseBoolean(System.getProperty("ADD_PAGE_SOURCE", "false"));
    public static final boolean ADD_BROWSER_LOG = Boolean.parseBoolean(System.getProperty("ADD_BROWSER_LOG", "false"));
    public static final boolean ADD_VIDEO = Boolean.parseBoolean(System.getProperty("ADD_PAGE_SOURCE", "false"));
}
