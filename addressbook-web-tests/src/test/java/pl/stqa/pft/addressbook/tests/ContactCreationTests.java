package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    //app.getContactHelper().selectCheckboxContact();
    app.getContactHelper().createContact(new ContactData
            ("Test", "Test", null, "test.new@test73737.pl", "test1"));

  }
}
