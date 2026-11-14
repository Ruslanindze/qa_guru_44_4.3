package qa.guru.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import qa.guru.config.TestConfig;

import java.util.Map;
import java.util.TimeZone;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {
    protected static final TestConfig testConfig = ConfigFactory.create(
            TestConfig.class,
            System.getProperties(),
            System.getenv()
    );

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));

        Configuration.baseUrl = testConfig.urlApp();
        Configuration.headless = testConfig.headless();
        Configuration.browser = testConfig.browser();
        Configuration.browserVersion = testConfig.browserVersion();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 15000;
        Configuration.remote = testConfig.urlRemote();

        // Если full, то по-умолчанию full hd
        if ("FULL".equalsIgnoreCase(testConfig.browserSize())) {
            Configuration.browserSize = "1920x1080";
        } else {
            Configuration.browserSize = testConfig.browserSize();
        }

        // Добавление записи видео
        if (testConfig.addVideo()) {
            MutableCapabilities options = new MutableCapabilities();
            options.setCapability("se:cdpEnabled", false);
            options.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = options;
        }
    }

    @AfterEach
    void addAttachments() {
        if (testConfig.addScreenshot()) Attach.takeSreenshot("Last screenshot");
        if (testConfig.addPageSource()) Attach.pageSource();
        if (testConfig.addBrowserLog()) Attach.browserConsoleLogs();
        if (testConfig.addVideo()) Attach.addVideo();

        closeWebDriver();
    }
}
