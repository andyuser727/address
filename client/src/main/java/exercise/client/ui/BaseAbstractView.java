package exercise.client.ui;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.Composite;

public class BaseAbstractView extends Composite {
    protected Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
