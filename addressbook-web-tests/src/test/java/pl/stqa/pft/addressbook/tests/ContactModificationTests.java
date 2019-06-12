package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectCheckboxContact();
        app.getContactHelper().clickEditContact();
        app.getContactHelper().fillNewContactForm(new ContactData("Modification", "Exercise", "Mr", "test.new@test7.pl"));
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
    }
}
