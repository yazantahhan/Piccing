/*
 * This class is for recheck edge if something change
 * Click edge to select and drag head or tail of edge to change.
 */

package desktopapplication1;

import java.awt.Point;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.ReconnectProvider;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author aaa
 */
public class SceneReconnectProvider implements ReconnectProvider {

        String edge;    // edge name
        String originalNode;    // old node name
        String replacementNode;     // new node name
        GraphSceneImpl graph; // object of GraphScene

        public SceneReconnectProvider(GraphSceneImpl g) {
            graph = g; // get reference of GraphScene to be used in this class
        }

        public void reconnectingStarted (ConnectionWidget connectionWidget, boolean reconnectingSource) {
        }

        public void reconnectingFinished (ConnectionWidget connectionWidget, boolean reconnectingSource) {
        }

        public boolean isSourceReconnectable (ConnectionWidget connectionWidget) {
            // check source widget
            Object object = graph.findObject (connectionWidget);
            edge = graph.isEdge (object) ? (String) object : null;
            originalNode = edge != null ? graph.getEdgeSource (edge) : null;
            return originalNode != null;
        }

        public boolean isTargetReconnectable (ConnectionWidget connectionWidget) {
            // check target widget
            Object object = graph.findObject (connectionWidget);
            edge = graph.isEdge (object) ? (String) object : null;
            originalNode = edge != null ? graph.getEdgeTarget (edge) : null;
            return originalNode != null;
        }

        public ConnectorState isReplacementWidget (ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource) {
            // check if something change
            Object object = graph.findObject (replacementWidget);
            replacementNode = graph.isNode (object) ? (String) object : null;
            if (replacementNode != null)
                return ConnectorState.ACCEPT;
            return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
        }

        public boolean hasCustomReplacementWidgetResolver (Scene scene) {
            return false;
        }

        public Widget resolveReplacementWidget (Scene scene, Point sceneLocation) {
            return null;
        }

        public void reconnect (ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource) {
            // readjust edge if something change
            if (replacementWidget == null)
                graph.removeEdge (edge);
            else if (reconnectingSource)
                graph.setEdgeSource (edge, replacementNode);
            else
                graph.setEdgeTarget (edge, replacementNode);
        }

    }
