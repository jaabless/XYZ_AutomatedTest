package org.xyzbank.tests.BankManagerTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.xyzbank.pages.AddCustomerPage;
import org.xyzbank.pages.BankManagerDashboard;
import org.xyzbank.tests.BaseTest;

@Epic("Bank Manager Functionality")
@Feature("Add Customer Feature")
public class VerifyManagerCanAddCustomer extends BaseTest {

    @Test
    @Story("Bank Manager adds a customer with valid details")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC001: Verify manager can add customer with valid data")
    public void testTC001_AddCustomerWithValidData() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();
        addCustomerForm.addCustomer("New", "Mensah", "12345");
        String actualResult = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assertions.assertTrue(actualResult.contains("Customer added successfully"));
    }

    @Test
    @Story("Input validation: Customer name with numbers")
    @Severity(SeverityLevel.NORMAL)
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
    @Story("Input validation: Customer name with special characters")
    @Severity(SeverityLevel.NORMAL)
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
    @Story("Input validation: Postal code must be numeric")
    @Severity(SeverityLevel.NORMAL)
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
    @Story("Input validation: All fields empty")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC005: Verify adding customer with empty fields")
    public void testTC005_AddCustomerWithEmptyFields() {
        BankManagerDashboard managerPage = loginPage.clickBankManagerLogin();
        AddCustomerPage addCustomerForm = managerPage.goToAddCustomer();

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
