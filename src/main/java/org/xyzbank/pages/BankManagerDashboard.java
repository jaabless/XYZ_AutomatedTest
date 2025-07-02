package org.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankManagerDashboard extends BasePage{

    @FindBy(xpath = "//button[normalize-space()='Add Customer']")
    private WebElement addCustomerTab;

    @FindBy(xpath = "//button[contains(text(), 'Open Account')]")
    private WebElement openAccountTab;

    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    private WebElement customersTab;

    public BankManagerDashboard(WebDriver driver) {
        super(driver);
    }

    public AddCustomerPage goToAddCustomer() {
        addCustomerTab.click();
        return new AddCustomerPage(driver);
    }

    public OpenAccountPage goToOpenAccount() {
        openAccountTab.click();
        return new OpenAccountPage(driver);
    }

    public ViewCustomersPage goToCustomersTable() {
        customersTab.click();
        return new ViewCustomersPage(driver);
    }


    // Add methods specific to the Bank Manager Dashboard page here
    // For example, methods to navigate to Add Customer, Open Account, and Customer Transactions pages
}
