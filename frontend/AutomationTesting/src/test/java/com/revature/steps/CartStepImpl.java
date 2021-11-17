package com.revature.steps;

import com.revature.pages.CartPage;
import com.revature.pages.MenuPage;
import com.revature.pages.NavbarPage;
import com.revature.pages.OrderManagementPage;
import com.revature.runners.CoffeeShopRunner;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartStepImpl {

    public static MenuPage menuPage = CoffeeShopRunner.menuPage;
    public static CartPage cartPage = CoffeeShopRunner.cartPage;
    public static OrderManagementPage orderManagementPage = CoffeeShopRunner.orderManagementPage;
    public static NavbarPage navBar = CoffeeShopRunner.navbarPage;
    public static WebDriver driver = CoffeeShopRunner.driver;


    @Given("The User is not logged in")
    public void the_user_is_not_logged_in() {

        driver.navigate().refresh();
    }

    @Given("The User's cart is empty")
    public void the_user_s_cart_is_empty() {

        driver.navigate().refresh();
    }

    @Given("The User is on the Cart Page")
    public void the_user_is_on_the_cart_page() {

        navBar.navCart.click();
    }

    @Then("The Submit Order button will be replaced by a message prompting the User to sign in")
    public void the_submit_order_button_will_be_replaced_by_a_message_prompting_the_user_to_sign_in() {

        Assertions.assertEquals("Please sign in to place order.", cartPage.preSubmitPrompt.getText());
    }

    @Given("The User is logged in")
    public void the_user_is_logged_in() {

        navBar.navLogin.click();

        orderManagementPage.userInput.sendKeys("coffeequeen92");

        orderManagementPage.passwordInput.sendKeys("Password1");

        orderManagementPage.loginBtn.click();

        new WebDriverWait(driver, 10L);
    }

    @Then("The Submit Order button will replaced by a message prompting the User to add items to their cart")
    public void the_submit_order_button_will_replaced_by_a_message_prompting_the_user_to_add_items_to_their_cart() {

        Assertions.assertEquals("Cannot submit empty order.", cartPage.preSubmitPrompt.getText());
    }

    @Given("The User's cart is not empty")
    public void the_user_s_cart_is_not_empty() {

        navBar.menuButton.click();

        menuPage.selectExtraItems.click();

        menuPage.quantityExtraItem1.sendKeys("2");

        menuPage.addItemButton1.click();

        menuPage.addItemButton6.sendKeys(Keys.RETURN);
    }

    @When("The User clicks the Remove From Cart button")
    public void the_user_clicks_the_remove_from_cart_button() {

        cartPage.item2RemoveButton.click();
    }

    @Then("That item is no longer in the cart")
    public void that_item_is_no_longer_in_the_cart() {

        Assertions.assertTrue(driver.findElements(By.xpath("/html/body/app-root/app-cart/div/table[1]/tr[3]")).isEmpty());
    }

    @When("The User clicks the Increase Quantity button")
    public void the_user_clicks_the_increase_quantity_button() {

        cartPage.item1IncreaseQuantityButton.click();
    }

    @Then("The Quantity of that item increases by one")
    public void the_quantity_of_that_item_increases_by_one() {

        Assertions.assertEquals("x 2", cartPage.item1Quantity.getText());
    }

    @When("The User clicks the Decrease Quantity button")
    public void the_user_clicks_the_decrease_quantity_button() {

        cartPage.item1DecreaseQuantityButton.click();
    }

    @Then("The Quantity of that item decreases by one")
    public void the_quantity_of_that_item_decreases_by_one() {

        Assertions.assertEquals("x 1", cartPage.item1Quantity.getText());
    }

    @When("The User selects Delivery")
    public void the_user_selects_delivery() {

        JavascriptExecutor js = ((JavascriptExecutor) driver);

        js.executeScript("arguments[0].scrollIntoView(true);", cartPage.deliverySelect);

        cartPage.deliverySelect.sendKeys(Keys.RETURN, Keys.ARROW_DOWN, Keys.ENTER);
    }

    @Then("Delivery is selected")
    public void delivery_is_selected() {

        Assertions.assertEquals("true", cartPage.deliverySelect.getAttribute("ng-reflect-model"));
    }

    @When("The User clicks the Submit Order button")
    public void the_user_clicks_the_submit_order_button() {

        cartPage.submitOrderButton.sendKeys(Keys.RETURN);
    }

    @Then("The User is redirected to the Landing Page")
    public void the_user_is_redirected_to_the_landing_page() {

        Assertions.assertEquals("http://localhost:4200/landingpage", driver.getCurrentUrl());
    }

    @Given("The User is on the Coffee Shop website")
    public void the_user_is_on_the_coffee_shop_website() {

        driver.manage().window().maximize();
        driver.get("http://localhost:4200");
    }

    @And("The User's cart has been emptied")
    public void the_user_s_cart_has_been_emptied() {

        navBar.navCart.click();
        Assertions.assertEquals("Cannot submit empty order.", cartPage.preSubmitPrompt.getText());
    }

    private void waitUp() {

        try {

            Thread.sleep(3000);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
