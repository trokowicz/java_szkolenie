package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectCheckboxContact(int index) {
        //  click(By.name("selected[]"));
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void clickEditContact(int index) {
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
/*
    public void clickOkAlertButton() {
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }*/

    public void createContact(ContactData contact, boolean creation) {
        addNewContactPage();
       // initCreatingNewContact();
        fillNewContactForm(contact, creation);
        submitNewContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void modify(int index, ContactData contact) {
        selectCheckboxContact(index);
        clickEditContact(index);
        fillNewContactForm(contact, false);
        submitContactModification();

    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {

            List<WebElement> cells =  element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();

            ContactData contact = new ContactData(firstname, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
