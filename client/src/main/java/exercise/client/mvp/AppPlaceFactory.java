package exercise.client.mvp;

import exercise.client.place.*;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AppPlaceFactory {

    @Inject
    ContactListPlace.Tokenizer contactListPlaceTokenizer;
    @Inject
    ContactDetailPlace.Tokenizer contactDetailPlaceTokenizer;

    @Inject
    Provider<ContactListPlace> contactListPlaceProvider;
    @Inject
    Provider<ContactDetailPlace> contactDetailPlaceProvider;

    public ContactListPlace.Tokenizer getContactListPlaceTokenizer() {
        return contactListPlaceTokenizer;
    }

    public ContactListPlace getContactListPlace() {
        return contactListPlaceProvider.get();
    }

    public ContactDetailPlace.Tokenizer getContactDetailPlaceTokenizer() {
        return contactDetailPlaceTokenizer;
    }

    public ContactDetailPlace getContactDetailPlace() {
        return contactDetailPlaceProvider.get();
    }
}
