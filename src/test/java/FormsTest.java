import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static ge.tbcitacademy.data.Constants.CUSTOM_DROPDOWN_URL;
import static ge.tbcitacademy.data.Constants.NATIVE_DROPDOWN_URL;
import static util.HelperClass.universalSelector;


public class FormsTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



    //1) customDropDownTest:
    //   1) navigate to https://tympanus.net/Tutorials/CustomDropDownListStyling/
    //   and go to demo2.
    //   2) Validate that the drop down menu is not visible before you click on it.
    //   3) Click on the 'Sign in with' drop down and validate that the menu is now visible.
    //   4) Use your `universalSelector` function to select 'Github'.


    @Test
    public void customDropDownTest() {
        driver.get(CUSTOM_DROPDOWN_URL);
        WebElement demo2Button = driver.findElement(By.xpath("//a[text() = \"Demo 2\"]"));
        demo2Button.click();
        WebElement dropDownMenu = driver.findElement(By.xpath("//ul[@class = \"dropdown\"]"));
        Assert.assertFalse(dropDownMenu.isDisplayed());
        WebElement signInWith = driver.findElement(By.xpath("//div[@class= \"wrapper-dropdown-2\"]"));
        signInWith.click();
        Assert.assertTrue(dropDownMenu.isDisplayed());
        universalSelector(dropDownMenu, "Github");

    }

//    2) nativeDropDownTest
//   1) navigate to https://techcanvass.com/examples/register.html
//   2) Choose radio button adjacent to your gender.
//   3) Use your `universalSelector` function to select 'Mega 123 Medium screen' from the native `select` tag.
//   4) Fill all the other input fields with any data (you can explore Java Faker library for fake data if you wish).
//   5) Submit the form.


    @Test
    public void nativeDropDownTest(){
        driver.get(NATIVE_DROPDOWN_URL);
        WebElement genderButton = driver.findElement(By.xpath("//input[@value = \"female\"]"));
        genderButton.click();

        WebElement dropdown = driver.findElement(By.xpath("//select[@name  =\"model\" ]"));
        Select select = new Select(dropdown);
        universalSelector(select, "Mega 123 Medium screen");

        driver.findElement(By.xpath("//input[@value = \"First Name\"]")).sendKeys("Flora");
        driver.findElement(By.xpath("//input[@value = \"Last Name\"]")).sendKeys("Ayvazyan");
        driver.findElement(By.xpath("//input[@value = \"City\"]")).sendKeys("Tbilisi");
        driver.findElement(By.xpath("//input[@value = \"Contact1\"]")).sendKeys("551-52-13-24");
        driver.findElement(By.xpath("//input[@value = \"Register\"]")).click();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
