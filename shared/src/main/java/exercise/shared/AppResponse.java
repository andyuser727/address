package exercise.shared;

import exercise.shared.dto.RpcDto;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AppResponse<T extends RpcDto> implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;

    private List<T> dtos;

    private T dto;

    private ArrayList<String> validationMessages;

    public AppResponse(){
    }

    public List<T> getDtos() {
        return dtos;
    }

    public void setDtos(List<T> dtos) {
        this.dtos = dtos;
    }

    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }

    // this would take validation messages to display in the screen
    public ArrayList<String> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(ArrayList<String> validationMessages) {
        this.validationMessages = validationMessages;
    }
}