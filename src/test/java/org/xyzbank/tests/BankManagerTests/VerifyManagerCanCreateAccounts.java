package org.xyzbank.tests.BankManagerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.pages.OpenAccountPage;
import org.xyzbank.tests.BaseTest;

public class VerifyManagerCanCreateAccounts extends BaseTest{

    @Test
    @DisplayName("TC006: Verify bank manager can create account for existing customer")
    public void testTC006_CreateAccountForExistingCustomer() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        OpenAccountPage openAccountPage = managerPage.goToOpenAccount();
        openAccountPage.openAccount("Harry Potter", "Dollar");
//        WebElement selectedCustomer = openAccountPage.getSelectedCustomer();
//        Select customerDropdown = new Select(selectedCustomer);
//        String selectedOptionText = customerDropdown.getFirstSelectedOption().getText();
//        System.out.println("Selected Customer: " + selectedOptionText);
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println("Alert Message: " + actualResult);
        Assertions.assertTrue(actualResult.contains("Account created successfully with account Number"));
    }

    @Test
    @DisplayName("TC007: Verify bank manager cannot create account for non-existing customer")
    public void testTC007_CreateAccountForNonExistingCustomer() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        OpenAccountPage openAccountPage = managerPage.goToOpenAccount();
        openAccountPage.openAccount("Non Existing", "Dollar");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println("Alert Message: " + actualResult);
        Assertions.assertTrue(actualResult.contains("Account created successfully with account Number"));
    }
}
