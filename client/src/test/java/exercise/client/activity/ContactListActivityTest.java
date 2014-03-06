package exercise.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provider;
import exercise.client.activity.contact.ContactListActivity;
import exercise.client.ui.contact.ContactListViewImpl;
import exercise.shared.AppServiceAsync;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class ContactListActivityTest {

    @BeforeClass
    public void initializeMockPresentation() {
        GWTMockUtilities.disarm();
    }

    @AfterClass
    public void reEnableWidgets() {
        GWTMockUtilities.restore();
    }

    @Test
    public void testSomeMethod() {

        ContactListActivity contactListActivity = new ContactListActivity(mock(EventBus.class), mock(ContactListViewImpl.class),
                mock(PlaceController.class), mock(AppServiceAsync.class), mock(Provider.class));

        // I'm afraid this is just a skeleton, but you might get the idea

    }
}
