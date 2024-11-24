import Base.BaseClass;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSexecutor extends BaseClass {
    @Test
    public void deleteTest() {
        driver.get(Constants.WEBDRIVERUNIVERSITY);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 3. ველოდებით, სანამ ელემენტი  გახდება ხილვადი და ვპოულობთ მას XPath-ით
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.PRACTICE_MAGIC_XPATH)));
        // 4. ვქმნით Actions ობიექტს და ვსარგებლობთ moveToElement() მეთოდით, რათა მივაწვდეთ მაუსის ფოკუსი ელემენტზე
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        // 5. ველოდებით "Delete" ღილაკის ხილვას და პოულობთ მას XPath-ით
        WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.DELETE_BUTTON_XPATH)));
        // 6. ვასრულებთ JavaScript კოდს ღილაკზე დასაჭერად
        js.executeScript("arguments[0].click()", delete);
        // 7. ველოდებით, სანამ წასაშლელი ელემენტი ("Practice Magic") გაუჩინარდება
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


    @Test
    public void scrollTest() {
        driver.get(Constants.TECHLISTIC);
        // პოულობს ყველა სექციას, რომლის h3 ელემენტი შეიცავს span-ს
        List<WebElement> sections = driver.findElements(By.xpath("//h3/span/parent::h3"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ვამოწმებთ ყველა სექციას და ვშლით არასაჭირო სექციებს
        for (int i = 0; i < sections.size(); i++) {
            String sectionString = sections.get(i).getText();

            if (sectionString.contains("Follow Techlistic")) sections.remove(sections.get(i));
            else if (sectionString.contains("2.2. Types of Web Table")) sections.remove(sections.get(i));
        }

        // პოულობს კოდის მაგალითებს, რომელთაც აქვთ განსაზღვრული CSS კლასი
        List<WebElement> codesFromSections = driver.findElements(By.xpath("//div[@class='bg-black rounded-md mb-4']"));

        // კონკრეტული სექციების დამატება, რომლებიც პირველ ლისტში არ იყო (სხვა გზით ვერ ვიპოვე :))
        WebElement sectioncode2 = driver.findElement(By.xpath("//*[@id='post-body-1325137018292710854']/div[15]/div[1]"));
        WebElement sectionCodeLast = driver.findElement(By.xpath("//*[@id='post-body-1325137018292710854']/div[23]/div[1]"));
        codesFromSections.add(sectioncode2);
        codesFromSections.add(sectionCodeLast);
        // კოდის მაგალითების შენახვა Map-ში (key: სექციის სახელი, value: კოდი)
        Map<String, String> examples = new HashMap<>();

        codesFromSections.remove(4); //პიტონის კოდი წავშალე :)
        Assert.assertEquals(codesFromSections.size(), sections.size());

        // ყველა კოდის მაგალითზე სკროლვა და მათი შენახვა Map-ში
        for (int i = 0; i < codesFromSections.size(); i++) {
            js.executeScript("arguments[0].scrollIntoView();", codesFromSections.get(i));
            examples.put(sections.get(i).getText(), codesFromSections.get(i).getText());
        }

        System.out.println(examples); //სახალისოა:)

        // პოპულარული ლინკების პოვნა და მათი ლეიბლი და ბმული Map-ში შენახვა
        List<WebElement> popularLinks = driver.findElements(By.xpath("//a[text()='Appium Tutorial']/parent::span/parent::li/parent::ul/child::li//a"));
        Map<String, String> popularLinksMap = new HashMap<>();
        for (WebElement popularLink : popularLinks) {
            popularLinksMap.put(popularLink.getText(), popularLink.getAttribute("href"));
        }
        System.out.println(popularLinksMap); //:დ

        // სკროლავს გვერდის ბოლოში
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


}

