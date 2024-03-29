package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

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
        //attach(By.name("photo"), contactData.getPhoto());

      if (creation) {
          if (contactData.getGroups().size() > 0); {
              Assert.assertTrue(contactData.getGroups().size() == 1);
              new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
          }
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

    public String dataFromDetailsForm(ContactData contactData){
        detailsViewById(contactData.getId());
        return wd.findElement(By.id("content")).getText();

    }

    private void detailsViewById(int id) {
        wd.findElement(By.cssSelector("[href='view.php?id=" + id + "']")).click();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homeTel = wd.findElement(By.name("home")).getAttribute("value");
        String mobileTel = wd.findElement(By.name("mobile")).getAttribute("value");
        String workTel = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String address2 = wd.findElement(By.name("address2")).getAttribute("value");
        wd.navigate().back();
        return contact = new ContactData().withFirstName(firstName).withLastName(lastName)
                .withHomeTel(homeTel).withMobileTel(mobileTel).withWorkTel(workTel)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address).withAddress2(address2);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
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

    public Set<ContactData> all2()
    {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows)
        {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value")) ;
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allAdresses = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAllAdresses(allAdresses));
        }
        return contacts;
    }
}
