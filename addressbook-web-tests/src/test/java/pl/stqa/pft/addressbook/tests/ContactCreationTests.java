package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testNewContact() throws Exception {

    app.getNavigationHelper().gotoHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();

    ContactData contact = new ContactData("Test", "Test", null, "test.new@test73737.pl", "test1");
    app.getContactHelper().createContact(contact, true);

    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
