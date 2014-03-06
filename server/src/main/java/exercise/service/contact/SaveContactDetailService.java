package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.domain.Contact;
import exercise.service.SimpleHandler;
import exercise.shared.SimpleResponse;
import exercise.shared.commands.SaveContactDetail;
import exercise.shared.dto.contact.ContactDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Component
@Transactional
public class SaveContactDetailService implements SimpleHandler<SaveContactDetail> {

    @Autowired
    private ContactDao contactDao;

    public SimpleResponse executeSimple(SaveContactDetail dto) {

        // these handlers should really be separated from service logic

        SimpleResponse response = new SimpleResponse();

        ContactDetailDto contactDetailDto = dto.getContactDetailDto();
        Contact contact;
        if (contactDetailDto.getId() != null) {
            contact = contactDao.find(contactDetailDto.getId());
        } else {
            contact = new Contact();
            contactDao.save(contact);
        }
        contact.setForename(contactDetailDto.getForename());
        contact.setSurname(contactDetailDto.getSurname());
        contact.setStreet(contactDetailDto.getStreet());
        contact.setCity(contactDetailDto.getCity());
        contact.setCounty(contactDetailDto.getCounty());
        contact.setCountry(contactDetailDto.getCountry());
        contact.setPostalCode(contactDetailDto.getPostalCode());


    return response;
}

    public Class getIncomingCommandClass() {
        return SaveContactDetail.class;
    }


}

