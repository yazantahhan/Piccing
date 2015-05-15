package desktopapplication1;

/*
 * This class is main class for GraphScene
 */
/**
 *
 * @author aaa
 */
import java.util.ArrayList;
import model.Constants;
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
    private WidgetAction reconnectAction = ActionFactory.createReconnectAction(new SceneReconnectProvider(this));
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

        // add zoom function to GraphScene (press Ctrl + scroll middle button mouse to zoom in-out)
        getActions().addAction(ActionFactory.createZoomAction());
        // add menu to GraphScene for adding new widget
        getActions().addAction(ActionFactory.createPopupMenuAction(new SceneMainMenu(this)));
    }

    @Override
    protected Widget attachNodeWidget(String arg) {
        IconNodeWidget widget = new IconNodeWidget(this);
        if (arg.contains("LED")) {  // if widget name start with 1 then use led widget
            widget.setImage(ImageUtilities.loadImage("icons/Led.gif"));
        } else if (arg.contains("Timer")) {  // if widget name start with 2 then use Timer widget
            widget.setImage(ImageUtilities.loadImage("icons/Timer.gif"));
        } else if (arg.contains("Button")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/Button.gif"));
        } else if (arg.contains("Delay")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/delay-32x32.png"));
        } else if (arg.contains("Sensor")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/sensor-32x32.png"));
        } else if (arg.contains("Motor")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/motor32.png"));
        } else if (arg.contains("ADC")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/ADC32.png"));
        } else if (arg.contains("ADC")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/ADC32.png"));
        } else if (arg.contains("Start")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/start45.png"));
        } else if (arg.contains("End")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/end45.png"));
        } else if (arg.contains("KeyBad")) {  // if widget name start with anything else then use blue widget
            widget.setImage(ImageUtilities.loadImage("icons/KeyBad32.png"));
        }


        // function that add edge between widgets
        // connectionaction must come before moveaction
        widget.getActions().addAction(
                ActionFactory.createExtendedConnectAction(
                connectionLayer, new MyConnectProvider(this)));

        // add move function to widget
        widget.getActions().addAction(ActionFactory.createMoveAction());

        //double-click widget to edit text
//        widget.getActions().addAction(ActionFactory.crea);
//        widget.getImageWidget().getActions().addAction() 
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
//        System.out.println(Arrays.asList(connection.getControlPoints().toArray()));\
        connection.getActions().addAction(createObjectHoverAction()); // detect hover
        connection.getActions().addAction(createSelectAction());  // detect selection
        connection.getActions().addAction(reconnectAction);   // detect edge change
        connectionLayer.addChild(connection);  //add new edge to connectionLayer
        Widget x = connectionLayer.getChildren().get(Integer.parseInt(arg0.replaceAll("\\D+", "")));
        String sourceWidgetStr = ((IconNodeWidget) (((ConnectionWidget) x).getSourceAnchor().getRelatedWidget())).getLabelWidget().getLabel();
        String targetWidgetStr = ((IconNodeWidget) (((ConnectionWidget) x).getTargetAnchor().getRelatedWidget())).getLabelWidget().getLabel();
//        Component sourceComponent = Constants.listOfCustomWidgets.get(getWidgetIndex(sourceWidgetStr, Constants.listOfCustomWidgets)).getComponent();
//        Component targetComponent = Constants.listOfCustomWidgets.get(getWidgetIndex(targetWidgetStr, Constants.listOfCustomWidgets)).getComponent();
        CustomWidget sourceCustomWidget = Constants.listOfCustomWidgets.get(getWidgetIndex(sourceWidgetStr, Constants.listOfCustomWidgets));
        CustomWidget targetCustomWidget = Constants.listOfCustomWidgets.get(getWidgetIndex(targetWidgetStr, Constants.listOfCustomWidgets));
        Constants.listOfCustomWidgets.get(getWidgetIndex(sourceWidgetStr, Constants.listOfCustomWidgets)).addOutputToList(targetCustomWidget);
        Constants.listOfCustomWidgets.get(getWidgetIndex(targetWidgetStr, Constants.listOfCustomWidgets)).addInputToList(sourceCustomWidget);
        return connection;
    }

    @Override
    protected void attachEdgeSourceAnchor(String edge, String oldSourceNode, String sourceNode) {
        //  check source widget of the edge
        Widget w = sourceNode != null ? findWidget(sourceNode) : null;
//        listOfCustomWidgets.get(getWidgetIndex(w, listOfCustomWidgets)).getComponent().setOutput(w);
//        ((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(w));
        ((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(w));
//        ((ConnectionWidget) findWidget(edge)).setRouter(RouterFactory.createOrthogonalSearchRouter(mainLayer));

    }

    @Override
    protected void attachEdgeTargetAnchor(String edge, String oldTargetNode, String targetNode) {
        //  check target of the edge
        Widget w = targetNode != null ? findWidget(targetNode) : null;
//        ((ConnectionWidget) findWidget(edge)).setTargetAnchor(AnchorFactory.createRectangularAnchor(w));
        ((ConnectionWidget) findWidget(edge)).setTargetAnchor(AnchorFactory.createRectangularAnchor(w));
//        ((ConnectionWidget) findWidget(edge)).setRouter(RouterFactory.createOrthogonalSearchRouter(mainLayer));
//        ((ConnectionWidget) findWidget(edge)).calculateRouting();
//        List<Point> j = ((ConnectionWidget) findWidget(edge)).getControlPoints();
//        j.get(j.size() - 1).y = j.get(j.size() - 1).y - 20;
//        ((ConnectionWidget) findWidget(edge)).setControlPoints(j, true);
//        System.out.println("");
//        x.setRoutingPolicy(ConnectionWidget.RoutingPolicy.DISABLE_ROUTING);
//        List<Point> z = x.getRouter().routeConnection(x);
//        x.setControlPoints(Arrays.asList(new Point(100, 100),
//                new Point(200, 200)), true);
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
            if (((IconNodeWidget) customWidgets.get(i).getWidget()).getLabelWidget().getLabel().compareTo(widget) == 0) {
                return i;
            }
        }
        return -1;
    }
}
