package exercise.client.activity.contact;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import exercise.client.activity.BaseAbstractActivity;
import exercise.client.place.ContactListPlace;
import exercise.client.ui.contact.ContactDetailViewImpl;
import exercise.shared.AppResponse;
import exercise.shared.AppServiceAsync;
import exercise.shared.SimpleResponse;
import exercise.shared.commands.LoadContactDetail;
import exercise.shared.commands.SaveContactDetail;
import exercise.shared.dto.contact.ContactDetailDto;

public class ContactDetailActivity extends BaseAbstractActivity<ContactDetailViewImpl> {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private Provider<ContactListPlace> contactListPlaceProvider;
    private ContactDetailDto contactDetailDto;
    private String contactId;

    @Inject
    public ContactDetailActivity(EventBus eventBus, ContactDetailViewImpl display,
                                 PlaceController placeController,
                                 AppServiceAsync appServiceAsync,
                                 Provider<ContactListPlace> contactListPlaceProvider) {
        super(display);
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.contactListPlaceProvider = contactListPlaceProvider;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        container.setWidget(display.asWidget());
        // should be using some kind of place data mechanism here.
        // get the id from the place props if there is one. load the contact, or allow new creation
        contactId = getProperties().get("contact_id");
        if (contactId != null) {
            loadContactDetails(Long.valueOf(contactId));
        } else {
            contactDetailDto = new ContactDetailDto();
        }
    }

    private void loadContactDetails(Long contactId) {

        // simple load command with the contact id
        LoadContactDetail loadContactDetail = new LoadContactDetail(contactId);

        // make the callback
        appServiceAsync.callServer(loadContactDetail,
                new AsyncCallback<AppResponse<ContactDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        // there should be some eye pleasing panel
                        caught.printStackTrace();
                    }

                    public void onSuccess(AppResponse<ContactDetailDto> result) {
                        // there should be some eye pleasing panel for any validation problems
                        contactDetailDto = result.getDto();
                        setData();
                    }
                });
    }

    private void setData() {
        // set the corresponding screen field values from the dto
        display.getForename().setValue(contactDetailDto.getForename());
        display.getSurname().setValue(contactDetailDto.getSurname());
        display.getStreet().setValue(contactDetailDto.getStreet());
        display.getCity().setValue(contactDetailDto.getCity());
        display.getCounty().setValue(contactDetailDto.getCounty());
        display.getCountry().setValue(contactDetailDto.getCountry());
        display.getPostalCode().setValue(contactDetailDto.getPostalCode());
    }

    public void doExit() {
        // go back to the list screen
        placeController.goTo(contactListPlaceProvider.get());
    }

    public void doSaveContact() {
        // need to have at least some validation on this. so i'm going
        // to enforce at least a name and postcode.
        // with a bit more time i'd do an eye pleasing message panel
        if (validate()) {
            // save the contact. set the id so if its and existing contact
            ContactDetailDto contactDetailDto = new ContactDetailDto();
            if (contactId != null) {
                contactDetailDto.setId(Long.valueOf(contactId));
            }
            contactDetailDto.setForename(display.getForename().getValue());
            contactDetailDto.setSurname(display.getSurname().getValue());
            contactDetailDto.setCity(display.getCity().getValue());
            contactDetailDto.setCounty(display.getCounty().getValue());
            contactDetailDto.setCountry(display.getCountry().getValue());
            contactDetailDto.setStreet(display.getStreet().getValue());
            contactDetailDto.setPostalCode(display.getPostalCode().getValue());

            // add dto to the command to sen to the server
            SaveContactDetail saveContactDetail = new SaveContactDetail(contactDetailDto);

            // make the callback
            appServiceAsync.callServerSimple(saveContactDetail,
                    new AsyncCallback<SimpleResponse>() {
                        public void onFailure(Throwable caught) {
                            // Show the RPC error message to the user
                            Window.alert("Error Saving Contact Details");
                            caught.printStackTrace();
                        }

                        public void onSuccess(SimpleResponse result) {
                            ContactDetailActivity.this.placeController.goTo(contactListPlaceProvider.get());
                        }
                    });

        } else {
            Window.alert("Please enter at least a name and a postcode");
        }
    }

    private boolean validate() {
        return (!display.getForename().getValue().equals("")
                && !display.getSurname().getValue().equals("")
                && !display.getPostalCode().getValue().equals(""));
    }


}
