package org.xyzbank.tests.CustomerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.pages.Customer.CustomerTransactionsPage;
import org.xyzbank.tests.BaseTest;
import io.qameta.allure.*;

@Epic("Customer Banking")
@Feature("Transaction Functionality")
public class VerifyCustomersCantEditTransactions extends BaseTest {

    @Story("Customer cannot deposit negative amount")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("TC011: Verify customer can view recent transactions")
    public void testTC011_ResetTransactions() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        CustomerTransactionsPage customerTransactionsPage = accountPage.viewTransactions();
        customerTransactionsPage.resetTransactions();
        Assertions.assertTrue(customerTransactionsPage.isTransactionTableDisplayed(), "Recent transactions are displayed with details (Date, Amount, Transaction Type)");
        Assertions.assertTrue(customerTransactionsPage.hasTransactions(), "Customer should have at least one transaction");
    }
}
