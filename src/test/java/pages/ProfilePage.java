package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    SelenideElement noRowsLabel = $(".rt-noData");
    public void openPage() {
        open("/profile");
    }

    public void checkNoRowsTextInEmptyTable() {
        noRowsLabel.shouldBe(Condition.visible);
        noRowsLabel.shouldHave(Condition.text("No rows found"));
    }
}
