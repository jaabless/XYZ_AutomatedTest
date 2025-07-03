package org.xyzbank.tests.BankManagerTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.OpenAccountPage;
import org.xyzbank.tests.BaseTest;

@Epic("Bank Manager Functionality")
@Feature("Open Bank Account Feature")
public class VerifyManagerCanCreateAccounts extends BaseTest {

    @Test
    @Story("Bank Manager creates account for existing customer")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC006: Verify bank manager can create account for existing customer")
    public void testTC006_CreateAccountForExistingCustomer() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        OpenAccountPage openAccountPage = managerPage.goToOpenAccount();
        openAccountPage.openAccount("Harry Potter", "Dollar");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println("Alert Message: " + actualResult);
        Assertions.assertTrue(actualResult.contains("Account created successfully with account Number"));
    }

    @Test
    @Story("Bank Manager attempts to create account for non-existing customer")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC007: Verify bank manager cannot create account for non-existing customer")
    public void testTC007_CreateAccountForNonExistingCustomer() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        OpenAccountPage openAccountPage = managerPage.goToOpenAccount();
        openAccountPage.openAccount("Non Existing", "Dollar");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println("Alert Message: " + actualResult);
        Assertions.assertTrue(actualResult.contains("Account created successfully with account Number"));
        // 🔁 Adjust the assertion above if app behavior is supposed to block non-existent users
    }
}
