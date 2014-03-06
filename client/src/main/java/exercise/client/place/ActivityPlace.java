package exercise.client.place;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import java.util.HashMap;
import java.util.Map;

public abstract class ActivityPlace<T extends Activity> extends Place {

    public Map<String, String> properties = new HashMap<String, String>();

    protected T activity;

    public ActivityPlace(T activity) {
        this.activity = activity;
    }

    public T getActivity() {
        return activity;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
