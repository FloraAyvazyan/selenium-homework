//### Step 5
// * Create test class in `src/test/java` named `NavigationTest`.
// * As in previous step, write @BeforeClass and @AfterClass methods.
//   * In this class you should open `https://ultimateqa.com/automation` page.
//* In this class write following test:
//


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbcitacademy.data.Constants.*;

public class NavigationTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(AUTOMATION_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void goToSuccessStoriesAndBack() throws InterruptedException {
        WebElement services = driver.findElement(By.linkText("Services"));
        services.click();
        Thread.sleep(4000);

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(CONSULTING_URL)) {
            System.out.println("URL is correct: " + currentUrl);
        } else {
            System.out.println("URL is incorrect! Expected: " + CONSULTING_URL + ", but got: " + currentUrl);
        }

        driver.navigate().back();
        Thread.sleep(4000);

        currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(AUTOMATION_URL)) {
            System.out.println("URL is correct: " + currentUrl);
        } else {
            System.out.println("URL is incorrect! Expected: " +AUTOMATION_URL + ", but got: " + currentUrl);
        }
    }
}
