package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.shared.commands.SaveContactDetail;
import exercise.shared.dto.contact.ContactDetailDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SaveContactDetailServiceTest {

    @Mock
    private ContactDao contactDao;

    @InjectMocks
    private SaveContactDetailService saveContactDetailService = new SaveContactDetailService();

    @BeforeMethod(alwaysRun = true)
    public void injectMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteSimple() {
        ContactDetailDto contactDetailDto = new ContactDetailDto();
        contactDetailDto.setForename("Andy");
        SaveContactDetail saveContactDetail = new SaveContactDetail(contactDetailDto);
        saveContactDetailService.executeSimple(saveContactDetail);
    }


}