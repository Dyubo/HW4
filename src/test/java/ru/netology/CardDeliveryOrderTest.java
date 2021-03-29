package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    @Test
    void shouldSucсessOrder() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Майер Евгений");
        $("[data-test-id=phone] input").setValue("+79129855670");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(" [data-test-id=notification]").shouldBe(visible, Duration.ofMillis(15000))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + date));


    }

    @Test
    void shouldRegisterCardOrderWithCalendar() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Пермь");
        $("[data-test-id='date'] .input__icon").click();
        $(".calendar__arrow[ data-step='1']").click();
        $$(".calendar__layout .calendar__row .calendar__day").find(exactText("5")).click();
        $("[data-test-id=name] .input__control").setValue("Каменева Дарья");
        $("[data-test-id=phone] .input__control").setValue("+79127879339");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

    @Test
    void shouldRegisterCardOrderWithCity() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Ек");
        $$(".menu-item").find(exactText("Екатеринбург")).click();
        $("[data-test-id='date'] .input__icon").click();
        $(".calendar__arrow[ data-step='1']").click();
        $$(".calendar__layout .calendar__row .calendar__day").find(exactText("5")).click();
        $("[data-test-id=name] .input__control").setValue("Голеев Владимир");
        $("[data-test-id=phone] .input__control").setValue("+79127588557");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }


}
