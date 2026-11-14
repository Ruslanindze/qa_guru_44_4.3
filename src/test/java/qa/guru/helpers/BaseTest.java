package qa.guru.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import qa.guru.config.Environment;

import java.util.Map;
import java.util.TimeZone;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {
    private boolean addPageSource = Environment.ADD_PAGE_SOURCE;
    private boolean addScreenshot = Environment.ADD_SCREENSHOT;
    private boolean addBrowserLog = Environment.ADD_BROWSER_LOG;
    private boolean addVideo = Environment.ADD_VIDEO;

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));

        Configuration.baseUrl = Environment.URL_APP;
        Configuration.headless = Environment.HEADLESS;
        Configuration.browser = Environment.BROWSER;
        Configuration.browserVersion = Environment.BROWSER_VERSION;
        Configuration.browserSize = Environment.BROWSER_SIZE;
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 15000;

        if (Environment.IS_REMOTE) {
            Configuration.remote = Environment.REMOTE_URL;

            if (Environment.ADD_VIDEO) {
                MutableCapabilities options = new MutableCapabilities();
                options.setCapability("se:cdpEnabled", false);
                options.setCapability("selenoid:options", Map.of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
                Configuration.browserCapabilities = options;
            }
        }
    }

    @AfterEach
    void addAttachments() {
        if (addScreenshot) Attach.takeSreenshot("Last screenshot");
        if (addPageSource) Attach.pageSource();
        if (addBrowserLog) Attach.browserConsoleLogs();
        if (Environment.IS_REMOTE && addVideo) Attach.addVideo();

        closeWebDriver();
    }
}
