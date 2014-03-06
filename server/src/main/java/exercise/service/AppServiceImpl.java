package exercise.service;

import exercise.shared.SimpleResponse;
import exercise.shared.dto.RpcDto;
import exercise.shared.AppResponse;
import exercise.shared.AppService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.Set;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Component
public class AppServiceImpl extends RemoteServiceServlet implements
        AppService, ServletContextAware {

    private ServletContext servletContext;

    private final Map<Class, Handler> handlers = new java.util.HashMap<Class, Handler>();
    private final Map<Class, SimpleHandler> simpleHandlers = new java.util.HashMap<Class, SimpleHandler>();

    // get spring to create a handler map so the app knows which handler to use with
    // which command
    @Autowired
    public AppServiceImpl(Set<Handler> handlerSet, Set<SimpleHandler> simpleHandlerSet) {
        for (Handler handler : handlerSet) {
            Class incomingCommandClass = handler.getIncomingCommandClass();
            handlers.put(incomingCommandClass, handler);
        }

        for (SimpleHandler handler : simpleHandlerSet) {
            Class incomingCommandClass = handler.getIncomingCommandClass();
            simpleHandlers.put(incomingCommandClass, handler);
        }
    }

    public AppResponse callServer(RpcDto dto) {
        Handler handler = handlers.get(dto.getClass());
        AppResponse response = null;
        try {
            response = handler.execute(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public SimpleResponse callServerSimple(RpcDto dto) {
        SimpleHandler handler = simpleHandlers.get(dto.getClass());
        SimpleResponse response = null;
        try {
            response = handler.executeSimple(dto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

