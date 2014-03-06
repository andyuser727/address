package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.domain.Contact;
import exercise.service.Handler;
import exercise.shared.AppResponse;
import exercise.shared.commands.LoadContactDetail;
import exercise.shared.dto.contact.ContactDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadContactDetailService implements Handler<LoadContactDetail> {

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private ContactDtoBuilder contactDtoBuilder;

    public AppResponse execute(LoadContactDetail dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();
        // use dao to load the persisted contact
        Contact contact = contactDao.find(dto.getContactId());
        ContactDetailDto contactDetailDto = new ContactDetailDto();
        // build the dto
        contactDetailDto = contactDtoBuilder.buildDto(contact);

        response.setDto(contactDetailDto);

        return response;
    }

    public Class getIncomingCommandClass(){
        return LoadContactDetail.class;
    }



}

