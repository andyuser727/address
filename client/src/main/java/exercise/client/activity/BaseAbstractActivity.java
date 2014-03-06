package exercise.client.activity;

import exercise.client.place.ActivityPlace;
import exercise.client.ui.BaseAbstractView;
import com.google.gwt.activity.shared.AbstractActivity;

import java.util.Map;

public abstract class BaseAbstractActivity<V extends BaseAbstractView> extends AbstractActivity{

    private Map<String, String> properties;
    protected final V display;

    // bind the view and the activity
    public BaseAbstractActivity(V display){
        this.display = display;
        display.setActivity(this);
    }

    public void init(ActivityPlace place) {
        this.properties = place.getProperties();
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
