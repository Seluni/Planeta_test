package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class PlanetaTests extends BaseTest {

    @Tag("Regression")
    @TmsLink("KDA-2887")
    @TmsLink("KDA-2886")
    @Description("Simple test check something")
    @Feature("Planeta main menu")
    @Test
    public void when_DoingTestTask_Expect_ToDoItPerfect() {
        Selenide.open("https://planeta.tc/ekb");
        ElementsCollection mainMenu = $$(".NavigationMenuItem__inner");

        // 1. Проверяет последовательно все пункты меню на смену цвета текста пунктов меню при наведении на него (текст) курсора мыши. Демо: Задание_1_1.gif;
        for (SelenideElement aMainMenu : mainMenu) {
            aMainMenu.hover();
            aMainMenu.shouldHave(Condition.cssValue("color", "rgba(236, 0, 140, 1)"));
        }
    }
}
