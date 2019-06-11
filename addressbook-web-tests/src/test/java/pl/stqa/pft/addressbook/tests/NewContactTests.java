package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.NewContactData;

public class NewContactTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().addNewContactPage();
    app.getContactHelper().initCreatingNewContact();
    app.getContactHelper().fillNewContactForm(new NewContactData("New", "Test", "Mrs", "test.new@test73737.pl"));
    app.getContactHelper().submitNewContactCreation();
    app.returnToHomePage();
    app.logout();
  }
}
