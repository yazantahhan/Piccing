/*
 *This class is for edit text of the widget.
 * Double click widget's text to edit.
 */
package desktopapplication1;

import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author aaa
 */
public class LabelTextFieldEditor implements TextFieldInplaceEditor {

    public boolean isEnabled(Widget widget) {
        return true;
    }

    public String getText(Widget widget) {
        return ((LabelWidget) widget).getLabel();
    }

    public void setText(Widget widget, String text) {
        ((LabelWidget) widget).setLabel(text);
    }
}
