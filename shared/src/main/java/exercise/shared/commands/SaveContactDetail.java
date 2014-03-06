package exercise.shared.commands;

import exercise.shared.dto.contact.ContactDetailDto;
import exercise.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveContactDetail extends RpcDto {

    ContactDetailDto contactDetailDto;

    public SaveContactDetail(){}

    public SaveContactDetail(ContactDetailDto contactDetailDto){
        this.contactDetailDto = contactDetailDto;
    }

    public ContactDetailDto getContactDetailDto() {
        return contactDetailDto;
    }
}
