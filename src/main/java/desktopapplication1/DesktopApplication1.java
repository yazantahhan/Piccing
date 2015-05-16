/*
 * DesktopApplication1.java
 */
package desktopapplication1;

import engine.HeaderParser;
import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class DesktopApplication1 extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        show(new DesktopApplication1View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopApplication1
     */
    public static DesktopApplication1 getApplication() {
        return Application.getInstance(DesktopApplication1.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws URISyntaxException {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select the Header File");
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();


        HeaderParser.parseHeader(file);
        launch(DesktopApplication1.class, args);
    }
}
