package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;


public class PlanetaTests {

    @Description("Проверка смены цвета ссылок при наведении")
    @Feature("Planeta main menu")
    @Test
    public void LinksChangeColor() {
        open("https://planeta.tc/ekb");
        ElementsCollection mainMenu = $$(".NavigationMenuItem__inner");

        // 1. Проверяет последовательно все пункты меню на смену цвета текста пунктов меню при наведении на него (текст) курсора мыши. Демо: Задание_1_1.gif;
        for (SelenideElement menuItem : mainMenu) {
            menuItem.shouldHave(cssValue("color", "rgb(51, 51, 51)"));
            menuItem.hover();
            menuItem.shouldHave(cssValue("color", "rgb(236, 0, 140)"));
        }
    }

    @Description("Проверка прокрутки плашек")
    @Feature("Planeta main menu")
    @Test
    public void NavigationMenuClick() {
        open("https://planeta.tc/ekb");
        ElementsCollection menuItems = $$(".NavigationMenuItem");

        menuItems.findBy(text("ПРОДУКТЫ")).click();

        //2.    После клика на первый пункт меню проверяет последовательно все пункты меню на прокрутку плашки этого меню при наведении на него (пункт) курсора мыши. Демо: Задание_1_2.gif;
        //3.    Проверяет, что все плашки меню содержат пункты "Сравнить продукты", "Пополнить баланс";

        for (SelenideElement MenuPad : menuItems) {
                MenuPad.hover();
                $(".PanesItem.PanesItem--active[data-id='"+MenuPad.getAttribute("data-target")+"']").shouldBe(visible).shouldHave(cssValue("display", "block")).shouldHave(text("Сравнить продукты")).shouldHave(text("Пополнить баланс"));

        }
    }

    @Description("Проверка плашек на закрывание")
    @Feature("Planeta main menu")
    @Test

    public void PanesMenuClose () {
        open("https://planeta.tc/ekb");
        ElementsCollection menuItems = $$(".NavigationMenuItem");

        //4.    Проверяет, что для всех пунктов меню работает кнопка закрытия плашки, то есть закрывает её.

        for (SelenideElement MenuPad : menuItems){
           MenuPad.click();
            $(".PanesItem.PanesItem--active[data-id='"+MenuPad.getAttribute("data-target")+"']").shouldBe(visible).shouldHave(cssValue("display", "block"));
            $(".Navigation__close").click();
        }
    }
}
