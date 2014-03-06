package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.domain.Contact;
import exercise.shared.AppResponse;
import exercise.shared.commands.LoadContactDetail;
import exercise.shared.dto.contact.ContactDetailDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

public class LoadContactDetailServiceTest {

    @Mock
    private ContactDao contactDao;

    @Mock
    private ContactDtoBuilder contactDtoBuilder;

    @InjectMocks
    private LoadContactDetailService loadContactDetailService = new LoadContactDetailService();

    @BeforeMethod(alwaysRun = true)
    public void injectMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {

        Contact contact1 = new Contact();

        contact1.setForename("Andy");
        contact1.setSurname("Smith");
        contact1.setPostalCode("RG12 0UT");

        ContactDetailDto contactDetailDto = new ContactDetailDto();

        when(contactDao.find(anyLong())).thenReturn(contact1);
        when(contactDtoBuilder.buildDto(contact1)).thenReturn(contactDetailDto);

        LoadContactDetail loadContactDetail = new LoadContactDetail();
        AppResponse<ContactDetailDto> appResponse = loadContactDetailService.execute(loadContactDetail);

        assertNotNull(appResponse.getDto());

    }


}