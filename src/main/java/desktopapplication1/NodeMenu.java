/*
 * This class is for menu to delete node(widget).
 * Right click widget to show delete widget menu.
 */

package desktopapplication1;

/**
 *
 * @author aaa
 */
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

public class NodeMenu implements PopupMenuProvider, ActionListener {

    private static final String DELETE_NODE_ACTION = "deleteNodeAction"; 

    private JPopupMenu menu;
    private IconNodeWidget node;

    private Point point;
    private GraphScene scene;

    public NodeMenu(GraphScene scn) {
        this.scene = scn; // get reference GraphScene from caller class
        menu = new JPopupMenu("Node Menu"); // create popup menu
        JMenuItem item;

        item = new JMenuItem("Delete Node");    // create new menuitem
        item.setActionCommand(DELETE_NODE_ACTION);
        item.addActionListener(this);
        menu.add(item); // add menuitem to popup menu
    }

    public JPopupMenu getPopupMenu(Widget widget,Point point){
        this.point = point;
        this.node = (IconNodeWidget)widget;
        return menu;
    }

    public void actionPerformed(ActionEvent e) {
        // if click delete menu then remove widget and refresh GrapgScene
        if(e.getActionCommand().equals(DELETE_NODE_ACTION)){
            scene.removeNodeWithEdges((String)scene.findObject (node));
            scene.validate();
        }
    }


}

