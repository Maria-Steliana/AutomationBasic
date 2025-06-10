import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebTableTest {

    WebDriver driver;
    public int initialTableSize = 0;
    String firstName = "Maria-Steliana";
    String lastName = "Carlan";
    String email = "test@gmail.com";
    String age = "26";
    String salary = "123456";
    String department = "Testing";


    @Test
    public void webTableTest(){
        openBrowser();
        choseMenu();
        choseSubMenu();
        getTableSize();
        clickToAddNewRecord();
        fieldFormValues();
        validateNewRecordsAreProperlyAdded();
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
        WebElement webTableSubMenu = driver.findElement(By.xpath("//span[text()='Web Tables']"));
        webTableSubMenu.click();
    }

    // Facem o metoda care sa ia numarul initial de randuri din tabel
    public int getTableSize(){
        List<WebElement> tableRowsList = driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[@class='rt-tr -odd' or @class='rt-tr -even']"));
        initialTableSize = tableRowsList.size();
        System.out.println("Numarul initial de randuri in tabel este: " + initialTableSize);
        return initialTableSize;
    }

    // Facem o metoda care face click pe adaugare rand nou (Add new row)
    public void clickToAddNewRecord(){
        WebElement clickToAddButton = driver.findElement(By.id("addNewRecordButton"));
        clickToAddButton.click();
    }

    // Facem o metoda care sa completeze toate campurile din formular
    public void fieldFormValues(){
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(lastName);

        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.sendKeys(email);

        WebElement ageField = driver.findElement(By.id("age"));
        ageField.sendKeys(age);

        WebElement salaryField = driver.findElement(By.id("salary"));
        salaryField.sendKeys(salary);

        WebElement departmentField = driver.findElement(By.id("department"));
        departmentField.sendKeys(department);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

    }

    // Facem o metoda care sa valideze ca am adaugat o intrare noua in tabel si sa verifice valorile pe care le-am dat
    public void validateNewRecordsAreProperlyAdded(){
        List<WebElement> tableRowsList = driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[@class='rt-tr -odd' or @class='rt-tr -even']"));
        Assert.assertTrue(tableRowsList.size() > initialTableSize, "There are no new entries in the table!, initial table size: " +
                initialTableSize + " is the same with actual table size: " + tableRowsList.size());
        String actualTableValues = tableRowsList.get(tableRowsList.size() - 1).getText(); // am scazut 1 pentru ca indexul incepe de la 0, deci avem elemente de la 0 la 3, care sunt 4 in total
        System.out.println("New record values are: " + actualTableValues);
        Assert.assertTrue(actualTableValues.contains(firstName), "First name value is not correct!, expected first name: " + firstName);
        Assert.assertTrue(actualTableValues.contains(lastName), "Last name value is not correct!, expected last name: " + lastName);
        Assert.assertTrue(actualTableValues.contains(email), "Email value is not correct!, expected email: " + email);
        Assert.assertTrue(actualTableValues.contains(age), "Age value is not correct!, expected age: " + age);
        Assert.assertTrue(actualTableValues.contains(salary), "Salary value is not correct!, expected salary: " + salary);
        Assert.assertTrue(actualTableValues.contains(department), "Department value is not correct!, expected department: " + department);

    }



}
