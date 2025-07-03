package org.xyzbank.tests.CustomerTests;

import org.xyzbank.pages.AddCustomerPage;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerTransactionsPage;
import org.xyzbank.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerLoginPage;

public class VerifyCustomerCanViewTransaction extends BaseTest {

    @Test
    @DisplayName("TC011: Verify customer can view recent transactions")
    public void testTC011_ViewRecentTransactions() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        CustomerTransactionsPage customerTransactionsPage = accountPage.viewTransactions();
        Assertions.assertTrue(customerTransactionsPage.isTransactionTableDisplayed(), "Recent transactions are displayed with details (Date, Amount, Transaction Type)");
        Assertions.assertTrue(customerTransactionsPage.hasTransactions(), "Customer should have at least one transaction");
    }

    @Test
    @DisplayName("TC012: Verify customer can view transactions with no history")
    public void testTC012_ViewTransactionsWithNoHistory() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Neville Longbottom");
        CustomerTransactionsPage customerTransactionsPage = accountPage.viewTransactions();
        Assertions.assertTrue(customerTransactionsPage.isTransactionTableDisplayed(), "Recent transactions are displayed with details (Date, Amount, Transaction Type)");
        Assertions.assertFalse(customerTransactionsPage.hasTransactions(), "Customer should have at least one transaction");;
    }
}
