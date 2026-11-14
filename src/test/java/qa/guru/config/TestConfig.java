package qa.guru.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "system:properties", // 1. Сначала ищет -D параметры (для локального запуска из IDEA)
        "system:env",        // 2. Затем ищет переменные окружения (для запуска из Jenkins)
        "classpath:config.properties" // 3. Если ничего не задано, берет значения по умолчанию из файла
})

public interface TestConfig extends Config {
    @Config.Key("URL_APP")
    @Config.DefaultValue("https://demoqa.com")
    String urlApp();

    @Config.Key("URL_REMOTE")
    String urlRemote();

    @Config.Key("BROWSER")
    @Config.DefaultValue("chrome")
    String browser();

    @Config.Key("BROWSER_VERSION")
    @Config.DefaultValue("latest")
    String browserVersion();

    @Config.Key("BROWSER_SIZE")
    @Config.DefaultValue("FULL")
    String browserSize();

    @Key("HEADLESS")
    @DefaultValue("false")
    boolean headless();

    @Key("ADD_PAGE_SOURCE")
    @DefaultValue("false")
    boolean addPageSource();

    @Key("ADD_SCREENSHOT")
    @DefaultValue("false")
    boolean addScreenshot();

    @Key("ADD_BROWSER_LOG")
    @DefaultValue("false")
    boolean addBrowserLog();

    @Key("ADD_VIDEO")
    @DefaultValue("false")
    boolean addVideo();
}
