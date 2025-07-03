package org.xyzbank.pages.Customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.xyzbank.pages.BasePage;

public class CustomerLoginPage extends BasePage {
    @FindBy(id = "userSelect")
    private WebElement customerSelect;

    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    private WebElement loginBtn;

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    public CustomerDashboardPage loginAsCustomer(String customerName) {
        new Select(customerSelect).selectByVisibleText(customerName);
        loginBtn.click();
        return new CustomerDashboardPage(driver);
    }

    public boolean isCustomerSelectable(String customerName) {
        Select select = new Select(customerSelect);
        for (WebElement option : select.getOptions()) {
            if (option.getText().equals(customerName)) {
                return true;
            }
        }
        return false;
    }
}
