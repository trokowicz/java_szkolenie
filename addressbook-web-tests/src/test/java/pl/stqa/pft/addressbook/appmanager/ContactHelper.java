package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;
import pl.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void addNewContactPage() {
        click(By.linkText("add new"));
    }

    public void submitNewContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.xpath("//input[@value='" + id + "']")).click();
    }

    public void clickEditContact(int id) {
        wd.findElement(By.cssSelector("[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void clickDeleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact, boolean creation) {
        addNewContactPage();
        fillNewContactForm(contact, creation);
        submitNewContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        clickEditContact(contact.getId());
        fillNewContactForm(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickEditContact(contact.getId());
        clickDeleteButton();
        contactCache = null;
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {

            List<WebElement> cells =  element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();

            contacts.add(new ContactData().withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {

            List<WebElement> cells =  element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            Integer id= Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return new Contacts(contactCache);
    }
}
