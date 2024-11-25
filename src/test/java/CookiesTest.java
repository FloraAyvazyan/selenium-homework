import Base.BaseClass;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CookiesTest extends BaseClass {
    //1) filterCookies:
    // - Navigate to https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html
    // - Use Stream API to find a cookie that has active_template in its name.
    // - Validate that the cookie value contains 'pub.site'.
    @Test

    public void filterCookies() {
        driver.get(Constants.TECHLISTIC);
        Cookie activeCookie = driver.manage().getCookies().stream()
                .filter(cookie -> cookie.getName().contains("active_template"))
                .findFirst()
                .orElse(null);
        Assert.assertNotNull(activeCookie);
        Assert.assertTrue(activeCookie.getValue().contains("pub_site"));


    }

    //2) injectCookie:
    // - Navigate to https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html
    // - Insert 10 different cookies on this website.
    // - Get these cookies and print them all out.
    // - Delete these cookies and assert that they have really been deleted

    @Test
    public void injectCookie() {
        driver.get(Constants.TECHLISTIC);
        for (int i = 1; i <= 10; i++) {
            Cookie newCookie = new Cookie("NewCookie" + i, "Value" + i);
            driver.manage().addCookie(newCookie);
        }

        Set<Cookie> filteredCookies = driver.manage().getCookies().stream()
                .filter(cookie -> cookie.getName().contains("NewCookie")).collect(Collectors.toSet());
        for (Cookie singleCookie : filteredCookies) {
            System.out.println(singleCookie.getName() + ", " + singleCookie.getValue());
        }

        for (Cookie singleCookie : filteredCookies) {
            driver.manage().deleteCookie(singleCookie);
        }
        Assert.assertNotNull(filteredCookies);
    }

    //3) autoCompleteTest:
    // - Navigate to https://demo.automationtesting.in/AutoComplete.html
    // - Start typing a name of an existing country and choose a suggestion.

    @Test
    public void autoCompleteTest() {
        driver.get(Constants.DEMO_AUTOMATIONTESTING_URL);
        driver.findElement(By.id("searchbox")).sendKeys("Georgia");

        List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("ui-menu-item")
        ));

        suggestions.stream()
                .filter(suggestion -> suggestion.getText().equalsIgnoreCase("Georgia"))
                .findFirst()
                .ifPresent(WebElement::click);


    }
}





