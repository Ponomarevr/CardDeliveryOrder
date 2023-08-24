package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryOrder {
    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldCorrectFill() throws InterruptedException {
        open("http://localhost:9999");

        Thread.sleep(2000);

        String planningDate = generateDate(4, "dd.MM.yyyy");

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[placeholder='Город']").setValue("Воронеж");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[placeholder='Дата встречи']").setValue(planningDate);
        form.$("[data-test-id=name] [name=name]").setValue("Пиотр Покрышкин-Кожедуб");
        form.$("[data-test-id=phone] [type=tel]").setValue("+79123456789");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$(".button__content").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(13))
                .shouldBe(Condition.visible);
        Thread.sleep(5000);

    }
}
