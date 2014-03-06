package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.domain.Contact;
import exercise.shared.AppResponse;
import exercise.shared.commands.LoadContactList;
import exercise.shared.dto.contact.ContactDetailDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class LoadContactListServiceTest {

    @Mock
    private ContactDao contactDao;

    @Mock
    private ContactDtoBuilder contactDtoBuilder;

    @InjectMocks
    private LoadContactListService loadContactListService = new LoadContactListService();

    @BeforeMethod(alwaysRun = true)
    public void injectMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {

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

        List<ContactDetailDto> contactDetailDtos = new ArrayList<ContactDetailDto>();

        ContactDetailDto contactDetailDto1 = new ContactDetailDto();
        ContactDetailDto contactDetailDto2 = new ContactDetailDto();
        contactDetailDtos.add(contactDetailDto1);
        contactDetailDtos.add(contactDetailDto2);

        when(contactDao.findAll()).thenReturn(contacts);
        when(contactDtoBuilder.buildDtos(contacts)).thenReturn(contactDetailDtos);

        LoadContactList loadContactList = new LoadContactList();
        AppResponse<ContactDetailDto> appResponse = loadContactListService.execute(loadContactList);

        assertEquals(appResponse.getDtos().size(), 2);

    }


}