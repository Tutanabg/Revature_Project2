package com.revature.steps;

import com.revature.pages.NavbarPage;
import com.revature.runners.CoffeeShopRunner;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class NavBarStepImpl {
    NavbarPage navbarPage = CoffeeShopRunner.navbarPage;
    WebDriver driver = CoffeeShopRunner.driver;

    //user clicks on menu
    @When("the user clicks on menu button")
    public void the_user_clicks_on_menu_button() {
        driver.manage().window().maximize();
        navbarPage.menuButton.click();
    }
    @Then("the user should be on menu page")
    public void the_user_should_be_on_menu_page() {
        Assertions.assertTrue(navbarPage.menuHead.getText().equals("Menu"));
    }

    @Given("the user is on the menu")
    public void the_user_is_on_the_menu() {
        driver.get("http://localhost:4200/menu");
    }
    @When("the user clicks on the about us button")
    public void the_user_clicks_on_the_about_us_button() {
        navbarPage.aboutUs.click();
    }
    @Then("the user should be on the about us page")
    public void the_user_should_be_on_the_about_us_page() {
        Assertions.assertTrue(navbarPage.aboutUsHead.getText().equals("About Us"));
    }


    @When("the user clicks on the contact us button")
    public void the_user_clicks_on_the_contact_us_button() {
        navbarPage.contactUs.click();
    }
    @Then("the user should be on the contact us button")
    public void the_user_should_be_on_the_contact_us_button() {
        Assertions.assertTrue(navbarPage.contactUsHead.getText().equals("Contact Us"));
    }


    @Given("the user is not logged in")
    public void the_user_is_not_logged_in() {
        Assertions.assertTrue(navbarPage.navLogin.isDisplayed());
    }
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        navbarPage.navLogin.click();
    }

    @Given("the user is on the menu page")
    public void the_user_is_on_the_menu_page() {
        driver.get("http://localhost:4200/menu");
    }
    @When("the user clicks on the cart button")
    public void the_user_clicks_on_the_cart_button() {
        navbarPage.navCart.click();
    }
    @Then("the user is on the cart page")
    public void the_user_is_on_the_cart_page() {
        Assertions.assertTrue(navbarPage.cartHead.getText().equals("Your Cart"));
    }
}
