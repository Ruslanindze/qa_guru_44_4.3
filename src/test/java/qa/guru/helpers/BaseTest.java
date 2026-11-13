package qa.guru.helpers;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import java.util.TimeZone;

public abstract class BaseTest {
    @BeforeAll
    static void setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));

        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        Configuration.browserVersion = "157.0";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
}
