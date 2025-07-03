package org.xyzbank.tests.CustomerTests;

import io.qameta.allure.*;
import org.xyzbank.pages.Customer.CustomerDashboardPage;
import org.xyzbank.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.Customer.CustomerLoginPage;

@Epic("Customer Banking")
@Feature("Deposit Functionality")
public class VerifyCustomerCanDeposit extends BaseTest {

    @Test
    @Story("Deposit with valid amount")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC013: Verify customer can make a deposit with valid amount")
    public void testTC013_DepositValidAmount() {
        CustomerLoginPage customerLoginPage = stepClickCustomerLogin();
        CustomerDashboardPage accountPage = stepLoginAsCustomer(customerLoginPage, "Hermoine Granger");

        accountPage.deposit(50);
        String actualResult = accountPage.getSuccessMessage();

        Assertions.assertTrue(actualResult.contains("Deposit Successful"),
                "Expected success message after valid deposit");
    }

    @Test
    @Story("Deposit with negative amount")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC014: Verify customer cannot make a deposit with negative amount")
    public void testTC014_DepositNegativeAmount() {
        CustomerLoginPage customerLoginPage = stepClickCustomerLogin();
        CustomerDashboardPage accountPage = stepLoginAsCustomer(customerLoginPage, "Hermoine Granger");

        int initialBalance = accountPage.getNumericBalance();
        accountPage.deposit(-50);
        int updatedBalance = accountPage.getNumericBalance();

        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after negative deposit");
    }

    @Test
    @Story("Deposit with zero amount")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC015: Verify customer cannot make a deposit with zero amount")
    public void testTC015_DepositZeroAmount() {
        CustomerLoginPage customerLoginPage = stepClickCustomerLogin();
        CustomerDashboardPage accountPage = stepLoginAsCustomer(customerLoginPage, "Hermoine Granger");

        int initialBalance = accountPage.getNumericBalance();
        accountPage.deposit(0);
        int updatedBalance = accountPage.getNumericBalance();

        Assertions.assertEquals(initialBalance, updatedBalance, "Balance should not change after '0' deposit");
    }

    @Test
    @Story("Account balance update after deposit")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC016: Verify account balance updates after a deposit")
    public void testTC016_AccountBalanceUpdatesAfterDeposit() {
        CustomerLoginPage customerLoginPage = stepClickCustomerLogin();
        CustomerDashboardPage accountPage = stepLoginAsCustomer(customerLoginPage, "Hermoine Granger");

        int initialBalance = accountPage.getNumericBalance();
        accountPage.deposit(50);
        accountPage.waitForBalanceToUpdate(initialBalance);

        int updatedBalance = accountPage.getNumericBalance();

        Assertions.assertEquals(initialBalance + 50, updatedBalance,
                "Account balance should increase by the deposit amount");
    }

    // Allure @Step methods to improve step-level reporting
    @Step("Click on 'Customer Login' button")
    private CustomerLoginPage stepClickCustomerLogin() {
        return loginPage.clickCustomerLogin();
    }

    @Step("Login as customer: {customerName}")
    private CustomerDashboardPage stepLoginAsCustomer(CustomerLoginPage loginPage, String customerName) {
        return loginPage.loginAsCustomer(customerName);
    }
}
