package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonPage extends BasePage{

    // Locatori specifici paginii
    private By alertsSubMenu = By.xpath("//span[text()='Alerts']");

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(driver.getTitle(), "DEMOQA", "Page is not loaded properly");
    }

    // Indendificam submeniul dorit si facem click pe el
    public void choseSubMenu(){
        driver.findElement(alertsSubMenu).click();
    }
}
