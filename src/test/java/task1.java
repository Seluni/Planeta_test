import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class task1 {
    @Description("Проверка пунктов меню на смену цвета при наведении на них")
    @Feature("PlanetaUrlCheck")
    @Test
    public void LinkChangeColor(){
        open("https://planeta.tc/ekb");
        ElementsCollection collection = $$(".NavigationMenuItem__inner");

        for (SelenideElement menuItem : collection) {
            menuItem.shouldHave(cssValue("color", "rgb(51, 51, 51)"));
            menuItem.hover();
            menuItem.shouldHave(cssValue("color", "rgb(236, 0, 140)"));
        }
    }

    @Description("Проверка всех пунктов меню")
    @Feature("PlanetaNavigateMenu")
    @Test
    public void NavigationMenuClick() throws InterruptedException {
        open("https://planeta.tc/ekb");
        ElementsCollection MenuItems = $$(".NavigationMenuItem");
        ElementsCollection MenuPanes = $$(".NavigationPanes");

        MenuItems.findBy(text("ПРОДУКТЫ")).click();

        for (SelenideElement MenuPad : MenuItems) {
            for (SelenideElement Panes : MenuPanes) {
                MenuPad.hover();
                Thread.sleep(3000);
                Panes.shouldBe(visible);
                Panes.shouldHave(text("Сравнить продукты"));
                Panes.shouldHave(text("Пополнить баланс"));
            }
        }

    }

    @Description("Проверка плашек на закрывание")
    @Feature("PlanetaPanesClose")
    @Test

    public void PanesMenuClose () throws InterruptedException {
        open("https://planeta.tc/ekb");
        ElementsCollection Closebutton = $$(".Navigation__close");
        ElementsCollection MenuItems = $$(".NavigationMenuItem");
        ElementsCollection MenuPanes = $$(".NavigationPanes");

        for (SelenideElement MenuPad : MenuItems) {
            for (SelenideElement Panes : MenuPanes) {
                for (SelenideElement CloseButton : Closebutton) {
                    MenuPad.click();
                    Thread.sleep(3000);
                    Panes.shouldBe(visible).shouldHave(cssValue("display", "block"));
                    CloseButton.click();
                    Panes.shouldBe(hidden).shouldHave(cssValue("display", "none"));

                }
            }
        }
    }
}
