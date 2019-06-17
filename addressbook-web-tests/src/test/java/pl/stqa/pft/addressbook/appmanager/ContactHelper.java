package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("title"), contactData.getTitle());
      click(By.name("email"));
      type(By.name("email"), contactData.getEmail());

      if (creation) {
          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
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
        click(By.name("selected[]"));
    }

    public void clickEditContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void clickDeleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickOkAlertButton() {
        wd.switchTo().alert().accept();
    }
}
