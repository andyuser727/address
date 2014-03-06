package exercise.service.contact;

import exercise.domain.Contact;
import exercise.shared.dto.contact.ContactDetailDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactDtoBuilder {

    public List<ContactDetailDto> buildDtos(List<Contact> contacts){

        List<ContactDetailDto> contactDetailDtos = new ArrayList<ContactDetailDto>();
        // loop through all found contacts and build a dto for each
        for (Contact contact : contacts){
            ContactDetailDto contactDetailDto = new ContactDetailDto();
            contactDetailDto.setId(contact.getId());
            contactDetailDto.setCity(contact.getCity());
            contactDetailDto.setCountry(contact.getCountry());
            contactDetailDto.setForename(contact.getForename());
            contactDetailDto.setStreet(contact.getStreet());
            contactDetailDto.setSurname(contact.getSurname());
            contactDetailDto.setPostalCode(contact.getPostalCode());
            contactDetailDto.setCounty(contact.getCounty());

            contactDetailDtos.add(contactDetailDto);
        }

        return contactDetailDtos;
    }

    public ContactDetailDto buildDto(Contact contact){

        ContactDetailDto contactDetailDto = new ContactDetailDto();
        // set the dto values from the persisted contact
        contactDetailDto.setId(contact.getId());
        contactDetailDto.setCity(contact.getCity());
        contactDetailDto.setCountry(contact.getCountry());
        contactDetailDto.setForename(contact.getForename());
        contactDetailDto.setStreet(contact.getStreet());
        contactDetailDto.setSurname(contact.getSurname());
        contactDetailDto.setPostalCode(contact.getPostalCode());
        contactDetailDto.setCounty(contact.getCounty());

        return contactDetailDto;
    }
}
