package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl.stqa.pft.addressbook.tests.TestBase.app;

public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all2().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("New").withLastName("Before modification").withTitle("Mrs")
                    .withEmail("test.brown@email.io").withGroup("test1")
                    .withHomeTel("111222333").withMobileTel("444333666").withWorkTel("454346452")
                    .withEmail2("second@email.io").withEmail3("third@email.io"), true);
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm =  app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllDetails(), equalTo(mergeDetails(contactInfoFromEditForm)));
    }

    private String mergeDetails(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()
                , contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> !s.equals(""))
                .map(ContactDetailsTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String details) {
        return details.replaceAll("\\s", "").replaceAll("[-()]", " ");
    }
}

