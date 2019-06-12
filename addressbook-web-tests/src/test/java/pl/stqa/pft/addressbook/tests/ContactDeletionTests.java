package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectCheckboxContact();
        app.getContactHelper().clickDeleteButton();
        app.getContactHelper().clickOkAlertButton();
    }
}
