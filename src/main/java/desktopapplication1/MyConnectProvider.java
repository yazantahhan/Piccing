/*
 * This class is for new Edge (lines between widgets) in GraphScene.
 * To add edge -> hold Ctrl Key plau click source widget and drag to target widget.
 */
package desktopapplication1;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Component;
import model.Constants;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.TwoStateHoverProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
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
    private String target = null;
     // name of target widget

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
        if (((IconNodeWidget) src).getLabelWidget().getLabel().compareTo("Start") != 0) {
            Constants.listOfCustomWidgets.get(getWidgetIndex(src, Constants.listOfCustomWidgets)).getComponent().showOutputPinsDialog();
        }
        if (((IconNodeWidget) trg).getLabelWidget().getLabel().compareTo("End") != 0) {
            Constants.listOfCustomWidgets.get(getWidgetIndex(trg, Constants.listOfCustomWidgets)).getComponent().showInputPinsDialog();
        }
         ConnectionWidget conwi = ((ConnectionWidget) graph.findWidget(edge));
        CustomWidget cw=Constants.hashOfCustomWidgets.get(src);
        if (cw.getName().compareTo("Start")!=0 && cw.getName().compareTo("End")!=0) {
            WidgetAction action = ActionFactory.createHoverAction(new MyHoverProvider(graph));
            LabelWidget label1 = new LabelWidget(graph,GraphSceneImpl.resultedStr);
            label1.setOpaque(true);
            conwi.addChild(label1);
            conwi.setConstraint(label1, LayoutFactory.ConnectionWidgetLayoutAlignment.CENTER_RIGHT, 0.5f);
            label1.getActions().addAction(action);
            GraphSceneImpl.resultedStr=" ";
        }
        
    }

    public int getWidgetIndex(Widget widget, ArrayList<CustomWidget> customWidgets) {
        for (int i = 0; i < customWidgets.size(); i++) {
            if (customWidgets.get(i).getWidget().equals(widget)) {
                return i;
            }
        }
        return -1;
    }
    private static class MyHoverProvider implements TwoStateHoverProvider {

        private Scene scene;

        public MyHoverProvider(Scene scene) {
            this.scene = scene;
        }

        public void unsetHovering(Widget widget) {
            if (widget != null) {
                widget.setBackground(scene.getLookFeel().getBackground(ObjectState.createNormal()));
                widget.setForeground(scene.getLookFeel().getForeground(ObjectState.createNormal()));
            }
        }

        public void setHovering(Widget widget) {
            if (widget != null) {
                ObjectState state = ObjectState.createNormal().deriveSelected(true);
                widget.setBackground(scene.getLookFeel().getBackground(state));
                widget.setForeground(scene.getLookFeel().getForeground(state));
            }
        }
    }
}
