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
        if (app.contact().count() == 0) {
        app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstName("New").withLastName("Before modification").withTitle("Mrs")
                    .withEmail("test.brown@email.io")
                    .withHomeTel("111222333").withMobileTel("444333666").withWorkTel("454346452")
                    .withEmail2("second@email.io").withEmail3("third@email.io").withAddress2("Malediwy 666"), true);
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all2().iterator().next();
        ContactData contactInfoFromEditForm =  app.contact().infoFromEditForm(contact);
        app.goTo().homePage();
        //assertThat(contact.getAllDetails(), equalTo(mergeDetails(contactInfoFromEditForm)));
        assertThat(app.contact().dataFromDetailsForm(contact).replaceAll("(?m)^[ \t]*\r?\n", "").replaceAll("[\\n\\r]" +
                        ".*Member of:\\s*([^\\n\\r]*)", ""),
                equalTo(mergeDetails(contactInfoFromEditForm)));
    }

    private String mergeDetails(ContactData contactData) {
        return Arrays.asList(contactData.getFirstName()+" "+ " " + contactData.getLastName(),
                contactData.getAddress(),
                "H: " + contactData.getHomePhone(), "M: " +  contactData.getMobilePhone(),  "W: " +  contactData.getWorkPhone(),
                contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3(),
                contactData.getAddress2()).stream().filter((s) -> !s.equals(""))
                .map(ContactDetailsTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replaceAll("[ \\f\\t\\v]+$", "").replaceAll("[ ]{2,}", " ");
    }
}

