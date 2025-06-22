package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DroppablePage extends BasePage {
    //locatori specifici;
    private By pageTitle = By.xpath("//h1[@class]");
    private By draggableElement = By.id("draggable");
    private By droppableElement = By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']");
    //public By droppableElement = By.id("droppable");

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Droppable", "Page is not loaded properly");
    }

    public void pickAndDropElement() {
        elementMethods.scrollPageDown("300");
        String initialTargetText = elementMethods.getTextFromElement(droppableElement); //am salvat textul de pe element intr-un string;
        elementMethods.pickAndDropElement(draggableElement,droppableElement);
        //elementMethods.setWait(1000L);
        Assert.assertNotEquals(elementMethods.getTextFromElement(droppableElement), initialTargetText,
                "Initial text is the same with actual text after element dropped");
        System.out.println("Initial text is: " + initialTargetText + " Text after successful drop: " +
                elementMethods.getTextFromElement(droppableElement));
    }


}