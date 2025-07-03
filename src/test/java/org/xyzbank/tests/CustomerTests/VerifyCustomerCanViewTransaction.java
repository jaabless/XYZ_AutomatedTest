package org.xyzbank.tests.CustomerTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.pages.Customer.CustomerTransactionsPage;
import org.xyzbank.tests.BaseTest;

@Epic("Customer Banking")
@Feature("Transaction Viewing")
public class VerifyCustomerCanViewTransaction extends BaseTest {

    @Test
    @Story("Customer views recent transactions")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC011: Verify customer can view recent transactions")
    public void testTC011_ViewRecentTransactions() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        CustomerTransactionsPage customerTransactionsPage = accountPage.viewTransactions();

        Assertions.assertTrue(
                customerTransactionsPage.isTransactionTableDisplayed(),
                "Recent transactions are displayed with details (Date, Amount, Transaction Type)"
        );
        Assertions.assertTrue(
                customerTransactionsPage.hasTransactions(),
                "Customer should have at least one transaction"
        );
    }

    @Test
    @Story("Customer views empty transaction history")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("TC012: Verify customer can view transactions with no history")
    public void testTC012_ViewTransactionsWithNoHistory() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Neville Longbottom");
        CustomerTransactionsPage customerTransactionsPage = accountPage.viewTransactions();

        Assertions.assertTrue(
                customerTransactionsPage.isTransactionTableDisplayed(),
                "Recent transactions are displayed with details (Date, Amount, Transaction Type)"
        );
        Assertions.assertFalse(
                customerTransactionsPage.hasTransactions(),
                "Customer should not have any transaction"
        );
    }
}
