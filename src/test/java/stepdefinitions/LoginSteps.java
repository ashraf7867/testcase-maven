package stepdefinitions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import pages.LoginPage;
import java.io.File;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    String username;
    String password;

    public LoginSteps() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new File("inputData.json"));
        username = node.get("validUser").get("username").asText();
        password = node.get("validUser").get("password").asText();
    }

    @Given("I open the login page")
    public void i_open_the_login_page() {
        loginPage.initBrowser();
        loginPage.navigateTo("https://the-internet.herokuapp.com/login");
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.login(username, password);
    }

    @Then("I should see the secure area")
    public void i_should_see_the_secure_area() {
        assertTrue(loginPage.isLoggedIn());
        loginPage.closeBrowser();
    }
}