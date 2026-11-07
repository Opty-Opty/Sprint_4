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

        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(dateInput));
        dateField.click();
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(rentPeriodDropdown)).click();

        By rentOption = By.xpath(".//div[@class='Dropdown-option' and text()='" + rentPeriod + "']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(rentOption));
        option.click();

        if (color != null && !color.isEmpty()) {
            By colorCheckbox = By.xpath(".//label[contains(text(), '" + color + "')]/input");
            WebElement checkbox = driver.findElement(colorCheckbox);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }

        wait.until(ExpectedConditions.elementToBeClickable(commentInput)).sendKeys(comment);

        WebElement orderBtn = wait.until(ExpectedConditions.presenceOfElementLocated(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", orderBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderBtn);

        WebElement yesBtn = wait.until(ExpectedConditions.presenceOfElementLocated(confirmYesButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesBtn);
    }

    public String getModalHeaderText() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(modalHeader)).getText();
    }
}
