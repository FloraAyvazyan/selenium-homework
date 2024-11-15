
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbcitacademy.data.Constants.*;


//* In `CommandsTest` write a test method annotated by @BeforeClass
// annotation and name it `setup`.
//   * In this method, using WebDriverManager, open the browser (chrome).
//   * Navigate to http://the-internet.herokuapp.com/dynamic_controls

//* Write a test annotated by @AfterClass annotation and name it `tearDown`.
//   * In this method terminate the browser.
public class CommandsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(DYNAMIC_CONTROLS_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void buttonsTest() throws InterruptedException {
        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        enableButton.click();
        Thread.sleep(4000);

        WebElement inputField = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        WebElement enabledText = driver.findElement(By.id("message"));

        if (inputField.isEnabled()) {
            System.out.println("input field became enable");
        } else {
            System.out.println("input field is not enable");
        }

        if (enabledText.isDisplayed()) {
            System.out.println("enable text is displayed and its value is : " + ENABLE_BUTTON_TEXT );
        } else {
            System.out.println("enable text is not displayed");
        }

        inputField.sendKeys(INPUT_FIELD_TEXT);
        Thread.sleep(4000);
        System.out.println(inputField.getAttribute("value"));

        inputField.clear();
    }

    @Test
    public void labelsTest() {
        WebElement heading = driver.findElement(By.xpath("//h4[text() = \"Dynamic Controls\"]"));
        String headingText = heading.getText();
        if (headingText.equals(MAIN_HEADING_TEXT)) {
            System.out.println("Main heading text matches: " + headingText);
        } else {
            System.out.println("Main heading text does not match: " + headingText);
        }
        WebElement description = driver.findElement(By
                .xpath("//p[text() = \"This example demonstrates when elements (e.g., checkbox, input field, etc.) are changed asynchronously.\"]"));

        String descriptionText = description.getText();
        if (descriptionText.equals(DESCRIPTION_TEXT)) {
            System.out.println("Main description text matches: " + descriptionText);
        } else {
            System.out.println("Main description text does not match: " + descriptionText);
        }
    }

}

