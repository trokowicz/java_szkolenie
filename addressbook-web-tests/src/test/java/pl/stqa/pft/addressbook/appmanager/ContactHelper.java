package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("title"), contactData.getTitle());
      click(By.name("email"));
      type(By.name("email"), contactData.getEmail());
    }

    public void type(By locator, String text) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void initCreatingNewContact() {
        click(By.name("firstname"));
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void addNewContactPage() {
        click(By.linkText("add new"));
    }

    public void submitNewContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectCheckboxContact() {
        click(By.id("3"));
    }

    public void clickEditContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='New'])[1]/following::img[2]"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }
}
