import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class TyposTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTypos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        String correctText = "Sometimes you'll see a typo, other times you won't.";
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < 10; i++) {
            List<WebElement> tags = driver.findElements(By.tagName("p"));
            WebElement secondTag = tags.get(1);
            String textOnPage = secondTag.getText();
            softAssert.assertEquals(textOnPage, correctText, "There is typo in the text: 'won,t' is misspelled");
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
