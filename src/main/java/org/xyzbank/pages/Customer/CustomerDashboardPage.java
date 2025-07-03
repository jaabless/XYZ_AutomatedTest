package org.xyzbank.pages.Customer;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xyzbank.pages.BasePage;

import java.time.Duration;

public class CustomerDashboardPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Deposit')]")
    private WebElement depositTab;

    @FindBy(xpath = "//button[contains(text(), 'Withdrawl')]")
    private WebElement withdrawTab;

    @FindBy(xpath = "//button[contains(text(), 'Transactions')]")
    private WebElement transactionsTab;

    @FindBy(xpath = "//input[@placeholder='amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Deposit')]")
    private WebElement depositSubmitBtn;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Withdraw')]")
    private WebElement withdrawSubmitBtn;

    @FindBy(xpath = "(//strong[@class='ng-binding'])[2]")
    private WebElement balanceLabel;

    @FindBy(xpath = "//span[@ng-show='noAccount']")
    private WebElement messageLabel;

    @FindBy(xpath = "//span[@class='error ng-binding']")
    private WebElement depositMessageLabel;

    @FindBy(xpath = "//span[@class='error ng-binding']")
    private WebElement successMessageLabel;

    public CustomerDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void deposit(int amount) {
        depositTab.click();
        amountInput.clear();
        amountInput.sendKeys(String.valueOf(amount));
        depositSubmitBtn.click();
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(int amount) {
        withdrawTab.click();
        amountInput.clear();
        amountInput.sendKeys(String.valueOf(amount));
        withdrawSubmitBtn.click();
    }

    public CustomerTransactionsPage viewTransactions() {
        transactionsTab.click();
        return new CustomerTransactionsPage(driver);
    }

    // ✅ Return numeric balance (stripping currency or labels)
    public int getNumericBalance() {
        String text = balanceLabel.getText().replaceAll("[^\\d]", "");
        if (text.isEmpty()) {
            throw new IllegalStateException("Balance label did not contain numeric value: " + balanceLabel.getText());
        }
        return Integer.parseInt(text);
    }


    // ✅ Wait until balance changes after deposit
    public void waitForBalanceToUpdate(int previousBalance) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            String updatedText = balanceLabel.getText().replaceAll("[^\\d]", "");
            return !updatedText.isEmpty() && Integer.parseInt(updatedText) != previousBalance;
        });
    }

    public String getNoAccountMessage() {
        return messageLabel.getText();
    }

    public String getSuccessMessage() {
        return depositMessageLabel.getText();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            return wait.until(ExpectedConditions.visibilityOf(successMessageLabel)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
