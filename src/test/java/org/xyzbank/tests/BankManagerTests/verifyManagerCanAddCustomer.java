package org.xyzbank.tests.BankManagerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.xyzbank.pages.AddCustomerPage;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.tests.BaseTest;

public class verifyManagerCanAddCustomer extends BaseTest {

    @Test
    public void testBankManagerFunctionality() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();


//        // Add Customer
//        String customerName = "John Doe";
//        managerPage.addCustomer("John", "Doe", "12345");
//
//        // Verify customer added (assuming alert confirmation)
//        driver.switchTo().alert().accept();
//
//        // Create Account
//        managerPage.openAccount(customerName, "Dollar");
//        driver.switchTo().alert().accept();
//
//        // Delete Customer
//        managerPage.deleteCustomer(customerName);
//
//        // Verify deletion (assuming customer no longer appears in search)
//        managerPage.customersTab.click();
//        managerPage.searchCustomerInput.sendKeys(customerName);
//        Assertions.assertFalse(driver.findElements(managerPage.deleteCustomerBtn.getLocator()).size() > 0);
    }


    @Test
    public void testTC001_AddCustomerWithValidData() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer("New", "Mensah", "12345");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assertions.assertTrue(actualResult.contains("Customer added successfully"));
    }

    @Test
    @DisplayName("TC002: Verify adding customer with invalid name (numbers)")
    public void testTC002_AddCustomerWithInvalidNameNumbers() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer("Kwame01", "Mensah", "12345");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assertions.assertEquals("Name must contain only alphabetic characters", actualResult);
    }

    @Test
    @DisplayName("TC003: Verify adding customer with invalid name (special characters)")
    public void testTC003_AddCustomerWithInvalidNameSpecial() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer("Kwame@", "Mensah", "12345");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assertions.assertEquals("Name must contain only alphabetic characters", actualResult);
    }

    @Test
    @DisplayName("TC004: Verify adding customer with invalid postal code")
    public void testTC004_AddCustomerWithInvalidPostalCode() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer("Kwame", "Mensah", "ABCD");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assertions.assertEquals("Postal code must contain only numeric characters", actualResult);
    }

    @Test
    @DisplayName("TC005: Verify adding customer with empty fields")
    public void testTC005_AddCustomerWithEmptyFields() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();

        // Try to submit the form with all empty fields
        addCustomerForm.addCustomer("", "", "");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement firstNameField = addCustomerForm.getFirstNameField();
        WebElement lastNameField = addCustomerForm.getLastNameField();
        WebElement postCodeField = addCustomerForm.getPostCodeField();

        String firstNameMessage = (String) js.executeScript("return arguments[0].validationMessage;", firstNameField);
        String lastNameMessage = (String) js.executeScript("return arguments[0].validationMessage;", lastNameField);
        String postCodeMessage = (String) js.executeScript("return arguments[0].validationMessage;", postCodeField);

        System.out.println("First Name Validation: " + firstNameMessage);
        System.out.println("Last Name Validation: " + lastNameMessage);
        System.out.println("Post Code Validation: " + postCodeMessage);

        Assertions.assertTrue(firstNameMessage.toLowerCase().contains("fill out this field"));
        Assertions.assertTrue(lastNameMessage.toLowerCase().contains("fill out this field"));
        Assertions.assertTrue(postCodeMessage.toLowerCase().contains("fill out this field"));
    }





}
