package org.xyzbank.pages.Customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xyzbank.pages.BasePage;

import java.util.List;

public class CustomerTransactionsPage extends BasePage {

    @FindBy(xpath = "//table")
    private WebElement transactionsTable;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement resetButton;

    public CustomerTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTransactionTableDisplayed() {
        return transactionsTable.isDisplayed();
    }

    public boolean hasTransactions() {
        List<WebElement> rows = transactionsTable.findElements(By.xpath(".//tr"));
        return rows.size() > 1; // >1 means at least one data row
    }

    public void resetTransactions() {
        resetButton.click();
//        waitForElementToDisappear(transactionsTable);
    }
}
