package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

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

        List<ContactData> before = app.contact().list();

        int index = before.size()-1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId())
                .withFirstName("Hanna").withLastName("Brown").withTitle("Mrs").withEmail("hanna.brown@email.io").withGroup("test1");

        app.contact().modify(index, contact);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }
}
