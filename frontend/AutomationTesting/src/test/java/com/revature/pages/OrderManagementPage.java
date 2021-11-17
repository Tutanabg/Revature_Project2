package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderManagementPage {
    public WebDriver driver;

    @FindBy(id = "userInput")
    public WebElement userInput;

    @FindBy(id = "passwordInput")
    public WebElement passwordInput;

    @FindBy(id = "loginpageLoginButton")
    public WebElement loginBtn;

    @FindBy(linkText = "Order Management")
    public WebElement orderManageNav;

    @FindBy(id = "ordermanageTodayID")
    public WebElement orderManageHead;

    @FindBy(className = "card-header")
    public WebElement cardHeader;

    @FindBy(id = "advanceBtn")
    public WebElement advanceBtn;

    @FindBy(id = "orderDate")
    public WebElement dateInput;

    @FindBy(id = "dayBtn")
    public WebElement dateBtn;

    @FindBy(id = "orderDay")
    public WebElement orderDay;

    public OrderManagementPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
