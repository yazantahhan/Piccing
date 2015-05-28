/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import jsonModel.ButtonJson;
import jsonModel.ComponentJson;
import jsonModel.LCDJson;
import jsonModel.LedJson;
import jsonModel.SensorJson;
import jsonModel.jsonMotor;
import model.CodeStructure;
import model.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openide.util.Exceptions;

public class HeaderParser {

    public static void parseHeader(File file) throws URISyntaxException {
        JSONParser parser = new JSONParser();
        HashMap<String, ComponentJson> listOfJsonComps = new HashMap<String, ComponentJson>();
        try {
            File f = file;
            JSONArray headerJson = (JSONArray) parser.parse(new FileReader(f));
            String name;
            String pin;
            String listOfPins;
            JSONObject jsonObj;

            jsonObj = ((JSONObject) headerJson.get(0));
            Constants.microcontroller = (String) jsonObj.get("microcontroller");
            if (Constants.microcontroller.compareTo("ARDUINO") == 0) {


                for (int i = 0; i < headerJson.size(); i++) {
                    jsonObj = ((JSONObject) headerJson.get(i));
                    if ((name = (String) jsonObj.get("microcontroller")) != null) {
                        Constants.microcontroller = name;
                    } else {
                        name = (String) jsonObj.get("name");
                        if (name.contains("LED")) {
                            if (!listOfJsonComps.containsKey("LED")) {
                                listOfJsonComps.put("LED", new LedJson("LED", null));
                                Constants.listOfAvailableComponentsStrings.add(name);
                            }
                            CodeStructure.setupA.append("pinMode(").append((String) jsonObj.get("pin"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setupA.append(", OUTPUT);\r\n");
                            } else {
                                CodeStructure.setupA.append(", INPUT);\r\n");
                            }
                            String color = (String) jsonObj.get("color");
                            ((LedJson) listOfJsonComps.get("LED")).getColorPinMapping().put(color, (String) jsonObj.get("pin"));
                            ((LedJson) listOfJsonComps.get("LED")).getAvailabeColors().add(color);
                        } else if (name.contains("BUTTON")) {
                            listOfPins = (String) jsonObj.get("pin");
                            listOfJsonComps.put("BUTTON", new ButtonJson("BUTTON", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                            Constants.listOfAvailableComponentsStrings.add(name);
                            CodeStructure.setupA.append((String) jsonObj.get("tris"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setupA.append("=0;\r\n");
                            } else {
                                CodeStructure.setupA.append("=1;\r\n");
                            }
                        } else if (name.contains("SENSOR")) {
                            if (!listOfJsonComps.containsKey("SENSOR")) {
                                listOfJsonComps.put("SENSOR", new SensorJson("SENSOR", null));
                                Constants.listOfAvailableComponentsStrings.add(name);
                            }
                            CodeStructure.setupA.append("pinMode(").append((String) jsonObj.get("pin"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setupA.append(", OUTPUT);\r\n");
                            } else {
                                CodeStructure.setupA.append(", INPUT);\r\n");
                            }
                            String type = (String) jsonObj.get("type");
                            ((SensorJson) listOfJsonComps.get("SENSOR")).getTypePinMapping().put(type, (String) jsonObj.get("pin"));
                            ((SensorJson) listOfJsonComps.get("SENSOR")).getAvailabeTypes().add(type);
                        } else if (name.contains("LCD")) {
                            listOfPins = (String) jsonObj.get("pinE");
                            listOfJsonComps.put("LCD", new LCDJson("LCD", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                            Constants.listOfAvailableComponentsStrings.add(name);
                            CodeStructure.setupA.append((String) jsonObj.get("tris"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setupA.append("=0;\r\n");
                            } else {
                                CodeStructure.setupA.append("=1;\r\n");
                            }
                        } else if (name.contains("MOTOR")) {
                            listOfPins = (String) jsonObj.get("pin");
                            listOfJsonComps.put("MOTOR", new jsonMotor("LCD", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                            Constants.listOfAvailableComponentsStrings.add(name);
                            String tris1 = ((String) jsonObj.get("tris")).split(",")[0];
                            String tris2 = ((String) jsonObj.get("tris")).split(",")[1];
                            CodeStructure.setupA.append(tris1);
                            if (((String) jsonObj.get("i/o")).split(",")[0].compareTo("out") == 0) {
                                CodeStructure.setupA.append("=0;\r\n");
                            } else {
                                CodeStructure.setupA.append("=1;\r\n");
                            }
                            CodeStructure.setupA.append(tris2);
                            if (((String) jsonObj.get("i/o")).split(",")[0].compareTo("out") == 0) {
                                CodeStructure.setupA.append("=0;\r\n");
                            } else {
                                CodeStructure.setupA.append("=1;\r\n");
                            }
                        }
                    }
                }
                CodeStructure.setupA.append(" Serial.begin(9600); ");
            } else {
                for (int i = 0; i < headerJson.size(); i++) {
                    jsonObj = ((JSONObject) headerJson.get(i));
                    if ((name = (String) jsonObj.get("microcontroller")) != null) {
                        Constants.microcontroller = name;
                    } else {
                        name = (String) jsonObj.get("name");
                        if (name.contains("LED")) {
                            if (!listOfJsonComps.containsKey("LED")) {
                                listOfJsonComps.put("LED", new LedJson("LED", null));
                                Constants.listOfAvailableComponentsStrings.add(name);
                            }
                            CodeStructure.setup.append((String) jsonObj.get("tris"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setup.append("=0;\r\n");
                            } else {
                                CodeStructure.setup.append("=1;\r\n");
                            }
                            String color = (String) jsonObj.get("color");
                            ((LedJson) listOfJsonComps.get("LED")).getColorPinMapping().put(color, (String) jsonObj.get("pin"));
                            ((LedJson) listOfJsonComps.get("LED")).getAvailabeColors().add(color);
                        } else if (name.contains("BUTTON")) {
                            listOfPins = (String) jsonObj.get("pin");
                            listOfJsonComps.put("BUTTON", new ButtonJson("BUTTON", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                            Constants.listOfAvailableComponentsStrings.add(name);
                            CodeStructure.setup.append((String) jsonObj.get("tris"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setup.append("=0;\r\n");
                            } else {
                                CodeStructure.setup.append("=1;\r\n");
                            }
                        } else if (name.contains("SENSOR")) {
                            if (!listOfJsonComps.containsKey("SENSOR")) {
                                listOfJsonComps.put("SENSOR", new SensorJson("SENSOR", null));
                                Constants.listOfAvailableComponentsStrings.add(name);
                            }
                            CodeStructure.setup.append((String) jsonObj.get("tris"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setup.append("=0;\r\n");
                            } else {
                                CodeStructure.setup.append("=1;\r\n");
                            }
                            String type = (String) jsonObj.get("type");
                            ((SensorJson) listOfJsonComps.get("SENSOR")).getTypePinMapping().put(type, (String) jsonObj.get("pin"));
                            ((SensorJson) listOfJsonComps.get("SENSOR")).getAvailabeTypes().add(type);
                        } else if (name.contains("LCD")) {
                            listOfPins = (String) jsonObj.get("pinE");
                            listOfJsonComps.put("LCD", new LCDJson("LCD", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                            Constants.listOfAvailableComponentsStrings.add(name);
                            CodeStructure.setup.append((String) jsonObj.get("tris"));
                            if (((String) jsonObj.get("i/o")).compareTo("out") == 0) {
                                CodeStructure.setup.append("=0;\r\n");
                            } else {
                                CodeStructure.setup.append("=1;\r\n");
                            }
                        } else if (name.contains("MOTOR")) {
                            listOfPins = (String) jsonObj.get("pin");
                            listOfJsonComps.put("MOTOR", new jsonMotor("LCD", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                            Constants.listOfAvailableComponentsStrings.add(name);
                            String tris1 = ((String) jsonObj.get("tris")).split(",")[0];
                            String tris2 = ((String) jsonObj.get("tris")).split(",")[1];
                            CodeStructure.setup.append(tris1);
                            if (((String) jsonObj.get("i/o")).split(",")[0].compareTo("out") == 0) {
                                CodeStructure.setup.append("=0;\r\n");
                            } else {
                                CodeStructure.setup.append("=1;\r\n");
                            }
                            CodeStructure.setup.append(tris2);
                            if (((String) jsonObj.get("i/o")).split(",")[0].compareTo("out") == 0) {
                                CodeStructure.setup.append("=0;\r\n");
                            } else {
                                CodeStructure.setup.append("=1;\r\n");
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        Constants.listOfJsonComponents = listOfJsonComps;
    }
}
