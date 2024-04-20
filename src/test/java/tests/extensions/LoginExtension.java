package tests.extensions;

import models.AuthBodyModel;
import models.AuthResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.AuthSpec.authRequestSpec;
import static specs.AuthSpec.authResponseSpec;

public class LoginExtension implements BeforeEachCallback {

    private final String testUserName = "TestUserAnna123";
    private final String testPassword = "TestUserAnna123!";

    @Override
    public void beforeEach(ExtensionContext context) {
        step(format("Log in with credentials username = %s, " +
                "password = %s", testUserName, testPassword), () -> {
            AuthBodyModel model = new AuthBodyModel();
            model.setUserName(testUserName);
            model.setPassword(testPassword);

            AuthResponseModel response = given(authRequestSpec)
                    .body(model)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .spec(authResponseSpec)
                    .extract().as(AuthResponseModel.class);

            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID",
                    response.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("token",
                    response.getToken()));
            getWebDriver().manage().addCookie(new Cookie("expires",
                    response.getExpires()));
        });
    }

}
