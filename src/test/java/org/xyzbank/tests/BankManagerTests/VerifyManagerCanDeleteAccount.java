package org.xyzbank.tests.BankManagerTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.pages.ViewCustomersPage;
import org.xyzbank.tests.BaseTest;
import org.xyzbank.tests.TestData.ManagerTestData;

@Epic("Bank Manager Functionality")
@Feature("Delete Customer Account")
public class VerifyManagerCanDeleteAccount extends BaseTest {

    @Test
    @Story("Manager deletes a customer account")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC009: Verify manager can delete an account")
    public void testTC009_ManagerCanDeleteAccount() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        ViewCustomersPage customersTable = managerPage.goToCustomersTable();
        Assertions.assertTrue(customersTable.isCustomerPresent(ManagerTestData.DEL_CUSTOMER_NAME), "Customer should be listed before deletion");
        customersTable.deleteCustomer(ManagerTestData.DEL_CUSTOMER_NAME);
        Assertions.assertFalse(customersTable.isCustomerPresent("Harry"), "Customer should be removed after deletion");
    }

    @Test
    @Story("Deleted customer cannot access their account")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC010: Verify customer cannot access deleted account")
    public void testTC010_CustomerCannotAccessDeletedAccount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        Assertions.assertFalse(customerLoginPage.isCustomerSelectable(ManagerTestData.NON_EXISTING_CUSTOMER), "Customer cannot be found after deletion");
    }
}
