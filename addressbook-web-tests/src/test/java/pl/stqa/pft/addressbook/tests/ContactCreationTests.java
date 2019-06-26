package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData
            ("Test", "Test", null, "test.new@test73737.pl", "test1"));
    app.returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
