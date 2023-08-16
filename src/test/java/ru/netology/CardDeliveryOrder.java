package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryOrder {
    @Test
    void shouldCorrectFill() {
        open("http://localhost:9999");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Формат даты
        // Добавляем 3 дня к текущей дате
        LocalDate newDate = currentDate.plusDays(10);
        // Форматируем новую дату
        String formattedDate = newDate.format(dateFormatter);

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[placeholder='Город']").setValue("Воронеж");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(" ");
        form.$("[placeholder='Дата встречи']").setValue(formattedDate);
        form.$("[data-test-id=name] [name=name]").setValue("Иван Иванов-Петров");
        form.$("[data-test-id=phone] [type=tel]").setValue("+79123456789");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$(".button__content").click();
        $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(13));

    }
}
