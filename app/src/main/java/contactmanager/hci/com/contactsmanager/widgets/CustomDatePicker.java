package contactmanager.hci.com.contactsmanager.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;

/**
 * Created by darshan bidkar and darshan reddy on 3/21/16.
 */
public class CustomDatePicker extends DatePicker {
    public CustomDatePicker(Context context) {
        super(context);
    }

    /*
    *
    * @author: darshan reddy
    * */
    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*
    *
    * @author: darshan reddy
    * */
    public CustomDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /*
    *
    * @author: darshan bidkar
    * */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }
}
