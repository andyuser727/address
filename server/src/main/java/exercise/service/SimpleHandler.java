package exercise.service;

import exercise.shared.SimpleResponse;
import exercise.shared.dto.RpcDto;

public interface SimpleHandler<T extends RpcDto> {
    public Class getIncomingCommandClass();
    public SimpleResponse executeSimple(T dto) throws Exception;

}
