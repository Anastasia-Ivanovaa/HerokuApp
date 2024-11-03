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

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckboxes() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        boolean checkboxOneBeforeSelecting = checkboxes.get(0).isSelected();
        softAssert.assertFalse(checkboxOneBeforeSelecting, "The first checkbox is checked.");
        checkboxes.get(0).click();
        boolean checkboxOneAfterSelecting = checkboxes.get(0).isSelected();
        softAssert.assertTrue(checkboxOneAfterSelecting, "The first checkbox is unchecked.");
        boolean checkboxTwoBeforeUnselecting = checkboxes.get(1).isSelected();
        softAssert.assertTrue(checkboxTwoBeforeUnselecting, "The second checkbox is unchecked.");
        checkboxes.get(1).click();
        boolean checkboxTwoAfterUnselecting = checkboxes.get(1).isSelected();
        softAssert.assertFalse(checkboxTwoAfterUnselecting, "The second checkbox is checked.");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
