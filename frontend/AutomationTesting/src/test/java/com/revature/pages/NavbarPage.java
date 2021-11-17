package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavbarPage {
    public WebDriver driver;

    @FindBy(linkText = "Menu")
    public WebElement menuButton;

    @FindBy(id = "menuHead")
    public WebElement menuHead;

    @FindBy(linkText = "About Us")
    public WebElement aboutUs;

    @FindBy(id = "aboutUsHead")
    public WebElement aboutUsHead;

    @FindBy(linkText = "Contact Us")
    public WebElement contactUs;

    @FindBy(id = "contactUsHead")
    public WebElement contactUsHead;

    @FindBy(id = "navLogin")
    public WebElement navLogin;

    @FindBy(id = "navCart")
    public WebElement navCart;

    @FindBy(id = "cartHead")
    public WebElement cartHead;

    public NavbarPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
