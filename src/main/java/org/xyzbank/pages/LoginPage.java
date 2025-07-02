package org.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//button[contains(text(), 'Bank Manager Login')]")
    private WebElement bankManagerLoginBtn;

    @FindBy(xpath = "//button[contains(text(), 'Customer Login')]")
    private WebElement customerLoginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public BankManagerDashboard clickBankManagerLogin() {
        bankManagerLoginBtn.click();
        return new BankManagerDashboard(driver);
    }

//    public CustomerLoginPage clickCustomerLogin() {
//        customerLoginBtn.click();
//        return new CustomerLoginPage(driver);
//    }
}