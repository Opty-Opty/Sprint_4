package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final String url = "https://qa-scooter.praktikum-services.ru/";

    // Кнопка согласия с куки «Да все привыкли»
    // Локатор элемента:
    private final By cookieButton = By.id("rcc-confirm-button");

    // Логотип «Самокат» в шапке сайта
    // Локатор элемента:
    private final By scooterLogo = By.className("Header_LogoScooter__3otGl");

    // Логотип «Яндекс» в шапке сайта
    // Локатор элемента:
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // Главный заголовок на странице («Учебный тренажер»)
    // Локатор элемента:
    private final By mainHeader = By.className("Home_Header__iE16B");

    // Верхняя кнопка «Заказать» в шапке сайта
    // Локатор элемента:
    private final By topOrderButton = By.xpath(".//div[contains(@class, 'Header')]//button[text()='Заказать']");

    // Нижняя кнопка «Заказать» перед разделом FAQ
    // Локатор элемента:
    private final By bottomOrderButton = By.xpath(".//div[not(contains(@class, 'Header'))]//button[text()='Заказать']");

    // Раздел «Вопросы о важном»
    // Локатор элемента:
    private final By faqSection = By.className("Home_FAQ__3u0I4");

    // Кнопки-стрелочки вопросов в FAQ
    private final String questionIdPattern = "accordion__heading-";

    // Панели с раскрывающимся текстом ответов в FAQ
    private final String answerIdPattern = "accordion__panel-";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void acceptCookiesIfPresent() {
        try {
            driver.findElement(cookieButton).click();
        } catch (Exception e) {
        }
    }

    public void clickTopOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    public void clickBottomOrderButton() {

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(bottomOrderButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickFaqQuestion(int index) {
        WebElement question = driver.findElement(By.id(questionIdPattern + index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", question);
    }

    public String getFaqAnswerText(int index) {
        return driver.findElement(By.id(answerIdPattern + index)).getText();
    }
}
