package util;


// ### Step 2

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

// 1) It should take two parameters:
//   1) element of type `Object` (or a Generic type, for example T if you want to use Generics).
//   2) visibleText of type `String`.
// 2) If `element` parameter is an instance of `Select` class, use the
// second parameter to choose an option.
// 3) If `element` parameter is an instance of `WebElement` class,
// then it is not a native `select` tag. Use the second parameter to
// locate the correct option and click on it.
//   1) Use your Java/Locators knowledge and imagination to the
//   fullest and create a good implementation.
public class HelperClass {
    public static <T> void universalSelector(T element, String visibleText) {
        if (element instanceof Select) {
            ((Select) element).selectByVisibleText(visibleText);
        } else if (element instanceof WebElement) {
            WebElement webElement = (WebElement) element;
            for (WebElement option : webElement.findElements(By.tagName("li"))) {
                if (option.getText().equals(visibleText)) {
                    option.click();
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Unsupported element type");
        }

        System.out.println("I AM A FIX");
    }
}
