import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class task1 {
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
}
