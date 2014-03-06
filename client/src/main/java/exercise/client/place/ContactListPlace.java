package exercise.client.place;

import exercise.client.activity.contact.ContactListActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ContactListPlace extends ActivityPlace<ContactListActivity> {

    @Inject
    public ContactListPlace(ContactListActivity activity) {
        super(activity);
    }

    @Prefix("contact_list")
    public static class Tokenizer extends AppTokenizer<ContactListPlace> implements PlaceTokenizer<ContactListPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<ContactListPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<ContactListPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public ContactListPlace getPlace(String token) {
            ContactListPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}