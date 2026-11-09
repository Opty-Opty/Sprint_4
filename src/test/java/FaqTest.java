import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

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
                { 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "chrome" },
                { 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "chrome" },
                { 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "chrome" },
                { 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "chrome" },
                { 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "chrome" },
                { 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "chrome" },
                { 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "chrome" },
                { 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "chrome" },

                { 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "firefox" },
                { 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "firefox" },
                { 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "firefox" },
                { 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "firefox" },
                { 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "firefox" },
                { 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "firefox" },
                { 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "firefox" },
                { 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "firefox" }
        });
    }

    @Before
    public void setUp() {
        initDriver(browser);
    }

    @Test
    public void testFaqSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookiesIfPresent();

        mainPage.scrollToFaq();
        mainPage.clickFaqQuestion(index);
        String actualAnswer = mainPage.getFaqAnswerText(index);

        Assert.assertTrue("Текст ответа на вопрос №" + index + " не совпадает с ожидаемым!",
                actualAnswer.contains(expectedAnswer));
    }
}
