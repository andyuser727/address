package exercise.shared;

import exercise.shared.dto.RpcDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AppService</code>.
 */
public interface AppServiceAsync {

    // two callback types. one returns data. one just saves. some people think you
    // should always at least return an id
    <T extends RpcDto> void callServer(RpcDto cmd, AsyncCallback<AppResponse<T>> callback);

    void callServerSimple(RpcDto cmd, AsyncCallback<SimpleResponse> callback);
}
