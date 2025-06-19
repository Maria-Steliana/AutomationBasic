package tests;

import org.testng.annotations.Test;
import pages.CommonPage;
import pages.HomePage;
import pages.WebTablePage;
import propertyUtility.PropertyUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.MenuConstants.ELEMENTS_MENU;
import static constants.SubMenuConstants.WEB_TABLE_SUBMENU;

public class WebTableMultipleInputsTest extends BaseTest {

    @Test
    public void webTableTest() {
        HomePage homePage = new HomePage(driver);
        homePage.isPageLoaded();
        homePage.goToDesiredMenu(ELEMENTS_MENU);
        CommonPage commonPage = new CommonPage(driver);
        commonPage.isPageLoaded();
        commonPage.goToDesiredSubMenu(WEB_TABLE_SUBMENU);
        WebTablePage webTablePage = new WebTablePage(driver);
        webTablePage.isPageLoaded();
        propertyUtility = new PropertyUtility("WebTableMultipleInputsTest");
        List<String> firstNameValue = propertyUtility.getPropertyAsList("firstName");
        List<String> lastNameValue = propertyUtility.getPropertyAsList("lastName");
        List<String> emailValue = propertyUtility.getPropertyAsList("email");
        List<String> ageValue = propertyUtility.getPropertyAsList("age");
        List<String> salaryValue = propertyUtility.getPropertyAsList("salary");
        List<String> departmentValue = propertyUtility.getPropertyAsList("department");
        for(int i=0; i < firstNameValue.size(); i++){
            Map<String, Object> webTableEntries = new HashMap<>();
            webTableEntries.put("firstName", firstNameValue.get(i));
            webTableEntries.put("lastName", lastNameValue.get(i));
            webTableEntries.put("email", emailValue.get(i));
            webTableEntries.put("age", ageValue.get(i));
            webTableEntries.put("salary", salaryValue.get(i));
            webTableEntries.put("department", departmentValue.get(i));
            webTablePage.webTablePageFlow(webTableEntries);
        }

        propertyUtility = new PropertyUtility("WebTableTest");
        Map<String,Object> webTableSingleEntries = propertyUtility.getAllProperties();
        webTablePage.webTablePageFlow(webTableSingleEntries);
    }
}