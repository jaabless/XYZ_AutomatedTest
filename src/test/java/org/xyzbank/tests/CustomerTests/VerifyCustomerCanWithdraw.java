package org.xyzbank.tests.CustomerTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.pages.Customer.CustomerLoginPage;
import org.xyzbank.tests.BaseTest;
import org.xyzbank.tests.TestData.CustomerTestData;

@Epic("Customer Banking")
@Feature("Withdrawal Functionality")
public class VerifyCustomerCanWithdraw extends BaseTest {

    @Test
    @Story("Customer withdraws valid amount")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC013: Verify customer can make a withdraw with valid amount")
    public void testTC013_WithdrawValidAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer(CustomerTestData.EXISTING_CUSTOMER_NAME);

        accountPage.withdraw(CustomerTestData.VALID_WITHDRAW_AMOUNT);
        String actualResult = accountPage.getSuccessMessage();
        Assertions.assertTrue(actualResult.contains("successful"));
    }

    @Test
    @Story("Customer attempts to withdraw negative amount")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC014: Verify customer cannot make a withdraw with negative amount")
    public void testTC014_WithdrawNegativeAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer(CustomerTestData.EXISTING_CUSTOMER_NAME);
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(CustomerTestData.NEGATIVE_WITHDRAW_AMOUNT);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after negative withdrawal");
    }

    @Test
    @Story("Customer attempts to withdraw zero amount")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC015: Verify customer cannot make a withdraw with zero amount")
    public void testTC015_WithdrawZeroAmount() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer(CustomerTestData.EXISTING_CUSTOMER_NAME);
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(CustomerTestData.ZERO_WITHDRAW_AMOUNT);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after '0' withdrawal");
    }

    @Test
    @Story("Customer attempts to withdraw more than available balance")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC020: Verify customer cannot withdraw amount exceeding balance")
    public void testTC020_WithdrawExceedingBalance() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer(CustomerTestData.EXISTING_CUSTOMER_NAME);
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(CustomerTestData.OVERDRAFT_AMOUNT); // Attempt to withdraw more than the balance
        int updatedBalance = accountPage.getNumericBalance();
        String actualResult = accountPage.getSuccessMessage();
        Assertions.assertTrue(actualResult.contains("Transaction Failed"));
        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after failed withdrawal");
    }

    @Test
    @Story("Customer views balance update after withdrawal")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC016: Verify account balance updates after a withdrawal")
    public void testTC016_AccountBalanceUpdatesAfterWithdrawal() {
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLogin();
        CustomerDashboardPage accountPage = customerLoginPage.loginAsCustomer(CustomerTestData.EXISTING_CUSTOMER_NAME);
        int initialBalance = accountPage.getNumericBalance();
        accountPage.withdraw(CustomerTestData.VALID_WITHDRAW_AMOUNT);
        accountPage.waitForBalanceToUpdate(initialBalance);
        int updatedBalance = accountPage.getNumericBalance();
        Assertions.assertEquals(initialBalance - 50, updatedBalance,
                "Account balance should decrease by the withdrawal amount");
    }
}
