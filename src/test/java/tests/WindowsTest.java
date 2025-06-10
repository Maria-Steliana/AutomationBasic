import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WindowsTest {

    WebDriver driver;

    @Test
    public void WindowsTest(){
        openBrowser();
        choseMenu();
        choseSubMenu();
        interactWithNewTab();
        interactWithNewWindow();
        interactWithNewWindowMessage();
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
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Browser Windows']"));
        alertsSubMenu.click();
    }

    // Deschidem primul tab de New Tab
    public void interactWithNewTab(){
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();
        // Declaram o lista de ferestre
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsList.get(1)); // ne mutam pe tabul nou
        WebElement tabTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page"; // asta este textul care apare in inspect pe acea noua pagina, in dreptul id-ului
        Assert.assertEquals(tabTextValue.getText(),expectedText, "Text is not displayed properly.");
        driver.close(); // close - inchide fereastra, quit - inchide intreaga instanta
        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewWindow(){
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();
        // Declaram o lista de ferestre
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsList.get(1)); // ne mutam pe tabul nou
        WebElement windowTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page"; // asta este textul care apare in inspect pe acea noua pagina, in dreptul id-ului
        Assert.assertEquals(windowTextValue.getText(),expectedText, "Text is not displayed properly.");
        driver.close(); // close - inchide fereastra, quit - inchide intreaga instanta
        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewWindowMessage(){
        WebElement newWindowMessage = driver.findElement(By.id("messageWindowButton"));
        newWindowMessage.click();
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles()); // Declaram o lista de ferestre
        if (windowsList.size() > 1 ){
            System.out.println("A new window is successfully opened.");
        }
        else{
            System.out.println("New window can't be opened.");
        }
        driver.switchTo().window(windowsList.get(1));
        driver.close();
        driver.switchTo().window(windowsList.get(0));
    }

    public void closeBrowser(){
        driver.quit();
    }
}
