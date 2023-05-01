package testsHomeWork9;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

    public class ParamTest {
        @BeforeEach
        void openPage() {
            Selenide.open("http://mirinstrumenta63.ru");
        }
        static Stream<Arguments> sideMenuTest() {
            return Stream.of(
                    Arguments.of(List.of("Сварочная проволока", "Сварочные аппараты",
                            "Сварочные электроды", "Сварочные маски",
                            "Сварочные принадлежности","Аппараты для сварки ПВХ труб")));
        }
        @MethodSource
        @ParameterizedTest(name = "тестирование отображения раздела в боковом меню")
        void sideMenuTest(List<String> result) {
            $$(".cat-menu__el-link").findBy(Condition.text("Сварочное оборудование")).click();
            $$(".js-category-link").filter(visible)
             .shouldHave(CollectionCondition.texts(result));
        }
        @ValueSource(strings= {
                "Контакты", "Доставка", "Оплата", "Возврат товара"} )

        @ParameterizedTest(name = "тестирование раздела {0}")
        void headButtonsTest( String razdel) {
            $$x("//ul//li//a").findBy(Condition.text(razdel)).click();
            $("h1").shouldHave(Condition.text(razdel));
        }
        @CsvSource (value = {
                "Электродрель, Поиск по запросу Электродрель",
                "Лестницы,Поиск по запросу Лестницы"} )

        @ParameterizedTest(name = "тестирование выдачи при поиске: {0} ")
        void searchTest (String razdel, String result) {
            $("[placeholder='Поиск по каталогу']").setValue(razdel).pressEnter();
            $("h1").shouldHave(Condition.text(result));
       }
    }

