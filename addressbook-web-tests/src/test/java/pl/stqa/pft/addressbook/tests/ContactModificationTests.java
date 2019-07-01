package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactPage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData
                    ("Test", "Test", null, "test.new@test73737.pl", "test1"),true);
        }
        app.returnToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectCheckboxContact(before.size() - 1);
        app.getContactHelper().clickEditContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Modification", "Exercise", "Mr", "test.new@test7.pl", null);
        app.getContactHelper().fillNewContactForm(contact, true);
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
