package org.xyzbank.tests.BankManagerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.xyzbank.pages.AddCustomerPage;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.tests.BaseTest;

public class VerifyCustomerAccessRestrictedUntilCreation extends BaseTest {

    @Test
    @DisplayName("TC008: Verify customer cannot access account before creation")
    public void testTC008_CustomerCannotAccessBeforeCreation() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer("New", "Customer", "12345");
        driver.switchTo().alert().accept();
        loginPage.goToHome();
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        customerLoginPage.loginAsCustomer("New Customer");
        CustomerDashboardPage customerDashboardPage = new CustomerDashboardPage(driver);
        String actualResult = customerDashboardPage.getNoAccountMessage();
        Assertions.assertTrue(actualResult.contains("Please open an account with us."));
    }
}
