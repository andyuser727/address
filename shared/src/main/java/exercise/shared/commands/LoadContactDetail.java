package exercise.shared.commands;

import exercise.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class LoadContactDetail extends RpcDto{

    Long contactId;

    public LoadContactDetail(){
    }

    public LoadContactDetail(Long id){
        this.contactId = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
