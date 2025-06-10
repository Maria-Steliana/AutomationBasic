import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTest {

    WebDriver driver;

    @Test
    public void FramesTest(){
        openBrowser();
        choseMenu();
        choseSubMenu();
        interactWithFrameOne();
        interactWithFrameTwo();
        //closeBrowser();

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
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Frames']"));
        alertsSubMenu.click();
    }

    public void closeBrowser(){
        driver.quit();
    }

    public void interactWithFrameOne(){
        WebElement frameOneElement = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameOneElement);
        WebElement frameOneTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(frameOneTextValue.getText(),expectedText, "Text is not displayed properly.");
        System.out.println("Frame1 text is: " + frameOneTextValue.getText());
        driver.switchTo().defaultContent();
    }

    public void interactWithFrameTwo(){
        WebElement frameTwoElement = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameTwoElement);
        WebElement frameTwoTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(frameTwoTextValue.getText(),expectedText, "Text is not displayed properly.");
        System.out.println("Frame2 text is: " + frameTwoTextValue.getText());
    }
}
