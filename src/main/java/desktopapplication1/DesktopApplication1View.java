/*
 * DesktopApplication1View.java
 */
package desktopapplication1;

import java.awt.BorderLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jsonModel.ComponentJson;
import jsonModel.LedJson;
import jsonModel.SensorJson;
import model.Component;
import model.Constants;
import org.netbeans.api.visual.graph.GraphScene;
import org.openide.util.Exceptions;

/**
 * The application's main frame.
 */
public class DesktopApplication1View extends FrameView {

    SceneMainMenu sced = new SceneMainMenu(SceneMainMenu.currentScene);

    public DesktopApplication1View(SingleFrameApplication app) {
        super(app);



        initComponents();
        Properties prop = new Properties();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        InputStream testf = this.getClass().getResourceAsStream("/properties/DesktopApplication1View.properties");
        try {
            prop.load(testf);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        // status bar initialization - message timeout, idle icon and busy animation, etc
//        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = Integer.parseInt(prop.getProperty("StatusBar.messageTimeout"));
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = Integer.parseInt(prop.getProperty("StatusBar.busyAnimationRate"));
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = new ImageIcon(prop.getProperty("StatusBar.busyIcons[" + i + "]"));
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = new ImageIcon(prop.getProperty("StatusBar.idleIcon"));
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        //Create the GraphSceneImpl:
        GraphScene scene = new GraphSceneImpl();
        //Add it to the JScrollPane:
        scrollPane.setViewportView(scene.createView());
        //Add the SatellitView to the scene:
        this.getFrame().add(scene.createSatelliteView(), BorderLayout.WEST);
        sced.addStart();
        sced.addEnd();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DesktopApplication1.getApplication().getMainFrame();
            aboutBox = new DesktopApplication1AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DesktopApplication1.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        mainPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ledBtn = new javax.swing.JButton();
        timerBtn = new javax.swing.JButton();
        buttonBtn = new javax.swing.JButton();
        LCDBtn = new javax.swing.JButton();
        sensorBtn = new javax.swing.JButton();
        delayBtn = new javax.swing.JButton();
        motorBtn = new javax.swing.JButton();
        adcbutton = new javax.swing.JButton();
        KeyBadbtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jFrame1.setName("jFrame1"); // NOI18N

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1821, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1651, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        mainPanel.setName("mainPanel"); // NOI18N

        scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollPane.setName("scrollPane"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1677, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1789, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(DesktopApplication1View.class);
        ledBtn.setIcon(resourceMap.getIcon("ledBtn.icon")); // NOI18N
        ledBtn.setName("ledBtn"); // NOI18N
        ledBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ledBtnMouseEntered(evt);
            }
        });
        ledBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ledBtnActionPerformed(evt);
            }
        });

        timerBtn.setIcon(resourceMap.getIcon("timerBtn.icon")); // NOI18N
        timerBtn.setName("timerBtn"); // NOI18N
        timerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                timerBtnMouseEntered(evt);
            }
        });
        timerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerBtnActionPerformed(evt);
            }
        });

        buttonBtn.setIcon(resourceMap.getIcon("buttonBtn.icon")); // NOI18N
        buttonBtn.setName("buttonBtn"); // NOI18N
        buttonBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBtnMouseEntered(evt);
            }
        });
        buttonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBtnActionPerformed(evt);
            }
        });

        LCDBtn.setIcon(resourceMap.getIcon("LCDBtn.icon")); // NOI18N
        LCDBtn.setName("LCDBtn"); // NOI18N
        LCDBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LCDBtnMouseEntered(evt);
            }
        });
        LCDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LCDBtnActionPerformed(evt);
            }
        });

        sensorBtn.setIcon(resourceMap.getIcon("sensorBtn.icon")); // NOI18N
        sensorBtn.setName("sensorBtn"); // NOI18N
        sensorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sensorBtnMouseEntered(evt);
            }
        });
        sensorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensorBtnActionPerformed(evt);
            }
        });

        delayBtn.setIcon(resourceMap.getIcon("delayBtn.icon")); // NOI18N
        delayBtn.setName("delayBtn"); // NOI18N
        delayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delayBtnMouseEntered(evt);
            }
        });
        delayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayBtnActionPerformed(evt);
            }
        });

        motorBtn.setIcon(resourceMap.getIcon("motorbtn.icon")); // NOI18N
        motorBtn.setName("motorbtn"); // NOI18N
        motorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                motorBtnMouseEntered(evt);
            }
        });
        motorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorBtnActionPerformed(evt);
            }
        });

        adcbutton.setIcon(resourceMap.getIcon("adcbutton.icon")); // NOI18N
        adcbutton.setName("adcbutton"); // NOI18N
        adcbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adcbuttonMouseEntered(evt);
            }
        });
        adcbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adcbuttonActionPerformed(evt);
            }
        });

        KeyBadbtn.setIcon(resourceMap.getIcon("KeyBadbtn.icon")); // NOI18N
        KeyBadbtn.setName("KeyBadbtn"); // NOI18N
        KeyBadbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KeyBadbtnMouseEntered(evt);
            }
        });
        KeyBadbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyBadbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KeyBadbtn)
                    .addComponent(adcbutton)
                    .addComponent(motorBtn)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ledBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(buttonBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LCDBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sensorBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timerBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(delayBtn))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ledBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LCDBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sensorBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timerBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delayBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(motorBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adcbutton)
                .addGap(18, 18, 18)
                .addComponent(KeyBadbtn)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1659, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1789, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N
        jMenuBar1.add(jMenu2);

        jFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1821, Short.MAX_VALUE)
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1857, Short.MAX_VALUE)
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 916, Short.MAX_VALUE)
                    .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 916, Short.MAX_VALUE)))
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setComponent(mainPanel);
        setMenuBar(jMenuBar1);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void ledBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ledBtnActionPerformed
        if (getAvailableComponent("LED") != null) {
            sced.addLed();
        }
}//GEN-LAST:event_ledBtnActionPerformed

    private void timerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timerBtnActionPerformed
        sced.addTimer();        // TODO add your handling code here:
    }//GEN-LAST:event_timerBtnActionPerformed

    private void buttonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBtnActionPerformed
        if (getAvailableComponent("BUTTON") != null) {
            sced.addButton();
        }
    }//GEN-LAST:event_buttonBtnActionPerformed

    private void LCDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LCDBtnActionPerformed
        if (getAvailableComponent("LCD") != null) {
            sced.addLcd();
        }
    }//GEN-LAST:event_LCDBtnActionPerformed

    private void sensorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensorBtnActionPerformed
        if (getAvailableComponent("SENSOR") != null) {
            ArrayList<String> ready = new ArrayList<String>();
            HashMap<String, String> listofPins = ((SensorJson) (Constants.listOfJsonComponents.get("SENSOR"))).getTypePinMapping();
            ArrayList<String> listOfSensors = ((SensorJson) (Constants.listOfJsonComponents.get("SENSOR"))).getAvailabeTypes();
            for (int i = 0; i < listofPins.size(); i++) {
                String currentType = listOfSensors.get(i);
                String pins;
                String x;
                String y = listofPins.get(currentType);
                if (Constants.microcontroller.compareTo("ARDUINO") == 0) {
                    x = currentType + "  -----> " + y;
                } else {
                    pins = (String) y.subSequence(10, 13);
                    x = currentType + "  -----> " + pins;
                }
                ready.add(x);

            }
            JComboBox<String> combo = new JComboBox(ready.toArray());

            final JComponent[] inputs = new JComponent[]{
                new JLabel("Type"),
                combo
            };
            JOptionPane.showMessageDialog(null, inputs, "Select Type", JOptionPane.PLAIN_MESSAGE);
            String selctedType = (String) combo.getSelectedItem();

            sced.addSensor(selctedType.split(" ")[0]);
        }
    }//GEN-LAST:event_sensorBtnActionPerformed

    private void delayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayBtnActionPerformed
        sced.addDelay();
    }//GEN-LAST:event_delayBtnActionPerformed

    private void motorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motorBtnActionPerformed
        if (getAvailableComponent("MOTOR") != null) {
            sced.addMotor();
        }
    }//GEN-LAST:event_motorBtnActionPerformed

    private void adcbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adcbuttonActionPerformed
        if (getAvailableComponent("ADC") != null) {
            sced.addAdc();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_adcbuttonActionPerformed

    private void sensorBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensorBtnMouseEntered
        sensorBtn.setToolTipText("Sensor");        // TODO add your handling code here:
    }//GEN-LAST:event_sensorBtnMouseEntered

    private void ledBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ledBtnMouseEntered
        ledBtn.setToolTipText("LED");        // TODO add your handling code here:
    }//GEN-LAST:event_ledBtnMouseEntered

    private void buttonBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBtnMouseEntered
        buttonBtn.setToolTipText("Push Button");        // TODO add your handling code here:
    }//GEN-LAST:event_buttonBtnMouseEntered

    private void LCDBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LCDBtnMouseEntered
        LCDBtn.setToolTipText("LCD");       // TODO add your handling code here:
    }//GEN-LAST:event_LCDBtnMouseEntered

    private void timerBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerBtnMouseEntered
        timerBtn.setToolTipText("Timer");        // TODO add your handling code here:
    }//GEN-LAST:event_timerBtnMouseEntered

    private void delayBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delayBtnMouseEntered
        delayBtn.setToolTipText("Delay");        // TODO add your handling code here:
    }//GEN-LAST:event_delayBtnMouseEntered

    private void motorBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_motorBtnMouseEntered
        motorBtn.setToolTipText("Motor");        // TODO add your handling code here:
    }//GEN-LAST:event_motorBtnMouseEntered

    private void adcbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adcbuttonMouseEntered
        adcbutton.setToolTipText("ADC");        // TODO add your handling code here:
    }//GEN-LAST:event_adcbuttonMouseEntered

    private void KeyBadbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KeyBadbtnMouseEntered
        KeyBadbtn.setToolTipText("Key Bad");        // TODO add your handling code here:
    }//GEN-LAST:event_KeyBadbtnMouseEntered

    private void KeyBadbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyBadbtnActionPerformed
        if (getAvailableComponent("KEYPAD") != null) {
            sced.addKeypad();        // TODO add your handling code here:
        }
    }//GEN-LAST:event_KeyBadbtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KeyBadbtn;
    private javax.swing.JButton LCDBtn;
    private javax.swing.JButton adcbutton;
    private javax.swing.JButton buttonBtn;
    private javax.swing.JButton delayBtn;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton ledBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton motorBtn;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton sensorBtn;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton timerBtn;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    public ComponentJson getAvailableComponent(String component) {
        ComponentJson foundedComponent = null;
        String temp;
        for (int i = 0; i < Constants.listOfAvailableComponentsStrings.size(); i++) {
            temp = Constants.listOfAvailableComponentsStrings.get(i);
            if (temp.contains(component)) {
                foundedComponent = Constants.listOfJsonComponents.get(temp);
                break;
            }
        }
        return foundedComponent;
    }
}
