package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    public WebDriver driver;


    @FindBy(xpath = "/html/body/app-root/app-menu/div[1]/table/tr[2]/td[4]/button")
    public WebElement selectExtraItems;

    @FindBy(xpath = "/html/body/app-root/app-menu/div[1]/table/tr[2]/td[4]/div/div/div[1]/input")
    public WebElement quantityExtraItem1;

    @FindBy(xpath = "/html/body/app-root/app-menu/div[1]/table/tr[2]/td[5]/button")
    public WebElement addItemButton1;

    @FindBy(xpath = "/html/body/app-root/app-menu/div[2]/table/tr[7]/td[5]/button")
    public WebElement addItemButton6;


    public MenuPage (WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
