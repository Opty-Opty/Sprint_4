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

    private final By metroDropdownOption = By.className("select-search__row");

    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public AboutUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCustomerForm(String firstName, String lastName, String address, String metro, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.elementToBeClickable(addressInput)).sendKeys(address);

        WebElement metroField = wait.until(ExpectedConditions.elementToBeClickable(metroStationInput));
        metroField.click();
        metroField.sendKeys(metro);

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(metroDropdownOption));
        option.click();

        wait.until(ExpectedConditions.elementToBeClickable(phoneInput)).sendKeys(phone);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }
}
