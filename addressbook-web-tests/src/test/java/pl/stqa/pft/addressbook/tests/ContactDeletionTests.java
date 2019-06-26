package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoContactPage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData
                    ("Test", "Test", null, "test.new@test73737.pl", "test1"));
        }
        app.returnToHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectCheckboxContact(before - 1);
        app.getContactHelper().clickDeleteButton();
        app.getContactHelper().clickOkAlertButton();
        //int after = app.getContactHelper().getContactCount();
        //Assert.assertEquals(after, before - 1);
    }
}
