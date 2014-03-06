package exercise.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ApplicationContainerPanel extends Composite {
    interface Binder extends UiBinder<Widget, ApplicationContainerPanel> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    protected DockLayoutPanel mainDockPanel;

    @UiField
    protected FlowPanel headerPanel;

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected Image addressLogo;

    @Inject
    public ApplicationContainerPanel() {

        initWidget(binder.createAndBindUi(this));

        addressLogo.setUrl("/exercise/address.png");
        addressLogo.setHeight("50px");
        addressLogo.setWidth("50px");

        contentWrapperPanel.setSize("1400px", "1000px");
    }

    public AcceptsOneWidget getContentWrapperPanel() {
        return new AcceptsOneWidget() {
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                contentWrapperPanel.setWidget(widget);
            }
        };
    }
}
