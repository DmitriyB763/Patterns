package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;

public class cardOrderTests {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());  // https://ru.selenide.org/documentation/reports.html  - подробнее про отчет
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }
@Test
    public void successfulCardOrder(){
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $x("//span[@data-test-id=\"city\"]//input").setValue(DataGenerator.generateCity("ru"));
    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    $("[data-test-id='date'] input").setValue(DataGenerator.generateDate(4));
        $x("//*[@name=\"name\"]").setValue(DataGenerator.generateName("ru"));
        $x("//*[@data-test-id=\"phone\"]//input").setValue(DataGenerator.generatePhone("ru"));
        $x("//*[@data-test-id=\"agreement\"]").click();
        $(withText("Запланировать")).click();
        $x("//*[@data-test-id=\"success-notification\"]").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15));

    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    $("[data-test-id='date'] input").setValue(DataGenerator.generateDate(7));
    $(withText("Запланировать")).click();
    //$x("//*[@data-test-id=\"replan-notification\"]").shouldHave(Condition.text("Необходимо подтверждение"));
    $(withText("Перепланировать")).click();
    $x("//*[@data-test-id=\"success-notification\"]").shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(15));
    }
}
