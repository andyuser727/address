package exercise.client.activity.contact;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.inject.Provider;
import exercise.client.activity.BaseAbstractActivity;
import exercise.client.place.ContactDetailPlace;
import exercise.client.ui.contact.ContactListViewImpl;
import exercise.shared.AppResponse;
import exercise.shared.AppServiceAsync;
import exercise.shared.commands.LoadContactList;
import exercise.shared.commands.SearchContacts;
import exercise.shared.dto.contact.ContactDetailDto;

import java.util.List;

public class ContactListActivity extends BaseAbstractActivity<ContactListViewImpl> {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private ListDataProvider<ContactDetailDto> dataProvider;
    List<ContactDetailDto> contactDetailDtos;
    private Provider<ContactDetailPlace> contactDetailPlaceProvider;

    @Inject
    public ContactListActivity(EventBus eventBus, ContactListViewImpl display,
                               PlaceController placeController,
                               AppServiceAsync appServiceAsync,
                               Provider<ContactDetailPlace> contactDetailPlaceProvider) {
        super(display);
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.contactDetailPlaceProvider = contactDetailPlaceProvider;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        container.setWidget(display.asWidget());
        loadContacts();
    }

    public void goToContactDetail(ContactDetailDto selected){
        ContactDetailPlace contactDetailPlace = contactDetailPlaceProvider.get();
        // TODO use constructor with map
        contactDetailPlace.getProperties().put("contact_id", String.valueOf(selected.getId()));
        placeController.goTo(contactDetailPlace);
    }

    public void doAddContact() {
        // go to the detail screen with no id to add a new contact
        ContactDetailPlace contactDetailPlace = contactDetailPlaceProvider.get();
        placeController.goTo(contactDetailPlace);
    }

    private void clearFields(){
        display.getForename().setValue(null);
        display.getSurname().setValue(null);
        display.getCity().setValue(null);
        display.getCounty().setValue(null);
        display.getCountry().setValue(null);
        display.getPostalCode().setValue(null);
    }

    public void doLoadContacts(){
        // clear the search criteria as we're loading all of them now
        clearFields();
        // load all contacts
        loadContacts();
    }

    public void doSearch() {
        // add all the partial string search criteria to the command
        SearchContacts searchContacts = new SearchContacts(display.getForename().getValue(),
                display.getSurname().getValue(),
                display.getStreet().getValue(),
                display.getCity().getValue(),
                display.getCounty().getValue(),
                display.getCountry().getValue(),
                display.getPostalCode().getValue());

        appServiceAsync.callServer(searchContacts,
                new AsyncCallback<AppResponse<ContactDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        caught.printStackTrace();
                        display.getTablePanel().setVisible(false);
                    }

                    public void onSuccess(AppResponse<ContactDetailDto> result) {
                        // set the screen data
                        contactDetailDtos = result.getDtos();
                        if (contactDetailDtos != null && contactDetailDtos.size() > 0) {
                            setComponents();
                            display.getTablePanel().setVisible(true);
                        }
                        else {
                            display.getTablePanel().setVisible(false);
                        }
                    }
                });
    }

    private void loadContacts() {
        LoadContactList loadContactList = new LoadContactList();

        appServiceAsync.callServer(loadContactList,
                new AsyncCallback<AppResponse<ContactDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        display.getTablePanel().setVisible(false);
                    }

                    public void onSuccess(AppResponse<ContactDetailDto> result) {
                        contactDetailDtos = result.getDtos();
                        if (contactDetailDtos != null && contactDetailDtos.size() > 0) {
                            setComponents();
                            display.getTablePanel().setVisible(true);
                        }
                    }
                });
    }

    private void setComponents() {
        // set the data into the table
        if (dataProvider == null) {
            dataProvider = new ListDataProvider<ContactDetailDto>();
            dataProvider.addDataDisplay(display.getTable());
        }

        dataProvider.getList().clear();
        dataProvider.getList().addAll(contactDetailDtos);
    }

    @Override
    public void onStop() {
        // remove the data from the table
        if (dataProvider != null && display.getTable() != null) {
            dataProvider.removeDataDisplay(display.getTable());
        }
    }

}
