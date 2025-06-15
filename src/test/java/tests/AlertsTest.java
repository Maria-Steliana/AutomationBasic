package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;
import pages.CommonPage;
import pages.HomePage;

import java.time.Duration;

public class AlertsTest extends BaseTest{


    @Test
    public void AlertsTest(){
        HomePage homePage = new HomePage(driver);
        homePage.isPageLoaded();
        homePage.choseMenu();

        CommonPage commonPage = new CommonPage(driver);
        commonPage.isPageLoaded();
        commonPage.choseSubMenu();

        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.isPageLoaded();
        alertsPage.interactWithFirstAlert();
        alertsPage.interactWithTimerAlert();
        alertsPage.interactWithConfirmAlert("Cancel");
        alertsPage.interactWithPromptAlert("Maria");

    }

    // IMPLEMENTAREA BRUTA A TESTULUI

    // Metoda care deschide browserul
//    public void openBrowser(){
//        driver = new ChromeDriver(); // Navigam catre pagina website-ului
//        driver.get("https://demoqa.com/");
//        driver.manage().window().maximize(); // Facem fereastra maxima
//    }



}


