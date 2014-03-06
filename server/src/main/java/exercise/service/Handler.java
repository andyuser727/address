package exercise.service;

import exercise.shared.dto.RpcDto;
import exercise.shared.AppResponse;

public interface Handler<T extends RpcDto> {
    public Class getIncomingCommandClass();
    public AppResponse execute(T dto) throws Exception;

}
