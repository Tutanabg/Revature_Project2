package com.revature.steps;

import com.revature.pages.LoginPage;
import com.revature.runners.CoffeeShopRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginStepImpl {
    public static LoginPage coffeeShop = CoffeeShopRunner.CoffeeShop;
    public static WebDriver driver = CoffeeShopRunner.driver;

    @Given("the user is on about us page")
    public void the_user_is_on_about_us_page() {
        driver.get("http://localhost:4200/about");
    }
    @When("the user clicks on the login button")
    public void the_user_clicks_on_the_login_button() {
        coffeeShop.login.click();
    }
    @Then("the user is on login page")
    public void the_user_is_on_login_page() {
        Assert.assertEquals("Login",coffeeShop.loginHeader.getText());
    }



    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("http://localhost:4200/login");
    }

    //Valid
    @When("the user enters a valid username and password")
    public void the_user_enters_a_valid_username_and_password() {
        coffeeShop.userInput.sendKeys("coffeequeen92");
        coffeeShop.passwordInput.sendKeys("Password1");
    }
    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        coffeeShop.loginBtn.click();
    }
    @Then("the user sign in, then logout button shows up")
    public void the_user_sign_in_then_logout_button_shows_up() {
        Assert.assertTrue(coffeeShop.loginDiv.findElement(By.xpath("/html/body/app-root/nav/div/div/div/button")).isEnabled());
    }

    //inValid
    @When("the user enters an invalid username or password")
    public void the_user_enters_an_invalid_username_or_password() {
        coffeeShop.userInput.sendKeys("asdasesaease");
        coffeeShop.passwordInput.sendKeys(" ");
    }
    @Then("the error message shows up and the invalid Texts are removed")
    public void the_error_message_shows_up_and_the_invalid_texts_are_removed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals("The username or password you entered does not match the records in our database. Please try again!",driver.switchTo().alert().getText());
            Assert.assertEquals("",coffeeShop.userInput.getText());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("the user clicks on the Forget Your Password?")
    public void the_user_clicks_on_the_forget_your_password() {
        coffeeShop.forgetPassword.click();
    }
    @Then("the update account window shows up, the submit button is disable")
    public void the_update_account_window_shows_up_the_submit_button_is_disable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.attributeContains(coffeeShop.updateAccountDiv,"class", "modal fade show"));
            Assert.assertEquals("block",coffeeShop.updateAccountDiv.getCssValue("display"));
            Assert.assertFalse(coffeeShop.updateSubmitBtn.isEnabled());
            coffeeShop.updateCloseBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("the user enters an valid information")
    public void the_user_enters_an_valid_information() {
        coffeeShop.inputFirstName2.sendKeys("asfasf");
        coffeeShop.inputLastName2.sendKeys("asfasf");
        coffeeShop.inputPhoneNumber2.sendKeys("1234567890");
        coffeeShop.inputEmail2.sendKeys("asfa@gma.com");
        coffeeShop.inputUsername2.sendKeys("coffeequeen92");
        coffeeShop.inputPassword2.sendKeys("Password1");
        coffeeShop.inputConfirmPassword2.sendKeys("Password1");
    }
    @Then("the submit button is enable")
    public void the_submit_button_is_enable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(coffeeShop.updateSubmitBtn));
            Assert.assertTrue(coffeeShop.updateSubmitBtn.isEnabled());
            coffeeShop.updateCloseBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //LogOut
    @When("the user clicks on the logout button")
    public void the_user_clicks_on_the_logout_button() {
        coffeeShop.loginDiv.findElement(By.xpath("/html/body/app-root/nav/div/div/div/button")).click();
    }
    @Then("the user logout, the user is on landing page")
    public void the_user_logout_the_login_button_shows_up() {
        Assert.assertEquals("Welcome to our Coffee shop!",coffeeShop.landingPageHeader.getText());
    }



    @When("the user clicks on the register now button")
    public void the_user_clicks_on_the_register_now_button() {
        coffeeShop.registerBtn.click();
    }
    @Then("the create account window shows up, the register button is disable")
    public void the_create_account_window_shows_up() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.attributeContains(coffeeShop.accountDiv,"class", "modal fade show"));
            Assert.assertEquals("block",coffeeShop.accountDiv.getCssValue("display"));
            Assert.assertFalse(coffeeShop.formSubmitBtn.isEnabled());
            coffeeShop.formCloseBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Given("the user is on the register window")
    public void the_user_is_on_the_register_window() {
        coffeeShop.registerBtn.click();
    }
    @When("the user clicks on the close button")
    public void the_user_clicks_on_the_close_button() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(coffeeShop.formCloseBtn));
            coffeeShop.formCloseBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Then("the create account window disappear")
    public void the_create_account_window_disappear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.attributeContains(coffeeShop.accountDiv,"style", "display: none"));
            Assert.assertEquals("none",coffeeShop.accountDiv.getCssValue("display"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @When("the user enters all valid inputs")
    public void the_user_enters_all_valid_inputs() {
        coffeeShop.inputFirstName.sendKeys("asfasf");
        coffeeShop.inputLastName.sendKeys("asfasf");
        coffeeShop.inputPhoneNumber.sendKeys("1234567890");
        coffeeShop.inputEmail.sendKeys("asfa@gma.com");
        coffeeShop.inputStreet.sendKeys("2223ese");
        coffeeShop.inputCity.sendKeys("abc");
        coffeeShop.inputZipCode.sendKeys("78758");
        coffeeShop.inputUsername.sendKeys("dayday");
        coffeeShop.inputPassword.sendKeys("Password1");
        coffeeShop.inputConfirmPassword.sendKeys("Password1");
    }
    @Then("the Register button is enabled")
    public void the_new_account_be_created_and_the_user_sign_in() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.elementToBeClickable(coffeeShop.formSubmitBtn));
            Assert.assertTrue(coffeeShop.formSubmitBtn.isEnabled());
            coffeeShop.formCloseBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
