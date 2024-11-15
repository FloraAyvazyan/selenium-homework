import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LocatorsTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void unorderedListTest(){
        driver.get("https://jqueryui.com/slider/");
        WebElement asideElement = driver.findElement(By
                .xpath("//aside[h3[@class='widget-title' and text()='Effects']]"));
        List<WebElement> listItems = asideElement.findElements(By.cssSelector("ul li"));

        List<WebElement> filteredList = listItems.stream()
                .filter(li -> li.getText().contains("o"))
                .collect(Collectors.toList());

        filteredList.parallelStream().forEach(item -> {
            WebElement link = item.findElement(By.tagName("a"));
            String href = link.getAttribute("href");
            if (!href.contains(Constants.EXPECTED_HREF_KEYWORD)) {
                System.out.println(href);
            }
        });


    }

    @Test
    public void buttonsTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElement = driver.findElement(By.xpath("//button[text() = \"Add Element\"]"));
        for(int i = 0; i < 3; i++){
            addElement.click();
        }
        Thread.sleep(4000);

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[class^='added-manually']"));
        WebElement lastDeleteButton = deleteButtons.get(deleteButtons.size() - 1);


        Assert.assertTrue(lastDeleteButton.getAttribute("class").contains(Constants.ADDED_MANUALLY_CLASS));
        Assert.assertEquals(lastDeleteButton.getAttribute("onclick"), Constants.EXPECTED_ONCLICK_ATTRIBUTE);


    }

    @Test
    public void challengingDomTest(){
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(text(),'Lorem')]"));


        for (WebElement element : elements) {
            String elementText = element.getText();
            if (elementText.contains("Apeirian9")) {
                System.out.println("Found the element: " + elementText);
                break;
            }
        }
    }
}
