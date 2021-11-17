package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
    public WebDriver driver;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[2]/td[2]/span")
    public WebElement cartItem1Text;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[2]/td[2]/p/span[1]")
    public WebElement extraItem1Text;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[2]/tr[2]/td[4]")
    public WebElement preSubmitPrompt;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[2]/tr[2]/td[4]/button")
    public WebElement submitOrderButton;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[3]/td[6]/button")
    public WebElement item2RemoveButton;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[3]")
    public WebElement item2Row;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[2]/td[4]/span[2]/button")
    public WebElement item1IncreaseQuantityButton;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[2]/td[4]/span[2]/span/button")
    public WebElement item1DecreaseQuantityButton;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[1]/tr[2]/td[4]/span[1]")
    public WebElement item1Quantity;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[2]/tr[2]/td[1]/select")
    public WebElement deliverySelect;

    @FindBy(xpath = "/html/body/app-root/app-cart/div/table[2]/tr[2]/td[2]/select")
    public WebElement paymentTypeSelect;


    public CartPage (WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
