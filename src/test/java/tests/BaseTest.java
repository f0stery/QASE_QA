package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.HomePage;
import utils.TestListener;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
//        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";

        loginPage = new LoginPage();
        homePage = new HomePage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @AfterMethod
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
