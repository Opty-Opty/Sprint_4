package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutUserPage {
    private final WebDriver driver;

    private final By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");
    // Используем более точный и стабильный локатор для строки станции в списке
    private final By metroDropdownOption = By.className("select-search__option");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public AboutUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCustomerForm(String firstName, String lastName, String address, String metro, String phone) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);

        WebElement metroField = driver.findElement(metroStationInput);
        metroField.click();

        metroField.sendKeys(metro);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(metroDropdownOption));

        driver.findElement(metroDropdownOption).click();

        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }
}
