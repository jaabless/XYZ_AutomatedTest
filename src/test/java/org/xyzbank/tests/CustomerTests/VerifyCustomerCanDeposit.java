package org.xyzbank.tests.CustomerTests;

import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerLoginPage;

public class VerifyCustomerCanDeposit extends BaseTest {

    @Test
    @DisplayName("TC013: Verify customer can make a deposit with valid amount")
    public void testTC013_DepositValidAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        accountPage.deposit(50);
        String actualResult = accountPage.getSuccessMessage();
        Assertions.assertTrue(actualResult.contains("Deposit Successful"));
    }

    @Test
    @DisplayName("TC014: Verify customer cannot make a deposit with negative amount")
    public void testTC014_DepositNegativeAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        int initialBalance = accountPage.getNumericBalance();
        accountPage.deposit(-50);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after negative deposit");

        // Step 6: Assert that success message is not displayed
//        Assertions.assertFalse(accountPage.isSuccessMessageDisplayed(), "No success message should appear for invalid deposit");
    }

    @Test
    @DisplayName("TC015: Verify customer cannot make a deposit with zero amount")
    public void testTC015_DepositZeroAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        int initialBalance = accountPage.getNumericBalance();
        accountPage.deposit(0);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after '0' deposit");

    }
//
    @Test
    @DisplayName("TC016: Verify account balance updates after a deposit")
    public void testTC016_AccountBalanceUpdatesAfterDeposit() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");

        int initialBalance = accountPage.getNumericBalance();
        accountPage.deposit(50);
        accountPage.waitForBalanceToUpdate(initialBalance);

        int updatedBalance = accountPage.getNumericBalance();

        Assertions.assertEquals(initialBalance + 50, updatedBalance,
                "Account balance should increase by the deposit amount");
    }

}
