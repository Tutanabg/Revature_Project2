package com.revature.runners;

import com.revature.pages.*;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = {"com.revature.steps"})
public class CoffeeShopRunner {

    public static WebDriver driver;
    public static LoginPage CoffeeShop;
    public static OrderManagementPage orderManagementPage;
    public static NavbarPage navbarPage;
    public static MenuPage menuPage;
    public static CartPage cartPage;

    @BeforeClass
    public static void setUp() {

        String path = System.getenv("DRIVER_PATH");
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver", path);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        CoffeeShop = new LoginPage(driver);
        orderManagementPage = new OrderManagementPage(driver);
        navbarPage = new NavbarPage(driver);
        menuPage = new MenuPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

}