package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {
    private final WebDriver driver;

    private final By dateInput = By.xpath(".//input[contains(@placeholder, 'Когда')]");
    private final By rentPeriodDropdown = By.className("Dropdown-arrow");
    private final By commentInput = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Заказать']");
    private final By confirmYesButton = By.xpath(".//div[contains(@class, 'Order_Modal')]//button[text()='Да']");

    private final By modalHeader = By.xpath("//div[contains(@class, 'ModalHeader')]");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRentForm(String date, String rentPeriod, String color, String comment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Заполнение поля даты
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(dateInput));
        dateField.click();
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);

        // 2. Открытие выпадающего списка периода
        wait.until(ExpectedConditions.elementToBeClickable(rentPeriodDropdown)).click();

        // 3. Выбор опции срока аренды самоката
        By rentOption = By.xpath(".//div[@class='Dropdown-option' and text()='" + rentPeriod + "']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(rentOption));
        option.click();

        // 4. Клик по чекбоксу цвета самоката
        if (color != null && !color.isEmpty()) {
            By colorCheckbox = By.xpath(".//label[contains(text(), '" + color + "')]/input");
            WebElement checkbox = driver.findElement(colorCheckbox);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }

        // 5. Заполнение комментария курьеру
        wait.until(ExpectedConditions.elementToBeClickable(commentInput)).sendKeys(comment);

        // 6. Скролл и клик «Заказать»
        WebElement orderBtn = wait.until(ExpectedConditions.presenceOfElementLocated(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", orderBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderBtn);

        // 7. Нажатие кнопки подтверждения «Да» для преодоления задержек в Chrome и Firefox
        WebElement yesBtn = wait.until(ExpectedConditions.presenceOfElementLocated(confirmYesButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesBtn);
    }

    public String getModalHeaderText() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(modalHeader)).getText();
    }
}
