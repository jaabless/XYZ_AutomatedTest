package org.xyzbank.tests.BankManagerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.pages.ViewCustomersPage;
import org.xyzbank.tests.BaseTest;

public class VerifyManagerCanDeleteAccount extends BaseTest {
    @Test
    @DisplayName("TC009: Verify manager can delete an account")
    public void testTC009_ManagerCanDeleteAccount() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        ViewCustomersPage customersTable = managerPage.goToCustomersTable();

        Assertions.assertTrue(customersTable.isCustomerPresent("Harry"), "Customer should be listed before deletion");

        customersTable.deleteCustomer("Harry");

        Assertions.assertFalse(customersTable.isCustomerPresent("Harry"), "Customer should be removed after deletion");
    }

    @Test
    @DisplayName("TC010: Verify customer cannot access deleted account")
    public void testTC010_CustomerCannotAccessDeletedAccount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
//        Assertions.assertTrue(customerLoginPage.isCustomerSelectable("New Customer"), "List of customers are displayed 1");
        Assertions.assertFalse(customerLoginPage.isCustomerSelectable("New Customer"), "Customer cannot be found 2");
    }
}
