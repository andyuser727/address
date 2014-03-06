package exercise.client;

import exercise.client.ioc.Injector;
import exercise.client.mvp.AppPlaceFactory;
import exercise.client.mvp.AppPlaceHistoryMapper;
import exercise.client.panel.ApplicationContainerPanel;
import exercise.client.place.ContactListPlace;
import exercise.shared.AppService;
import exercise.shared.AppServiceAsync;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Exercise implements EntryPoint
{
    private final AppServiceAsync greetingService = GWT
            .create(AppService.class);

    private Injector injector = GWT.create(Injector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
        EventBus eventBus = injector.getEventBus();
        PlaceController placeController = injector.getPlaceController();

        final ApplicationContainerPanel applicationContainerPanel = injector.getApplicationContainerPanel();

        ActivityMapper activityMapper = injector.getActivityMapper();
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(applicationContainerPanel.getContentWrapperPanel());

        AppPlaceFactory factory = injector.getAppPlaceFactory();
        ContactListPlace defaultPlace = factory.getContactListPlace();

        final AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        historyMapper.setFactory(factory);

        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        History.fireCurrentHistoryState();

        RootLayoutPanel.get().add(applicationContainerPanel);
	}
}
