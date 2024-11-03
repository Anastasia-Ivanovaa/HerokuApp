import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDropdown() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        softAssert.assertEquals(options.get(0).getText(), "Please select an option");
        softAssert.assertEquals(options.get(1).getText(), "Option 1");
        softAssert.assertEquals(options.get(2).getText(), "Option 2");
        select.selectByVisibleText("Option 1");
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected(), "The option is NOT selected.");
        select.selectByVisibleText("Option 2");
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected(), "The option is NOT selected.");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
