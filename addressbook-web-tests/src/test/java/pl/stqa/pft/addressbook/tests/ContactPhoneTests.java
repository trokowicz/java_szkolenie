package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all2().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("New").withLastName("Before modification").withTitle("Mrs")
                    .withEmail("test.brown@email.io").withGroup("test1")
                    .withHomeTel("111222333").withMobileTel("444333666").withWorkTel("454346452"), true);
        }
    }

    @Test

    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all2().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomeTel(), equalTo(contactInfoFromEditForm.getHomeTel()));
        assertThat(contact.getMobileTel(), equalTo(contactInfoFromEditForm.getMobileTel()));
        assertThat(contact.getWorkTel(), equalTo(contactInfoFromEditForm.getWorkTel()));
    }
}
