import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    private final int index;
    private final String expectedAnswer;
    private final String browser;

    public FaqTest(int index, String expectedAnswer, String browser) {
        this.index = index;
        this.expectedAnswer = expectedAnswer;
        this.browser = browser;
    }

        @Parameterized.Parameters(name = "Браузер: {2}, Вопрос №{0}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][] {
                { 0, "400 рублей", "chrome" },
                { 1, "один самокат", "chrome" },
                { 0, "400 рублей", "firefox" },
                { 1, "один самокат", "firefox" }
        });
    }

    @Before
    public void setUp() {
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void testFaqDropdownReturnsCorrectText() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookiesIfPresent();
        mainPage.clickFaqQuestion(index);

        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(2))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.id(String.format("accordion__panel-%d", index))));

        String actualAnswer = mainPage.getFaqAnswerText(index);
        Assert.assertTrue("Текст ответа под индексом " + index + " не содержит ожидаемую фразу!",
                actualAnswer.contains(expectedAnswer));
    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
