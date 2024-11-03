import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInputNumbers() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputNumber = driver.findElement(By.tagName("input"));
        inputNumber.sendKeys("10");
        inputNumber.sendKeys(Keys.ARROW_UP);
        String increasedNumber = inputNumber.getAttribute("value");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(increasedNumber, "11", "Incorrect number.");
        inputNumber.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN);
        String decreasedNumber = inputNumber.getAttribute("value");
        softAssert.assertEquals(decreasedNumber, "9", "Incorrect number.");
        softAssert.assertAll();
    }

    @Test
    public void checkInputLetters(){
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement inputNumber = driver.findElement(By.tagName("input"));
        inputNumber.sendKeys("abc");
        String letters = inputNumber.getAttribute("value");
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(letters,"","The input takes letters");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
