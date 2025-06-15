package Homeworks.pomExample.tests;

import Homeworks.pomExample.pages.CartPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CartTest extends Homeworks.pomExample.tests.BaseTest {

    @Test
    public void addItemToCart(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        cartPage.acceptCookiesPolicy();
        cartPage.addItemToCart("rochii");
    }
}