package desktopapplication1;

/*
 * This class is main class for GraphScene
 */
/**
 *
 * @author aaa
 */
import java.util.ArrayList;
import model.Component;
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
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openide.util.ImageUtilities;

public class GraphSceneImpl extends GraphScene<String, String> {

    public static ArrayList<CustomWidget> listOfCustomWidgets;
    private LayerWidget mainLayer;
    private LayerWidget connectionLayer;
    private LayerWidget interactionLayer;
    // add edit text function to widget
    private WidgetAction editorAction = ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor());
    // add reconnect function to edge (line between widgets)
    private WidgetAction reconnectAction = ActionFactory.createReconnectAction(new SceneReconnectProvider(this));
    // create node menu in GraphScene (right click at widget to show menu)
    private NodeMenu nodeMenu = new NodeMenu(this);
    private long edgeCounter = 0; // use is number to name each new edge

    public GraphSceneImpl() {
        // create layers and add all of its to GraphScene
        listOfCustomWidgets = new ArrayList<CustomWidget>();
        mainLayer = new LayerWidget(this);  // layer for widgets
        connectionLayer = new LayerWidget(this);    // layer for edges
        interactionLayer = new LayerWidget(this);
        addChild(mainLayer);
        addChild(connectionLayer);
        addChild(interactionLayer);

        // add zoom function to GraphScene (press Ctrl + scroll middle button mouse to zoom in-out)
        getActions().addAction(ActionFactory.createZoomAction());
        // add menu to GraphScene for adding new widget
        getActions().addAction(ActionFactory.createPopupMenuAction(new SceneMainMenu(this)));
    }

    @Override
    protected Widget attachNodeWidget(String arg) {
        IconNodeWidget widget = new IconNodeWidget(this);
        if (arg.startsWith("1")) {  // if widget name start with 1 then use led widget
            widget.setImage(ImageUtilities.loadImage("icons/Led.gif"));
        } else if (arg.startsWith("2")) {  // if widget name start with 2 then use Timer widget
            widget.setImage(ImageUtilities.loadImage("icons/Timer.gif"));
        } else {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/Button.gif"));
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
        ConnectionWidget connection = new ConnectionWidget(this);
        connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED); // assign edge type
        connection.setEndPointShape(PointShape.SQUARE_FILLED_BIG);
        connection.getActions().addAction(createObjectHoverAction()); // detect hover
        connection.getActions().addAction(createSelectAction());  // detect selection
        connection.getActions().addAction(reconnectAction);   // detect edge change
        connectionLayer.addChild(connection);  //add new edge to connectionLayer
        Widget x = connectionLayer.getChildren().get(Integer.parseInt(arg0.replaceAll("\\D+", "")));
        String sourceWidgetStr = ((IconNodeWidget) (((ConnectionWidget) x).getSourceAnchor().getRelatedWidget())).getLabelWidget().getLabel();
        String targetWidgetStr = ((IconNodeWidget) (((ConnectionWidget) x).getTargetAnchor().getRelatedWidget())).getLabelWidget().getLabel();
        Component sourceComponent = listOfCustomWidgets.get(getWidgetIndex(sourceWidgetStr, listOfCustomWidgets)).getComponent();
        Component targetComponent = listOfCustomWidgets.get(getWidgetIndex(targetWidgetStr, listOfCustomWidgets)).getComponent();
        listOfCustomWidgets.get(getWidgetIndex(sourceWidgetStr, listOfCustomWidgets)).getComponent().setOutput(targetComponent);
        listOfCustomWidgets.get(getWidgetIndex(targetWidgetStr, listOfCustomWidgets)).getComponent().setInput(sourceComponent);
        return connection;
    }

    @Override
    protected void attachEdgeSourceAnchor(String edge, String oldSourceNode, String sourceNode) {
        //  check source widget of the edge
        Widget w = sourceNode != null ? findWidget(sourceNode) : null;
//        listOfCustomWidgets.get(getWidgetIndex(w, listOfCustomWidgets)).getComponent().setOutput(w);
        ((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(w));

    }

    @Override
    protected void attachEdgeTargetAnchor(String edge, String oldTargetNode, String targetNode) {
        //  check target of the edge
        Widget w = targetNode != null ? findWidget(targetNode) : null;
        ((ConnectionWidget) findWidget(edge)).setTargetAnchor(AnchorFactory.createRectangularAnchor(w));
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

    public int getWidgetIndex(String widget, ArrayList<CustomWidget> customWidgets) {
        for (int i = 0; i < customWidgets.size(); i++) {
            if (customWidgets.get(i).getComponent().getName().compareTo(widget) == 0) {
                return i;
            }
        }
        return -1;
    }
}
