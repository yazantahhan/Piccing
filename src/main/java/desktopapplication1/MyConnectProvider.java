/*
 * This class is for new Edge (lines between widgets) in GraphScene.
 * To add edge -> hold Ctrl Key plau click source widget and drag to target widget.
 */
package desktopapplication1;

import java.awt.Point;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

/**
 *
 * @author aaa
 */
class MyConnectProvider implements ConnectProvider {

    GraphSceneImpl graph; // object of GraphScene
    private String source = null;   // name of source widget
    private String target = null;   // name of target widget

    public MyConnectProvider(GraphSceneImpl g) {
        this.graph = g; // get reference of GraphScene to be used in this class
    }

    public boolean isSourceWidget(Widget src) {
        // check if widget is source widget of current edge
        Object object = graph.findObject(src);
        source = graph.isNode(object) ? (String) object : null;
        return src instanceof IconNodeWidget && src != null ? true : false;
    }

    public ConnectorState isTargetWidget(Widget src, Widget trg) {
        // check if widget is target widget of current edge
        Object object = graph.findObject(trg);
        target = graph.isNode(object) ? (String) object : null;
        return src != trg && trg instanceof IconNodeWidget ? ConnectorState.ACCEPT : ConnectorState.REJECT;
    }

    public boolean hasCustomTargetWidgetResolver(Scene arg0) {
        return false;
    }

    public Widget resolveTargetWidget(Scene arg0, Point arg1) {
        return null;
    }

    public void createConnection(Widget src, Widget trg) {
        // assign name to new edge
        String edge = "edge" + graph.getEdgeCounter();
        // add edge to GraphScene via connectionLayer (see function attachEdgeWidget in GrapgSceneImpl)
        graph.addEdge(edge);
        // link current edge to source widget
        graph.setEdgeSource(edge, source);
        // link current edge to target widget
        graph.setEdgeTarget(edge, target);
        // adjust number for new coming edge
        graph.setEdgeCounter(graph.getEdgeCounter() + 1);
    }
}
