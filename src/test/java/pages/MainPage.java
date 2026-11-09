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
    private final By cookieButton = By.id("rcc-confirm-button");

    // Логотип «Самокат» в шапке сайта
    private final By scooterLogo = By.className("Header_LogoScooter__3otGl");

    // Логотип «Яндекс» в шапке сайта
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    // Главный заголовок на странице
    private final By mainHeader = By.className("Home_Header__iE16B");

    // Верхняя кнопка «Заказать» в шапке сайта
    private final By topOrderButton = By.xpath(".//div[contains(@class, 'Header')]//button[text()='Заказать']");

    // Нижняя кнопка «Заказать» перед разделом FAQ
    private final By bottomOrderButton = By.xpath(".//div[not(contains(@class, 'Header'))]//button[text()='Заказать']");

    // Универсальный локатор родительского раздела FAQ по тексту заголовка
    private final By faqSection = By.xpath("//div[div[text()='Вопросы о важном']]");

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

        public void scrollToFaq() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(faqSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void clickFaqQuestion(int index) {
        WebElement question = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.id(questionIdPattern + index)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", question);
    }

    public String getFaqAnswerText(int index) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerIdPattern + index))).getText();
    }
}
