package org.xyzbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.xyzbank.pages.Customer.CustomerDashboardPage;

import java.util.List;

public class ViewCustomersPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    private WebElement searchCustomerInput;

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    private WebElement deleteCustomerBtn;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> customerRows;

    public ViewCustomersPage(WebDriver driver) {
        super(driver);
    }

    public void deleteCustomer(String customerName) {
        for (WebElement row : customerRows) {
            if (row.getText().contains(customerName)) {
                WebElement deleteButton = row.findElement(By.xpath(".//button[text()='Delete']"));
                deleteButton.click();
                break;
            }
        }
    }


//    public boolean isCustomerPresent(String customerName) {
//        searchCustomerInput.clear();
//        searchCustomerInput.sendKeys(customerName);
//        return deleteCustomerBtn.isDisplayed();
//    }

    public boolean isCustomerPresent(String customerName) {
        searchCustomerInput.clear();
        searchCustomerInput.sendKeys(customerName);

        // Wait briefly to allow filtering (could also use WebDriverWait if necessary)
//        try {
//            Thread.sleep(500); // optional or replace with WebDriverWait
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (WebElement row : customerRows) {
            if (row.getText().contains(customerName)) {
                return true;
            }
        }
        return false;
    }
}
