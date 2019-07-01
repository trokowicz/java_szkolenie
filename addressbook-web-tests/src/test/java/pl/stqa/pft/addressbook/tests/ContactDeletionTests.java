package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData
                    ("Test", "Test", null, "test.new@test73737.pl", "test1"),true);
        }
        app.returnToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectCheckboxContact(before.size() - 1);
        app.getContactHelper().clickDeleteButton();
        app.getContactHelper().clickOkAlertButton();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
        }
    }

