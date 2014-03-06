package exercise.client.ioc;

import exercise.client.mvp.AppActivityMapper;
import exercise.client.mvp.AppPlaceFactory;
import exercise.client.panel.ApplicationContainerPanel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

@GinModules({InjectionModule.class})
public interface Injector extends Ginjector {

    AppPlaceFactory getAppPlaceFactory();

    EventBus getEventBus();

    AppActivityMapper getActivityMapper();

    PlaceController getPlaceController();

    ApplicationContainerPanel getApplicationContainerPanel();
}