package exercise.client.ui.contact;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import exercise.client.activity.contact.ContactListActivity;
import exercise.shared.dto.contact.ContactDetailDto;
import exercise.client.ui.BaseAbstractView;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;

public class ContactListViewImpl extends BaseAbstractView {
    private static ContactListViewImplUiBinder uiBinder = GWT.create(ContactListViewImplUiBinder.class);

    interface ContactListViewImplUiBinder extends UiBinder<Widget, ContactListViewImpl> {
    }

    private Activity activity;

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected SimplePager pager;

    @UiField
    protected FlowPanel tablePanel, searchPanel;

    private CellTable<ContactDetailDto> table;

    @UiField
    protected
    TextBox forename, surname, street, city, county, country, postalCode;

    public ContactListViewImpl() {
        createContactTable();
        initWidget(uiBinder.createAndBindUi(this));
        tablePanel.add(table);
        tablePanel.add(pager);
        tablePanel.setVisible(false);
        pager.setDisplay(table);
    }

    private void createContactTable() {

        // Create the cell table. Headings should really be done with internationalized mechanism

        table = new CellTable<ContactDetailDto>();

        TextColumn<ContactDetailDto> forenameColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getForename();
            }
        };

        table.addColumn(forenameColumn, "First Name");

        TextColumn<ContactDetailDto> surnameColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getSurname();
            }
        };

        table.addColumn(surnameColumn, "Surname");

        TextColumn<ContactDetailDto> streetColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getStreet();
            }
        };

        table.addColumn(streetColumn, "Street");

        TextColumn<ContactDetailDto> cityColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getCity();
            }
        };

        table.addColumn(cityColumn, "City");

        TextColumn<ContactDetailDto> countyColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getCounty();
            }
        };

        table.addColumn(countyColumn, "County");

        TextColumn<ContactDetailDto> countryColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getCountry();
            }
        };

        table.addColumn(countryColumn, "Country");

        TextColumn<ContactDetailDto> postalCodeColumn = new TextColumn<ContactDetailDto>() {
            @Override
            public String getValue(ContactDetailDto dto) {
                return dto.getPostalCode();
            }
        };

        table.addColumn(postalCodeColumn, "Postcode");

        // add the click through. go to detail screen
        final SingleSelectionModel<ContactDetailDto> selectionModel = new SingleSelectionModel<ContactDetailDto>();
        table.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                ContactDetailDto selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    ((ContactListActivity)getActivity()).goToContactDetail(selected);
                }
            }
        });
    }

    public CellTable<ContactDetailDto> getTable() {
        return table;
    }

    public FlowPanel getTablePanel() {
        return tablePanel;
    }

    public SimplePager getPager() {
        return pager;
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

    @UiHandler("btnAdd")
    protected void btnAdd(ClickEvent e) {
        ((ContactListActivity)getActivity()).doAddContact();
    }

    @UiHandler("btnSearch")
    protected void btnSearch(ClickEvent e) {
        ((ContactListActivity)getActivity()).doSearch();
    }

    @UiHandler("btnShowAll")
    protected void btnShowAll(ClickEvent e) {
        ((ContactListActivity)getActivity()).doLoadContacts();
    }
}
