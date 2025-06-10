package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;


public class PracticeFormTest extends BaseTest{

    @Test
    public void practiceFormTest(){

//        driver = new ChromeDriver();
//        // Navigam catre pagina website-ului
//        driver.get("https://demoqa.com/");
//
//        // Facem fereastra browser-ului maxima
//        driver.manage().window().maximize();

        // Identificam meniul dorit si facem click pe el
        WebElement FromMenu = driver.findElement(By.xpath("//h5[text()='Forms']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", FromMenu); // cu aceste doua linii de cod se face scroll pana
        FromMenu.click(); // Actionam butonul din meniul de mai sus

        // Indendificam submeniul dorit si facem click pe el
        WebElement PracticeFormSubMenu = driver.findElement(By.xpath("//span[text()='Practice Form']"));
        PracticeFormSubMenu.click();

        // Identificam elementele din formular si facem actiuni corespunzatoare pe fiecare

        // Completam field-ul de first name
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        String firstNameText = "Maria-Steliana";
        firstNameField.sendKeys(firstNameText);

        // Completam field-ul de last name
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        String lastNameText = "Carlan";
        lastNameField.sendKeys(lastNameText);

        // Completam field-ul de email
        WebElement emailField = driver.findElement(By.id("userEmail"));
        String emailText = "test@gmail.com";
        emailField.sendKeys(emailText);
        js.executeScript("window.scrollBy(0,500)"); // scroll 500 pixeli

        // Selectam gender-ul persoanei
        WebElement genderMale = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        WebElement genderFemale = driver.findElement(By.xpath("//label[@for='gender-radio-2']"));
        WebElement genderOther = driver.findElement(By.xpath("//label[@for='gender-radio-3']"));
        String genderValueText = "Female";
        List<WebElement> genderList = List.of(genderMale, genderFemale, genderOther); // o alta metoda de a declara o lista
        for (int i = 0; i < genderList.size(); i++) {
            if (genderList.get(i).getText().equals(genderValueText)) {
                genderList.get(i).click();
                break;
            }
        }

        // Completam field-ul cu numarul de telefon
        WebElement mobilePhoneField = driver.findElement(By.id("userNumber"));
        String mobilePhoneText = "0712341234";
        mobilePhoneField.sendKeys(mobilePhoneText);

        // Completam field-ul cu data nasterii
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthInput.click();
        WebElement monthOfBirthElement = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        Select selectMonth = new Select(monthOfBirthElement);
        String monthValueText = "October";
        selectMonth.selectByVisibleText(monthValueText);
        WebElement yearOfBirthElement = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        Select selectYear = new Select(yearOfBirthElement);
        String yearValueText = "1998";
        selectYear.selectByVisibleText(yearValueText);
        // alegem ziua nasterii dintr-o lista de zile posibile
        List<WebElement> dayOfBirthList = driver.findElements(By.xpath("//div[contains(@class,'react-datepicker__day')]"));
        String dayValueText = "26";
        for (int i = 0; i < dayOfBirthList.size(); i++) {
            if (dayOfBirthList.get(i).getText().equals(dayValueText)) {
                dayOfBirthList.get(i).click();
                break;
            }
        }

        // Completam field-ul de subiecte
        WebElement subjectInputElement = driver.findElement(By.id("subjectsInput"));
        String mathsSubjectText = "Maths";
        subjectInputElement.sendKeys(mathsSubjectText);
        subjectInputElement.sendKeys(Keys.ENTER);
        String physicsSubjectText = "Physics";
        subjectInputElement.sendKeys(physicsSubjectText);
        subjectInputElement.sendKeys(Keys.ENTER);
        js.executeScript("window.scrollBy(0,200)"); // asa facem scroll

        // Selectam check-boxurile de hobby-uri
        WebElement sportHobbyElement = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
        WebElement readingHobbyElement = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']"));
        WebElement musicHobbyElement = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-3']"));
        List<WebElement> hobbiesList = List.of(sportHobbyElement, musicHobbyElement, readingHobbyElement);
        String sportHobbyText = "Sports";
        String readingHobbyText = "Reading";
        String musicHobbyText = "Music";
        List<String> hobbyValueTextList = List.of(sportHobbyText, readingHobbyText, musicHobbyText);
        for (String hobby : hobbyValueTextList) {
            for (int i = 0; i < hobbiesList.size(); i++) {
                if (hobbiesList.get(i).getText().equals(hobby)) {
                    hobbiesList.get(i).click();
                    break;
                }
            }
        }

        // facem scroll
        js.executeScript("window.scrollBy(0,200)");

        // Uploadez o poza, un fisier pe site, pe care l-am downloadat si am dat un filepath
        WebElement uploadFileElement = driver.findElement(By.id("uploadPicture"));
        String pictureFileText = "TestImage.png";
        String pictureFilePath = "src/test/resources/pictures/" + pictureFileText;
        File file = new File(pictureFilePath);
        uploadFileElement.sendKeys(file.getAbsolutePath());

        // Identific elementul "Address Field" de pe pagina, dau click pe el si introduc textul "Adresa domiciliu 1"
        WebElement addressField = driver.findElement(By.id("currentAddress"));
        String addressValueText = "Adresa domiciliu 1";
        addressField.sendKeys(addressValueText);
        // Aleg tara - statul din lista disponibila prin tastarea textului "NCR" si apoi apas tasta Enter
        WebElement stateInputElement = driver.findElement(By.id("react-select-3-input"));
        String stateValueText = "NCR";
        stateInputElement.sendKeys(stateValueText);
        stateInputElement.sendKeys(Keys.ENTER);

        // alegem orasul de pe formular
        WebElement cityInputElement = driver.findElement(By.id("react-select-4-input"));
        String cityValueText = "Delhi";
        cityInputElement.sendKeys(cityValueText);
        cityInputElement.sendKeys(Keys.ENTER);

        // apasam butonu Submit
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Validam tabelul cu datele de intrare folosite
        // Facem un hashmap cu expected values

        HashMap<String, String> expectedValues = new HashMap<>();
        expectedValues.put("Student Name", firstNameText + " " + lastNameText);
        expectedValues.put("Student Email", emailText);
        expectedValues.put("Gender", genderValueText);
        expectedValues.put("Mobile", mobilePhoneText);
        expectedValues.put("Date of Birth", dayValueText + " " + monthValueText + "," + yearValueText);
        expectedValues.put("Subjects", mathsSubjectText + ", " + physicsSubjectText);
        expectedValues.put("Hobbies", sportHobbyText + ", " + readingHobbyText + ", " + musicHobbyText);
        expectedValues.put("Picture", pictureFileText);
        expectedValues.put("Address", addressValueText);
        expectedValues.put("State and City", stateValueText + " " + cityValueText);

        // declaram listele cu valorile actuale din tabel
        List<WebElement> submitTablesKeys = driver.findElements(By.xpath("//tbody//td[1]"));
        List<WebElement> submitTablesValues = driver.findElements(By.xpath("//tbody//td[2]"));
        HashMap<String, String> actualValues = new HashMap<>();
        for (int i = 0; i < submitTablesKeys.size(); i++) {
            actualValues.put(submitTablesKeys.get(i).getText(), submitTablesValues.get(i).getText());
        }

        // assert-ul este validare ca un anumit obiect e egal cu altul sau ca valorile dintre anumite obiecte sunt egale, sau ca un element exista
        Assert.assertEquals(actualValues, expectedValues, "Actual Values: " + actualValues + " are not equal/are not the same with the expected values: " + expectedValues);


    }


}
