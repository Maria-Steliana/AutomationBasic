package Homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HMCheckbox {

    WebDriver driver;

    @Test
    public void hmCheckbox(){
        openBrowser();
        choseMenu();
        choseSubMenu();

        // Expandăm nodurile pentru a avea acces la toate submeniurile
        expandNode("Home");
        expandNode("Desktop");
        expandNode("Documents");
        expandNode("Downloads");
        expandNode("WorkSpace");
        expandNode("Office");

        // Selectam checkbox-urile dorite
        //selectCheckbox("Home");
        selectCheckbox("Office");
        selectCheckbox("Desktop");


    }

    // Metoda care deschide browserul
    public void openBrowser(){
        driver = new ChromeDriver(); // Navigam catre pagina website-ului
        driver.get("https://demoqa.com/"); // Facem fereastra browser-ului maxima
        driver.manage().window().maximize(); // Facem fereastra maxima
    }

    // Identificam meniul dorit si facem click pe el
    public void choseMenu(){
        WebElement elementsMenu = driver.findElement(By.xpath("//h5[text()='Elements']"));
        scrollToElement(elementsMenu);
        elementsMenu.click(); // Actionam butonul din meniul de mai sus
    }

    // Facem o metoda care sa faca scroll
    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Indendificam submeniul dorit si facem click pe el
    public void choseSubMenu(){
        WebElement webTableSubMenu = driver.findElement(By.xpath("//span[text()='Check Box']"));
        webTableSubMenu.click();
    }

    // Metoda care dă click pe săgeata de expand asociată unui nod din lista de checkbox-uri.
    // Nodurile (ex: "Home", "Documents", "Downloads") pot fi expandate pentru a afișa sub-elementele lor.
    // Căutăm elementul care conține numele nodului (ex: <span class='rct-title'>Home</span>)
    // și urcăm în structura până la containerul părinte ('rct-text'), care conține și butonul de expand.
    // Din acel container, selectăm butonul <button> care reprezintă săgeata de expand/collapse.
    public void expandNode(String nodeName){
        WebElement expandButton = driver.findElement(By.xpath("//span[@class='rct-title' and text()='" + nodeName + "']/ancestor::span[@class='rct-text']/button"));
        expandButton.click();
    }


    // Metodă care bifează checkbox-urile dintr-un nod specificat.
    // Dacă nodul specificat este un nod părinte (ex: Home), se vor bifa toate subcheckbox-urile asociate.
    // Dacă se selectează un nod copil (ex: Desktop sau Office), doar checkbox-urile corespunzătoare acelei secțiuni vor fi bifate.
    public void selectCheckbox(String nodeName) {
        WebElement node = driver.findElement(By.xpath("//span[@class='rct-title' and text()='" + nodeName + "']"));
        // Urcăm la părintele 'rct-text', apoi căutăm fratele 'rct-checkbox'
        WebElement checkbox = node.findElement(By.xpath("preceding-sibling::span[@class='rct-checkbox']"));
        checkbox.click();
    }


}