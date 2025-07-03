package org.xyzbank.tests.CustomerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.tests.BaseTest;

public class VerifyCustomerCanWithdraw extends BaseTest {

    @Test
    @DisplayName("TC013: Verify customer can make a deposit with valid amount")
    public void testTC013_WithdrawValidAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        accountPage.withdraw(50);
        String actualResult = accountPage.getSuccessMessage();
        Assertions.assertTrue(actualResult.contains("successful"));
    }

    @Test
    @DisplayName("TC014: Verify customer cannot make a withdraw with negative amount")
    public void testTC014_WithdrawNegativeAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(-50);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after negative deposit");

        // Step 6: Assert that success message is not displayed
//        Assertions.assertFalse(accountPage.isSuccessMessageDisplayed(), "No success message should appear for invalid deposit");
    }

    @Test
    @DisplayName("TC015: Verify customer cannot make a withdraw with zero amount")
    public void testTC015_WithdrawZeroAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(0);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after '0' deposit");
    }

    @Test
    @DisplayName("TC020: Verify customer can withdraw amount exceeding balance")
    public void testTC020_WithdrawExceedingBalance() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(1000000); // Attempt to withdraw more than the balance
        int updatedBalance = accountPage.getNumericBalance();
        String actualResult = accountPage.getSuccessMessage();
        Assertions.assertTrue(actualResult.contains("Transaction Failed"));
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after deposit");
    }

    @Test
    @DisplayName("TC016: Verify account balance updates after a deposit")
    public void testTC016_AccountBalanceUpdatesAfterWithdrawal() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer("Hermoine Granger");

        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(50);
        accountPage.waitForBalanceToUpdate(initialBalance);

        int updatedBalance = accountPage.getNumericBalance();

        Assertions.assertEquals(initialBalance - 50, updatedBalance,
                "Account balance should decrease by the withdrawal amount");
    }

}
