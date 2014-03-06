package exercise.service.contact;

import exercise.domain.Contact;
import exercise.shared.dto.contact.ContactDetailDto;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ContactDtoBuilderTest {

    private ContactDtoBuilder contactDtoBuilder = new ContactDtoBuilder();

    @Test
    public void testBuildDtos(){

        List<Contact> contacts = new ArrayList<Contact>();

        Contact contact1 = new Contact();
        Contact contact2 = new Contact();

        contact1.setForename("Andy");
        contact1.setSurname("Smith");
        contact1.setPostalCode("RG12 0UT");

        contact2.setForename("Chiara");
        contact2.setSurname("Pierucci");
        contact2.setPostalCode("RG12 0UT");

        contacts.add(contact1);
        contacts.add(contact2);

        List<ContactDetailDto> contactDetailDtos = contactDtoBuilder.buildDtos(contacts);

        assertEquals(contactDetailDtos.size(), 2);
    }

    @Test
    public void testBuildDto(){
        Contact contact1 = new Contact();

        contact1.setForename("Andy");
        contact1.setSurname("Smith");
        contact1.setPostalCode("RG12 0UT");

        ContactDetailDto contactDetailDto = contactDtoBuilder.buildDto(contact1);

        assertNotNull(contactDetailDto);
    }
}
