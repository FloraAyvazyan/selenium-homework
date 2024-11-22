import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.HelperClass;
import java.time.Duration;

public class TablesTest {
    private static WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); //
        //     wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // გამოიყენება ელემენტების 20 წამის ლოდინისთვის.
        driver.manage().window().maximize();
    }

    @Test
    public void tablesTest() {
        // `JavascriptExecutor` ობიექტის შექმნა JavaScript ოპერაციების შესასრულებლად.
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get(Constants.URL1); // ტვირთავს URL1-ს (განსაზღვრულია `Constants` კლასში).

        js.executeScript("window.scrollBy(0,500)", ""); // გვერდს 500 პიქსელით ქვემოთ ახვევს.
        HelperClass.getCellData(driver, "//table[@id='customers']", Constants.ROLAND_MENDEL_TEXT);

        js.executeScript("window.scrollBy(0,200)", ""); // გვერდს კიდევ 200 პიქსელით ქვემოთ ახვევს.
        HelperClass.getCellData(driver, "//table[@class='tsc_table_s13']", Constants.SHANGHAI_TEXT);

        driver.get(Constants.URL2);
        HelperClass.getCellData(driver, "//table[@id='t01']", Constants.HONDA_TEXT);

        driver.get(Constants.URL3);
        HelperClass.getCellData(driver, "//div[@class='large-10 columns']//table", Constants.PHEDRUM4_TEXT);

    }

    @Test
    public void myTableTest() {
        driver.get(Constants.URL4);
        JavascriptExecutor js = (JavascriptExecutor) driver; // ქმნის `JavascriptExecutor` ობიექტს.
        js.executeScript("window.scrollBy(0,500)", ""); // გვერდს 500 პიქსელით ქვემოთ ახვევს.
        HelperClass.getCellData(driver, "//table[@id='countries']", Constants.FRENCH_TEXT);

        driver.get(Constants.URL5);
        HelperClass.getCellData(driver, "//table[@id='table02']", Constants.ACCOUNTANT_TEXT);

    }

    @AfterClass
    public void tearDown() {
        if (!(driver == null)) { // ამოწმებს, არის თუ არა `driver` ობიექტი ცარიელი.
            driver.quit(); // ბრაუზერს ხურავს და ყველა რესურსს ათავისუფლებს.
        }
    }
}

