package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();

        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData
                    ("Test", "Test", null, "test.new@test73737.pl", "test1"),true);
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() -1;

        app.getContactHelper().delete(index);
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
        }
    }

