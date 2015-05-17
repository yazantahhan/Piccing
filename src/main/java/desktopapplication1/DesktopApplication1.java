/*
 * DesktopApplication1.java
 */
package desktopapplication1;

import engine.HeaderParser;
import java.io.File;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
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
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new OpenFileFilter("json","JSON File") );
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();


        HeaderParser.parseHeader(file);
        launch(DesktopApplication1.class, args);
    }
}

/**
 * This class defines which file types are displayed (by default) by the JFileChooser and what file
 * types appear in the drop down menu in the file dialog.
 * You could add more than one file type to the open file dialog by creating multiple instances of this 
 * class and then repeatedly calling addFileFilter.
 * @author LaSpina
 */

class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
}