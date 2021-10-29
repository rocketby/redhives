package tests.registration;

import allure.JiraIssue;
import allure.Layer;
import allure.Microservice;
import com.codeborne.selenide.Selenide;
import enums.Endpoint;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseTest;

@Owner("tat")
@Layer("web")
@Microservice("registration")
@DisplayName("Verify registration with wrong format of email")
public class RegistrationTests extends BaseTest {
    private RegistrationPage registrationPage;

    @BeforeEach
    @DisplayName("Open registration page")
    void setUpBeforeEach() {
        registrationPage = Selenide.open(Endpoint.REGISTRATION.getPath(), RegistrationPage.class);
    }

    @ParameterizedTest(name = "Unsuccessful registration (fill wrong email: {0})")
    @AllureId("5678")
    @ValueSource(strings = {"test", "test@", "test.com", "test@.com", "test@tt.11", "@gmail.com"})
    @JiraIssue("HOMEWORK-257")
    @Tags({@Tag("web"), @Tag("negative")})
    @DisplayName("Login attempts with bad format of email")
    void checkUnsuccessfulRegistration(String wrongEmail) {
        String password = "test123456";
        registrationPage
                .enterEmail(wrongEmail)
                .enterPassword(password)
                .markCheckboxAgreeWithTerms()
                .clickRegister()
                .shouldDisplayRegistrationPage();
    }
}
