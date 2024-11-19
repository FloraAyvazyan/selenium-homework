import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static ge.tbcitacademy.data.Constants.DYNAMIC_CONTROLS_URL2;
import static ge.tbcitacademy.data.Constants.PROGRESS_BAR_URL;

public class WaitsTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



    //1) waitForDisappearance:
    //   1) navigate to http://the-internet.herokuapp.com/dynamic_controls
    //   2) Click on the 'Enable' button.
    //   3) Wait for the disappearance of the progress bar.
    //   4) Validate that the button you just clicked now has 'Disable' as its text.
    //   4) Proceed to type 'ACCESS GRANTED' in the input field above the 'Disable' button.
    @Test
    public void waitForDisappearance(){
        driver.get(DYNAMIC_CONTROLS_URL2);
        WebElement enableButton = driver.findElement(By.xpath("//button[text()= \"Enable\"]"));
        enableButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

        Assert.assertEquals(enableButton.getText(), "Disable");

        WebElement inputField = driver.findElement(By.xpath("//input[@type = \"text\"]"));
        inputField.sendKeys("ACCESS GRANTED");
    }

    //2) waitForText:
    //   1) Navigate to https://demoqa.com/progress-bar
    //   2) Click to 'Start' button
    //   3) Wait until progress bar reaches 100 % and print '100%'

    @Test
    public void waitForText(){
        driver.get(PROGRESS_BAR_URL);
        driver.findElement(By.xpath("//button[@id = \"startStopButton\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("progress-bar")), "100%"));

        System.out.println("100%");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
