package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("New").withLastName("Before modification").withTitle("Mrs").withEmail("test.brown@email.io").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Hanna").withLastName("Brown").withTitle("Mrs").withEmail("hanna.brown@email.io").withGroup("test1");

        app.contact().modify(contact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }
}
