package qa.guru.config;


import java.util.List;
import java.util.Map;

public class Environment {
    public static final String URL_APP = System.getProperty("URL_APP", "https://demoqa.com");

    // Remote configs
    public static final String URL_REMOTE = System.getProperty("URL_REMOTE");

    // Browser configs
    public static final Map<String, List<String>> MAP_BROWSER_VERSIONS = Map.of(
            "chrome", List.of("148.0", "156.0", "157.0"),
            "firefox", List.of("159.0", "159.0-min", "160.0", "160.0-min")
    );
    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("HEADLESS", "false"));
    public static final String BROWSER = System.getProperty("BROWSER", "chrome");
    public static final String BROWSER_VERSION;
    public static final String BROWSER_SIZE = System.getProperty("BROWSER_SIZE", "FULL");

    // Attachments
    public static final boolean ADD_PAGE_SOURCE = Boolean.parseBoolean(System.getProperty("ADD_PAGE_SOURCE", "false"));
    public static final boolean ADD_SCREENSHOT = Boolean.parseBoolean(System.getProperty("ADD_SCREENSHOT", "false"));
    public static final boolean ADD_BROWSER_LOG = Boolean.parseBoolean(System.getProperty("ADD_BROWSER_LOG", "false"));
    public static final boolean ADD_VIDEO = Boolean.parseBoolean(System.getProperty("ADD_VIDEO", "false"));

    // Статический инициализатор
    static {
        String browseVersion = System.getProperty("BROWSER_VERSION");

        if (browseVersion.equals("first")) {
            BROWSER_VERSION = MAP_BROWSER_VERSIONS.get(BROWSER).getFirst();
        } else if (browseVersion.equals("last")) {
            BROWSER_VERSION = MAP_BROWSER_VERSIONS.get(BROWSER).getLast();
        } else if (MAP_BROWSER_VERSIONS.get(BROWSER).contains(browseVersion)) {
            BROWSER_VERSION = browseVersion;
        } else {
            BROWSER_VERSION = MAP_BROWSER_VERSIONS.get(BROWSER).getLast();
        }
    }
}
