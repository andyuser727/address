package exercise.service.contact;

import exercise.dao.contact.ContactDao;
import exercise.domain.Contact;
import exercise.service.Handler;
import exercise.shared.AppResponse;
import exercise.shared.commands.SearchContacts;
import exercise.shared.dto.contact.ContactDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Component
@Transactional
public class SearchContactsService implements Handler<SearchContacts> {

    @Autowired
    ContactDao contactDao;

    @Autowired
    ContactDtoBuilder contactDtoBuilder;

    public AppResponse execute(SearchContacts dto) throws IllegalArgumentException {

        // these handlers should really be separated from service logic

        AppResponse response = new AppResponse();

        // use dao to do the wild card search
        List<Contact> contacts = contactDao.searchContacts(dto.getForename(),
                dto.getSurname(),
                dto.getStreet(),
                dto.getCity(),
                dto.getCounty(),
                dto.getCountry(),
                dto.getPostalCode());

        List<ContactDetailDto> contactDetailDtos = new ArrayList<ContactDetailDto>();
        contactDetailDtos = contactDtoBuilder.buildDtos(contacts);
        // add the dtos to the response to be used by the celltable in the screen
        response.setDtos(contactDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return SearchContacts.class;
    }


}

