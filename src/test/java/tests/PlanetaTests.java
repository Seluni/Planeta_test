package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static jdk.nashorn.internal.objects.NativeMath.random;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.Collection;
import java.util.Random;


public class PlanetaTests extends BaseTest{

//    private static final Object Random = java.util.Random;


    @Description("Проверка смены цвета ссылок при наведении")
    @Feature("Planeta main menu")
    @Test
    public void LinksChangeColor() {
        open(Configuration.baseUrl);
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
        open(Configuration.baseUrl);
        ElementsCollection menuItems = $$(".NavigationMenuItem");

        menuItems.findBy(text("ПРОДУКТЫ")).click();

        //2.    После клика на первый пункт меню проверяет последовательно все пункты меню на прокрутку плашки этого меню при наведении на него (пункт) курсора мыши. Демо: Задание_1_2.gif;
        //3.    Проверяет, что все плашки меню содержат пункты "Сравнить продукты", "Пополнить баланс";

        for (SelenideElement MenuPad : menuItems) {
                MenuPad.hover();
                $(".PanesItem.PanesItem--active[data-id='"+MenuPad.getAttribute("data-target")+"']")
                    .shouldBe(visible)
                    .shouldHave(cssValue("display", "block"))
                    .shouldHave(text("Сравнить продукты"))
                    .shouldHave(text("Пополнить баланс"));

        }
    }

    @Description("Проверка плашек на закрывание")
    @Feature("Planeta main menu")
    @Test

    public void PanesMenuClose () {
        open(Configuration.baseUrl);
        ElementsCollection menuItems = $$(".NavigationMenuItem");

        //4.    Проверяет, что для всех пунктов меню работает кнопка закрытия плашки, то есть закрывает её.

        for (SelenideElement MenuPad : menuItems){
           MenuPad.click();
            $(".PanesItem.PanesItem--active[data-id='"+MenuPad.getAttribute("data-target")+"']")
                .shouldBe(visible)
                .shouldHave(cssValue("display", "block"));
            $(".Navigation__close").click();
        }
    }

//    @Description("Проверка вложенных ссылок")
//    @Feature("Planeta second scenario")
//    @Test

//    public void CheckInnerUrls(){
//        open(Configuration.baseUrl);
//        ElementsCollection menuItems = $$(".NavigationMenuItem");
//
//        menuItems.findBy(text("ПРОДУКТЫ")).click();
//
////        ElementsCollection productItems = $$(".PanesItem.PanesItem--active[data-id='"+"NavigationProducts"+"'] > .NavigationPanesHeader.Grid.Grid--withGutterMedium > .Grid-cell.u-size1of3 > .NavigationList > .NavigationList__item");
//        ElementsCollection productItems = $$ (By.xpath("/html/body/div[6]/div/div[1]/div/div[1]/div/div[1]/div[1]/div[1]/div[1]"));
//        for (SelenideElement Products : productItems){
//            Products.click();
//
////            Products.shouldHave(text("Обратите внимание на раздел Акции. В нем содержится актуальная информация о специальных предложениях, скидках на оборудование и возможностях получения бонусных баллов — чатлов, с помощью которых вы можете компенсировать затраты по тарифам «Планеты» на интернет и другие услуги."));
//            menuItems.findBy(text("ПРОДУКТЫ")).click();
//        }
//    }

    @Description("Заявка через 'Включить планету'")
    @Feature("Planeta main menu")
    @Test
    public void CheckConnection(){

        final Random random = new Random();

        open(Configuration.baseUrl);
        $("#request > a").click();

        $("#address_street").setValue("Шау");
        $(".streetInputs > .autocompleteContainer > .autocompleteList").shouldHave(cssValue("display","block"));
        $(".streetInputs > .autocompleteContainer > .autocompleteList > .autocompletePoint.autocompletePoint").click();

        $("#address_house").setValue("93");
        $(".houseInputs > .autocompleteContainer > .autocompleteList").shouldHave(cssValue("display","block"));
        $(".houseInputs > .autocompleteContainer > .autocompleteList > .autocompletePoint.autocompletePoint").click();

        $("#step1_submit").submit();

        $("#step2_form > .wizard-connect").shouldHave(text("Ура!"));



        $("#connect_first_name").setValue("TestUser"+random.nextInt(4) +"");
        $("#connect_phone").setValue(""+ random.nextInt(999999999)+"");
        $("#step2_submit").submit();

        $(".wizard-right").shouldHave(text("Заявка принята"));

    }
}
