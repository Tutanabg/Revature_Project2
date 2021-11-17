package com.revature.steps;

import com.revature.pages.CartPage;
import com.revature.pages.MenuPage;
import com.revature.pages.NavbarPage;
import com.revature.runners.CoffeeShopRunner;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class MenuStepImpl {

    public static MenuPage menuPage = CoffeeShopRunner.menuPage;
    public static NavbarPage navBar = CoffeeShopRunner.navbarPage;
    public static CartPage cartPage = CoffeeShopRunner.cartPage;
    public static WebDriver driver = CoffeeShopRunner.driver;

    @Given("The User is on the MenuPage")
    public void the_user_is_on_the_menu_page() {

        driver.manage().window().maximize();
        driver.get("http://localhost:4200/menu");
    }

    @When("The User clicks the show-extra-items button for some menu item")
    public void the_user_clicks_the_show_extra_items_button_for_some_menu_item() {

        menuPage.selectExtraItems.click();
    }

    @When("The User changes the quantity of any extra item")
    public void the_user_changes_the_quantity_of_any_extra_item() {

        menuPage.quantityExtraItem1.sendKeys("2");
    }

    @When("The User clicks the add-item-to-cart button for the menu item")
    public void the_user_clicks_the_add_item_to_cart_button_for_the_menu_item() {

        menuPage.addItemButton1.click();
    }

    @When("The User clicks the cart button")
    public void the_user_clicks_the_cart_button() {

        navBar.navCart.click();
    }

    @Then("The selected menu item with the chosen extra items should be visible in the cart")
    public void the_selected_menu_item_with_the_chosen_extra_items_should_be_visible_in_the_cart() {

        Assertions.assertEquals("espresso: $2.50", cartPage.cartItem1Text.getText());
        Assertions.assertEquals("almond milk x 2 =", cartPage.extraItem1Text.getText());
    }

    private void waitUp() {

        try {

            Thread.sleep(3000);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
