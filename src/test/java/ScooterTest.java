import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AboutUserPage;
import pages.MainPage;
import pages.RentPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ScooterTest {
    private WebDriver driver;

    private final String browser;
    private final String button;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    public ScooterTest(String browser, String button, String firstName, String lastName, String address,
                       String metro, String phone, String date, String period, String color, String comment) {
        this.browser = browser;
        this.button = button;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тест {index}: Браузер {0} | Кнопка {1} | Цвет {9}")
    public static Collection<Object[]> getOrderData() {
        return Arrays.asList(new Object[][] {
                // Google Chrome тесты)
                {"chrome", "top", "Ян", "Ли", "г. Москва, Арбат, 5", "Арбатская", "79991112233", "25.11.2026", "сутки", "чёрный жемчуг", "Быстрее"},
                {"chrome", "top", "Константин", "Иванов", "г. Москва, Мира, 12", "Авиамоторная", "79017654321", "01.12.2026", "двое суток", "серая безысходность", "У двери"},
                {"chrome", "bottom", "Ян", "Ли", "г. Москва, Арбат, 5", "Арбатская", "79991112233", "25.11.2026", "сутки", "чёрный жемчуг", "Быстрее"},
                {"chrome", "bottom", "Константин", "Иванов", "г. Москва, Мира, 12", "Авиамоторная", "79017654321", "01.12.2026", "двое суток", "серая безысходность", "У двери"},
                // Mozilla Firefox тесты
                {"firefox", "top", "Ян", "Ли", "г. Москва, Арбат, 5", "Арбатская", "79991112233", "25.11.2026", "сутки", "чёрный жемчуг", "Быстрее"},
                {"firefox", "top", "Константин", "Иванов", "г. Москва, Мира, 12", "Авиамоторная", "79017654321", "01.12.2026", "двое суток", "серая безысходность", "У двери"},
                {"firefox", "bottom", "Ян", "Ли", "г. Москва, Арбат, 5", "Арбатская", "79991112233", "25.11.2026", "сутки", "чёрный жемчуг", "Быстрее"},
                {"firefox", "bottom", "Константин", "Иванов", "г. Москва, Мира, 12", "Авиамоторная", "79017654321", "01.12.2026", "двое суток", "серая безысходность", "У двери"}
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
    public void testOrderFlow() {
        MainPage mainPage = new MainPage(driver);
        AboutUserPage aboutUserPage = new AboutUserPage(driver);
        RentPage rentPage = new RentPage(driver);

        mainPage.open();
        mainPage.acceptCookiesIfPresent();

        if ("top".equals(button)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        aboutUserPage.fillCustomerForm(firstName, lastName, address, metro, phone);
        rentPage.fillRentForm(date, period, color, comment);

        Assert.assertTrue("Окно подтверждения заказа не открылось!",
                rentPage.getModalHeaderText().contains("заказ"));

    }

        @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
