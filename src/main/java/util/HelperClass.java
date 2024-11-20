package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

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

    public static void getCellData(WebDriver driver, String tableXPath, String text) {
        WebElement table = driver.findElement(By.xpath(tableXPath));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        for (WebElement row : tableRows) {
            List<WebElement> rowEntries = row.findElements(By.xpath(".//th | .//td"));
            for (WebElement entry : rowEntries) {
                String elementText = entry.getText();
                if (elementText.equals(text)) {
                    System.out.println(elementText);
                    return;
                }
            }
        }
        throw new NoSuchElementException("Element with text '" + text + "' not found in the table.");
    }
}
