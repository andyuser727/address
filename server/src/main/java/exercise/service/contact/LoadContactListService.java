package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.domain.Contact;
import exercise.service.Handler;
import exercise.shared.AppResponse;
import exercise.shared.commands.LoadContactList;
import exercise.shared.dto.contact.ContactDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadContactListService implements Handler<LoadContactList> {

    @Autowired
    ContactDao contactDao;

    @Autowired
    ContactDtoBuilder contactDtoBuilder;

    public AppResponse<ContactDetailDto> execute(LoadContactList dto) throws IllegalArgumentException {
        // could've had the same handler for the partial search. decided not to somehow
        // then REALLY decided not to down to time.
        // anyway... would've meant a bit more logic in this, and i definitely wouldn't have reused the
        // same query.
        AppResponse response = new AppResponse();

        // these handlers should really be separated from service logic

        // use dao to fid all the contacts
        List<Contact> contacts = contactDao.findAll();

        List<ContactDetailDto> contactDetailDtos = new ArrayList<ContactDetailDto>();
        contactDetailDtos = contactDtoBuilder.buildDtos(contacts);
        // add the dtos to the response to be used by the celltable in the screen
        response.setDtos(contactDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass(){
        return LoadContactList.class;
    }


}

