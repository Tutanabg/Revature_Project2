package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    //Login
    @FindBy(xpath = "/html/body/app-root/nav/div/div/div/button")
    public WebElement login;

    @FindBy(xpath = "/html/body/app-root/app-login/div[1]/h1")
    public WebElement loginHeader;

    @FindBy(id = "userInput")
    public WebElement userInput;

    @FindBy(id = "passwordInput")
    public WebElement passwordInput;

    @FindBy(id = "loginpageLoginButton")
    public WebElement loginBtn;

    @FindBy(id = "loginDiv")
    public WebElement loginDiv;

    @FindBy(xpath = "/html/body/app-root/app-login/div[1]/div[2]/div[2]/div/button[2]")
    public WebElement registerBtn;

    @FindBy(xpath = "/html/body/app-root/app-landing-page/div[1]/h1")
    public WebElement landingPageHeader;

    @FindBy(id = "accountDiv")
    public WebElement accountDiv;

    @FindBy(xpath = "/html/body/app-root/app-login/div[2]/div/div/form/div[2]/button[1]")
    public WebElement formCloseBtn;

    @FindBy(xpath = "/html/body/app-root/app-login/div[3]/div/div/form/div[2]/button[2]")
    public WebElement formSubmitBtn;

    @FindBy(id = "inputFirstName")
    public WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    public WebElement inputLastName;

    @FindBy(id = "inputPhoneNumber")
    public WebElement inputPhoneNumber;

    @FindBy(id = "inputEmail")
    public WebElement inputEmail;

    @FindBy(id = "inputStreet")
    public WebElement inputStreet;

    @FindBy(id = "inputCity")
    public WebElement inputCity;

    @FindBy(id = "inputZipCode")
    public WebElement inputZipCode;

    @FindBy(id = "inputUsername")
    public WebElement inputUsername;

    @FindBy(id = "inputPassword")
    public WebElement inputPassword;

    @FindBy(id = "inputConfirmPassword")
    public WebElement inputConfirmPassword;

    @FindBy(xpath = "/html/body/app-root/app-login/div[1]/div[2]/div[2]/div/a")
    public WebElement forgetPassword;

    @FindBy(xpath = "/html/body/app-root/app-login/div[3]")
    public WebElement updateAccountDiv;

    @FindBy(id = "updateSubmitBtn")
    public WebElement updateSubmitBtn;

    @FindBy(id = "updateCloseBtn")
    public WebElement updateCloseBtn;

    @FindBy(id = "inputFirstName2")
    public WebElement inputFirstName2;

    @FindBy(id = "inputLastName2")
    public WebElement inputLastName2;

    @FindBy(id = "inputPhoneNumber2")
    public WebElement inputPhoneNumber2;

    @FindBy(id = "inputEmail2")
    public WebElement inputEmail2;

    @FindBy(id = "inputUsername2")
    public WebElement inputUsername2;

    @FindBy(id = "inputPassword2")
    public WebElement inputPassword2;

    @FindBy(id = "inputConfirmPassword2")
    public WebElement inputConfirmPassword2;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
