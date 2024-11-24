import Base.BaseClass;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.xpath;

public class Exceptions extends BaseClass {

    @Test
    public void InvalidSelectorExceptionTest(){
        driver.get(Constants.AUTOMATIONEXERCISE);
       try {
           WebElement button = driver.findElement(By.className("btn btn-success"));
           button.click();
       }catch (InvalidSelectorException e){
           System.out.println("\nException caught " + e.getMessage());
           //className სელექტორი იღებს მხოლოდ ერთ კლასს,
           // ხოლო "btn btn-success" შეიცავს ორ კლასს, გამოყოფილს სივრცით
       }
    }


    @Test
    public void noSuchFrameExceptionTest(){
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        try {
            driver.switchTo().frame("frame-middle");

        }catch (NoSuchFrameException e){
            System.out.println("\nException caught " + e.getMessage());
        }
        //აქ ჯერ top frame-ზეა საჭირო გადართვა და მერე შუზე
    }

    @Test
    public void ElementNotInteractableExceptionest() {
        driver.get(Constants.AUTOMATIONEXERCISE);
        try {
            WebElement kidsButton = driver.findElement(xpath("//*[@id=\"Kids\"]/div/ul/li[2]/a"));
            kidsButton.click();
        } catch (ElementNotInteractableException e) {
            System.out.println("\nException caught " + e.getMessage());

            //ეს ელემეტი ჯერ არა არის ინტერაქტიული, გამოსავალი ისააა რომ ჯერ დავკლიკოტ KIDS ღილაკზე
        }
    }

    //მადლობა :დდ

}
