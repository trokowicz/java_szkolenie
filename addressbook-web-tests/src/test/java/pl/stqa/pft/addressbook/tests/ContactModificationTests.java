package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData
                    ("Test", "Test", null, "test.new@test73737.pl", "test1"),true);
        }
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        int index = before.size()-1;

        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Modification", "Exercise", "Mr", "test.new@test7.pl", null);

        app.getContactHelper().modify(index, contact);
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }
}
