import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;

public class FramesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkFrames() {
        driver.get("https://the-internet.herokuapp.com/frames");
        driver.findElement(By.xpath("//a[text()='iFrame']")).click();
        driver.findElement(By.xpath("//div[@class='tox-icon']")).click();
        String textInFrame = driver.switchTo().frame("mce_0_ifr").findElement(By.id("tinymce")).getText();
        Assert.assertEquals(textInFrame, "Your content goes here.", "The text isn't matched.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
