package org.xyzbank.tests.BankManagerTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.AddCustomerPage;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.tests.BaseTest;
import org.xyzbank.tests.TestData.ManagerTestData;

@Epic("Bank Manager Functionality")
@Feature("Customer Access Control")
public class VerifyCustomerAccessRestrictedUntilCreation extends BaseTest {

    @Test
    @Story("Customer cannot log in or use services until account is created")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC008: Verify customer cannot access account before creation")
    public void testTC008_CustomerCannotAccessBeforeCreation() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer(ManagerTestData.NEW_CUSTOMER_FIRST_NAME, ManagerTestData.NEW_CUSTOMER_LAST_NAME, ManagerTestData.NEW_CUSTOMER_POSTAL_CODE);
        driver.switchTo().alert().accept();
        loginPage.goToHome();
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        customerLoginPage.loginAsCustomer(ManagerTestData.NEW_CUSTOMER_NAME);
        CustomerDashboardPage customerDashboardPage = new CustomerDashboardPage(driver);
        String actualResult = customerDashboardPage.getNoAccountMessage();
        Assertions.assertTrue(actualResult.contains("Please open an account with us."),
                "Customer should not have access until an account is created.");
    }
}
