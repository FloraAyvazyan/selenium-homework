// ### Step 4
// * Create test class in `src/test/java` named `WebElementTest`.
// * As in previous step, write @BeforeClass and @AfterClass methods.
//   * In this class, you should open http://the-internet.herokuapp.com/drag_and_drop.
//* In this class write following tests:
//




import ge.tbcitacademy.data.Constants.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbcitacademy.data.Constants.*;

public class WebElementTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(DRAG_AND_DROP_URL);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void dimensionsTest() throws InterruptedException {
        int columnAY = driver.findElement(By.id("column-a")).getLocation().getY();
        int columnBY = driver.findElement(By.id("column-b")).getLocation().getY();
        Thread.sleep(4000);

        if (columnAY == columnBY) {
            System.out.println("Y coordinates are the same");
        } else {
            System.out.println("Y coordinates arent the same");
        }
    }

    @Test
    public void linkTest() {
        WebElement link = driver.findElement(By.linkText("Elemental Selenium"));
        String href = link.getAttribute("href");
        if ("http://elementalselenium.com/".equals(href)) {
            System.out.println("href attributes match " + href);
        } else {
            System.out.println("href attributes dont match " + href);
        }
    }
}
