package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertsTest {

    WebDriver driver;

    @Test
    public void AlertsTest(){
        openBrowser();
        choseMenu();
        choseSubMenu();
        //interactWithFirstAlert();
        //interactWithTimerAlert();
        interactWithConfirmAlert("Cancel");
        interactWithPromptAlert("Maria");

    }

    // Metoda care deschide browserul
    public void openBrowser(){
        driver = new ChromeDriver(); // Navigam catre pagina website-ului
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize(); // Facem fereastra maxima
    }

    // Identificam meniul dorit si facem click pe el
    public void choseMenu(){
        WebElement alertsWindowsAndFramesMenu = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        scrollToElement(alertsWindowsAndFramesMenu);
        alertsWindowsAndFramesMenu.click(); // Actionam butonul din meniul de mai sus
    }

    // Facem o metoda care sa faca scroll
    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Indendificam submeniul dorit si facem click pe el
    public void choseSubMenu(){
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Alerts']"));
        alertsSubMenu.click();
    }

    // Facem o metoda care sa interactioneze cu prima alerta
    public void interactWithFirstAlert(){
        WebElement firstAlertButton = driver.findElement(By.id("alertButton"));
        firstAlertButton.click();
        Alert FirstAlert = driver.switchTo().alert();
        FirstAlert.accept();
    }

    // Facem o metoda care sa interactioneze cu a doua alerta
    public void interactWithTimerAlert(){
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();
        // Inainte sa schimbam focusul pe alerta, trebuie sa punem un wait explicit
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert timerAlert = driver.switchTo().alert();
        timerAlert.accept();
    }

    // Facem o metoda care sa interactioneze cu a treia alerta
    public void interactWithConfirmAlert(String alertValue) {
        WebElement confirmAlertButton = driver.findElement(By.id("confirmButton"));
        confirmAlertButton.click();
        Alert confirmAlert = driver.switchTo().alert();
        if (alertValue.equals("Ok")) {
            confirmAlert.accept();
            WebElement alertResultText = driver.findElement(By.id("confirmResult"));
            Assert.assertTrue(alertResultText.getText().contains(alertValue), "You didn't select Ok. You selected: " + alertResultText.getText());
        }
        if (alertValue.equals("Cancel")) {
            confirmAlert.dismiss();
            WebElement alertResultText = driver.findElement(By.id("confirmResult"));
            Assert.assertTrue(alertResultText.getText().contains(alertValue), "You didn't select Cancel. You selected: " + alertResultText.getText());
        }
    }

    // Facem o metoda care sa interactioneze cu a patra alerta
    public void interactWithPromptAlert(String nameInput){
        WebElement promptAlertButton = driver.findElement(By.id("promtButton"));
        promptAlertButton.click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys(nameInput);
        promptAlert.accept();
        WebElement promptResult = driver.findElement(By.id("promptResult"));
        Assert.assertTrue(promptResult.getText().contains(nameInput), "The result is not as expected. Result: " + promptResult.getText());
    }

}


