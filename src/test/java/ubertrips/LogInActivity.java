package ubertrips;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LogInActivity {

    AndroidDriver driver;

        @BeforeMethod
        public void setupTest() throws MalformedURLException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("platormName", "Android");
            //capabilities.setCapability("platformVersion", "5.0.2(Lollipop)");
            capabilities.setCapability("deviceName", "Xiaomi");
            capabilities.setCapability("app", "C:/Users/PC-1/Dropbox/apps/CodesForUber_com.freerides.ubertrips_1.3.apk"); // work PC

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

        private String app_pm = "com.freerides.ubertrips:id/";
        private By email = By.id(app_pm + "login_form_et");
        private By start = By.id(app_pm + "login_form_btn");

        @Test(priority = 2)
        public void validEmail() {

            String valid = "timqae@gmail.com";

            driver.findElement(email).sendKeys(valid);
            driver.hideKeyboard();
            driver.findElement(start).click();
            //driver.closeApp();
        }

        @Test(priority = 1)
        public void invalidEmail() {

            String[] logins = new String[]{
                    " ",
                /*"w:\"/@err.com",
                "Abc@err..com",
                "A@b@err.com",
                "£€@&{.com",
                "er r /t@err.com"*/
            };

            for (String invalid : logins) {
                driver.findElement(email).sendKeys(invalid);
                driver.hideKeyboard();
                driver.findElement(start).click();
                driver.findElement(email).clear();
            }
        }

        @AfterTest
        public void afterTest() {
            driver.quit();
        }
    }


