package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.goTo().homePage();

    Contacts before = app.contact().all();

    File photo = new File("src/test/resources/27.png");
    ContactData contact = new ContactData()
            .withFirstName("Maciej").withLastName("Kowalski").withTitle("Mr").withEmail("maciej.kowalski@gmail.io").withGroup("test1")
            .withPhoto(photo);
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
