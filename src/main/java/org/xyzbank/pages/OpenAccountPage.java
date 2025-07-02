package org.xyzbank.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class OpenAccountPage extends BasePage {

    @FindBy(id = "userSelect")
    private WebElement customerSelect;

    @FindBy(id = "currency")
    private WebElement currencySelect;

    @FindBy(xpath = "//button[contains(text(), 'Process')]")
    private WebElement processAccountBtn;

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSelectedCustomer() {
        return customerSelect;
    }

    public void openAccount(String customerName, String currency) {
        List<WebElement> options = new Select(customerSelect).getOptions();
        System.out.println("Available Customers:");
        for (WebElement option : options) {
            System.out.println(option.getText());
        }


        System.out.println("Selecting customer: " + customerName);
        new Select(customerSelect).selectByVisibleText(customerName);

        System.out.println("Selecting currency: " + currency);
        new Select(currencySelect).selectByVisibleText(currency);

        processAccountBtn.click();
    }

//    public void openAccount(String customerName, String currency) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        // Set customer dropdown
//        js.executeScript(
//                "const select = arguments[0]; " +
//                        "for (let i = 0; i < select.options.length; i++) { " +
//                        "  if (select.options[i].text === arguments[1]) { " +
//                        "    select.selectedIndex = i; " +
//                        "    select.dispatchEvent(new Event('change')); " +
//                        "    break; " +
//                        "  }" +
//                        "}", customerSelect, customerName
//        );
//
//        // Set currency dropdown
//        js.executeScript(
//                "const select = arguments[0]; " +
//                        "for (let i = 0; i < select.options.length; i++) { " +
//                        "  if (select.options[i].text === arguments[1]) { " +
//                        "    select.selectedIndex = i; " +
//                        "    select.dispatchEvent(new Event('change')); " +
//                        "    break; " +
//                        "  }" +
//                        "}", currencySelect, currency
//        );
//
//        // Click the process button
//        processAccountBtn.click();
//    }


}
