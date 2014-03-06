package exercise.client.place;

import exercise.client.activity.contact.ContactDetailActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ContactDetailPlace extends ActivityPlace<ContactDetailActivity> {

    @Inject
    public ContactDetailPlace(ContactDetailActivity activity) {
        super(activity);
    }

    @Prefix("contact_detail")
    public static class Tokenizer extends AppTokenizer<ContactDetailPlace> implements PlaceTokenizer<ContactDetailPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<ContactDetailPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<ContactDetailPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public ContactDetailPlace getPlace(String token) {
            ContactDetailPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }
    }
}