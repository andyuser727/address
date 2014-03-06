package exercise.client.ui.contact;

import exercise.client.activity.contact.ContactDetailActivity;
import exercise.client.ui.BaseAbstractView;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class ContactDetailViewImpl extends BaseAbstractView {
    private static ContactDetailViewImplUiBinder uiBinder = GWT.create(ContactDetailViewImplUiBinder.class);

    interface ContactDetailViewImplUiBinder extends UiBinder<Widget, ContactDetailViewImpl> {
    }

    private Activity activity;

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected
    TextBox forename, surname, street, city, county, country, postalCode;

    public ContactDetailViewImpl() {

        initWidget(uiBinder.createAndBindUi(this));
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public TextBox getForename() {
        return forename;
    }

    public TextBox getSurname() {
        return surname;
    }

    public TextBox getStreet() {
        return street;
    }

    public TextBox getCity() {
        return city;
    }

    public TextBox getCounty() {
        return county;
    }

    public TextBox getCountry() {
        return country;
    }

    public TextBox getPostalCode() {
        return postalCode;
    }

    @UiHandler("btnExit")
    protected void btnExit(ClickEvent e) {
        ((ContactDetailActivity)getActivity()).doExit();
    }

    @UiHandler("btnSaveContact")
    protected void btnSaveContact(ClickEvent e) {
        ((ContactDetailActivity)getActivity()).doSaveContact();
    }
}
