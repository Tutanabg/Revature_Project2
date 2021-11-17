package com.revature.steps;

import com.revature.pages.OrderManagementPage;
import com.revature.runners.CoffeeShopRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderManagementStepImpl {
    public static OrderManagementPage orderManagementPage = CoffeeShopRunner.orderManagementPage;
    public static WebDriver driver = CoffeeShopRunner.driver;

    // the manager can click on the order management page
    @Given("the user is a manager")
    public void the_user_is_a_manager() {
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/login");
        orderManagementPage.userInput.sendKeys("coffeequeen92");
        orderManagementPage.passwordInput.sendKeys("Password1");
        orderManagementPage.loginBtn.click();
        new WebDriverWait(driver, 10L);

    }
    @When("the manager clicks on the order management page")
    public void the_manager_clicks_on_the_order_management_page() {
        orderManagementPage.orderManageNav.click();
    }
    @Then("the manager should be on the order management page")
    public void the_manager_should_be_on_the_order_management_page() {
        Assertions.assertTrue(orderManagementPage.orderManageHead.getText().equals("Orders Today"));
    }

    //the manager can see all the orders for today
    @When("the manager is on the order management page")
    public void the_manager_is_on_the_order_management_page() {
        orderManagementPage.orderManageNav.click();
    }
    @Then("the manager should see the orders from today")
    public void the_manager_should_see_the_orders_from_today() {
        Assertions.assertTrue(orderManagementPage.cardHeader.isDisplayed());
    }

    // the manager can advance an order
    @When("the manager clicks on an order to advance")
    public void the_manager_clicks_on_an_order_to_advance() {
        orderManagementPage.orderManageNav.click();

    }
    @Then("the order should have status as ready")
    public void the_order_should_have_status_as_ready() {
        Assertions.assertTrue(orderManagementPage.advanceBtn.isDisplayed());
    }



    @Given("the user is a manager on the order page")
    public void the_user_is_a_manager_on_the_order_page() {
        Assertions.assertTrue(orderManagementPage.orderManageHead.getText().equals("Orders Today"));
    }
    @When("the manager inputs the day to view orders from")
    public void the_manager_inputs_the_day_to_view_orders_from() {
        orderManagementPage.dateInput.sendKeys("11-12-2021");

    }
    @When("the manager clicks the button to see orders")
    public void the_manager_clicks_the_button_to_see_orders() {
        new WebDriverWait(driver, 10L);
        orderManagementPage.orderManageHead.click();
//        driver.findElement(By.id("dayBtn")).click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("dayBtn")));
    }
    @Then("the orders from that day should be displayed")
    public void the_orders_from_that_day_should_be_displayed() {

        orderManagementPage.orderDay.isDisplayed();
    }
}
