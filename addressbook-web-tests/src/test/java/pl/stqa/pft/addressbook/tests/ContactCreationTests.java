package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData()
            .withLastName("Lastname1").withFirstName("Firstname1").withTitle("Mr").withEmail("email@test1.com").withGroup("test1")});
    list.add(new Object[] {new ContactData()
            .withLastName("Lastname2").withFirstName("Firstname2").withTitle("Mr").withEmail("email@test2.com").withGroup("test2")});
    list.add(new Object[] {new ContactData()
            .withLastName("Lastname3").withFirstName("Firstname3").withTitle("Mr").withEmail("email@test3.com").withGroup("test3")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testNewContact(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false) //checking absolute file path & checking if file exists
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/27.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
