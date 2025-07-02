package org.xyzbank.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Add Customer Form Page Object
public class AddCustomerPage extends BasePage{

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeInput;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Add Customer')]")
    private WebElement addCustomerSubmit;

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getFirstNameField() {
        return firstNameInput;
    }

    public WebElement getLastNameField() {
        return lastNameInput;
    }

    public WebElement getPostCodeField() {
        return postCodeInput;
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);
        addCustomerSubmit.click();
    }
}
