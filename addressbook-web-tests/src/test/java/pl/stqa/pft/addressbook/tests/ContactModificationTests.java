package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactPage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData
                    ("Test", "Test", null, "test.new@test73737.pl", "test1"));
        }
        app.returnToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectCheckboxContact(before - 1);
        app.getContactHelper().clickEditContact();
        app.getContactHelper().fillNewContactForm
                (new ContactData("Modification", "Exercise", "Mr", "test.new@test7.pl", null));
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
