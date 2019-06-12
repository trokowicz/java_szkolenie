package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.stqa.pft.addressbook.model.NewContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewContactForm(NewContactData newContactData) {
      type(By.name("firstname"), newContactData.getFirstName());
      type(By.name("lastname"), newContactData.getLastName());
      type(By.name("title"), newContactData.getTitle());
      click(By.name("email"));
      type(By.name("email"), newContactData.getEmail());
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
}
