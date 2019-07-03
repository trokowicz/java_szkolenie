package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {

    app.goTo().homePage();

    Set<ContactData> before = app.contact().all();

    ContactData contact = new ContactData()
            .withFirstName("Maciej").withLastName("Kowalski").withTitle("Mr").withEmail("maciej.kowalski@gmail.io").withGroup("test1");
    app.contact().create(contact, true);

    app.goTo().homePage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    //contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
