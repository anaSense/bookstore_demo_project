package tests;

import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import tests.extensions.WithLogin;

import static io.qameta.allure.Allure.step;

public class BookstoreProfileTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    void checkEmptyProfilePageWithLoginTest() {
        step("Open authorized profile page", () -> {
            profilePage.openPage();
        });

        step("Check \"No rows\" text in empty table", () -> {
            profilePage.checkNoRowsTextInEmptyTable();
        });
    }
}
