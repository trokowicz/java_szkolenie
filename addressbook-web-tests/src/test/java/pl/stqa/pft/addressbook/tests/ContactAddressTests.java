package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all2().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("New").withLastName("Before modification").withTitle("Mrs")
                    .withEmail("test.brown@email.io")
                    .withHomeTel("111222333").withMobileTel("444333666").withWorkTel("454346452")
                    .withEmail2("second@email.io").withEmail3("third@email.io")
                    .withAddress("Warsaw, Marszalkowska street").withAddress("Warsaw, Swietokrzyska street"), true);
        }
    }

    @Test

    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all2().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllAdresses(), equalTo(mergeAdresses(contactInfoFromEditForm)));
    }

    private String mergeAdresses(ContactData contact) {
        return Arrays.asList(contact.getAddress(), contact.getAddress2())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactAddressTests:: cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String address) {
        return address.replaceAll("[()]","");
    }
}