package desktopapplication1;

/*
 * This class is main class for GraphScene
 */
/**
 *
 * @author aaa
 */
import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.openide.util.ImageUtilities;

public class GraphSceneImpl extends GraphScene<String, String> {

    private LayerWidget mainLayer;
    private LayerWidget connectionLayer;
    private LayerWidget interactionLayer;
    // add edit text function to widget
    private WidgetAction editorAction = ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor());
    // add reconnect function to edge (line between widgets)
    private WidgetAction reconnectAction = ActionFactory.createReconnectAction (new SceneReconnectProvider (this));
    // create node menu in GraphScene (right click at widget to show menu)
    private NodeMenu nodeMenu = new NodeMenu(this);

    private long edgeCounter = 0; // use is number to name each new edge

    public GraphSceneImpl() {
        // create layers and add all of its to GraphScene
        mainLayer = new LayerWidget(this);  // layer for widgets
        connectionLayer = new LayerWidget(this);    // layer for edges
        interactionLayer = new LayerWidget(this);
        addChild(mainLayer);
        addChild(connectionLayer);
        addChild(interactionLayer);

        // create 3 sample widgets at beginning
        Widget w1 = addNode("1.LED");
        w1.setPreferredLocation(new Point(10, 100));
        Widget w2 = addNode("2. Timer");
        w2.setPreferredLocation(new Point(100, 250));
        Widget w3 = addNode("3.Button");
        w3.setPreferredLocation(new Point(200, 150));

        // add zoom function to GraphScene (press Ctrl + scroll middle button mouse to zoom in-out)
        getActions().addAction(ActionFactory.createZoomAction());
        // add menu to GraphScene for adding new widget
        getActions().addAction(ActionFactory.createPopupMenuAction(new SceneMainMenu(this)));
        
    }


    @Override
    protected Widget attachNodeWidget(String arg) {
        IconNodeWidget widget = new IconNodeWidget(this);

        if (arg.startsWith("1")) {  // if widget name start with 1 then use led widget
            widget.setImage(ImageUtilities.loadImage("Led.gif"));
        } else if (arg.startsWith("2")) {  // if widget name start with 2 then use Timer widget
            widget.setImage(ImageUtilities.loadImage("Timer.gif"));
        } else {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("Button.gif"));
        }

        // function that add edge between widgets
        // connectionaction must come before moveaction
        widget.getActions().addAction(
                ActionFactory.createExtendedConnectAction(
                connectionLayer, new MyConnectProvider(this)));

        // add move function to widget
        widget.getActions().addAction(ActionFactory.createMoveAction());

        //double-click widget to edit text
        widget.getLabelWidget().getActions().addAction(editorAction);

        //mouse-over, the event is consumed while the mouse is over the widget:
        widget.getActions().addAction(createObjectHoverAction());

        // add node menu to delete node or widget (right click at widget to show menu)
        widget.getActions().addAction(ActionFactory.createPopupMenuAction(nodeMenu));

        widget.setLabel(arg);   // set widget name

        mainLayer.addChild(widget); // add widget to mainLayer
        return widget;
    }

    @Override
    protected Widget attachEdgeWidget(String arg0) {
        //  this function will be called if add new edge
        ConnectionWidget connection = new ConnectionWidget (this);
        connection.setTargetAnchorShape (AnchorShape.TRIANGLE_FILLED); // assign edge type
        connection.setEndPointShape (PointShape.SQUARE_FILLED_BIG);
        connection.getActions ().addAction (createObjectHoverAction ()); // detect hover
        connection.getActions ().addAction (createSelectAction ());  // detect selection
        connection.getActions ().addAction (reconnectAction);   // detect edge change
        connectionLayer.addChild (connection);  //add new edge to connectionLayer
        return connection;
    }

    @Override
    protected void attachEdgeSourceAnchor(String edge, String oldSourceNode, String sourceNode) {
        //  check source widget of the edge
        Widget w = sourceNode != null ? findWidget (sourceNode) : null;
        ((ConnectionWidget) findWidget (edge)).setSourceAnchor (AnchorFactory.createRectangularAnchor (w));
    }

    @Override
    protected void attachEdgeTargetAnchor(String edge, String oldTargetNode, String targetNode) {
        //  check target of the edge
        Widget w = targetNode != null ? findWidget (targetNode) : null;
        ((ConnectionWidget) findWidget (edge)).setTargetAnchor (AnchorFactory.createRectangularAnchor (w));
    }

    /**
     * @return the edgeCounter
     */
    public long getEdgeCounter() {
        return edgeCounter;     // for other class to access EdgeCounter
    }

    /**
     * @param edgeCounter the edgeCounter to set
     */
    public void setEdgeCounter(long edgeCounter) {
        this.edgeCounter = edgeCounter; // for other class to access EdgeCounter
    }

}
