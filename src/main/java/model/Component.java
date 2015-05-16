package model;

import javax.swing.JOptionPane;

public abstract class Component {

    private String name = "";
    private Component input = null;
    private Component output = null;
    private String resultedStr=" ";

    public String getResultedStr() {
        return resultedStr;
    }

    public void setResultedStr(String resultedStr) {
        this.resultedStr = resultedStr;
    }

    public Component(String name, Component input, Component output) {
        this.name = name;
        this.input = input;
        this.output = output;
    }

    public abstract String getComponentsCode();

    public Component getInput() {
        return input;
    }

    public void setInput(Component input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component getOutput() {
        return output;
    }

    public void setOutput(Component output) {
        this.output = output;
    }

    abstract public void showInputPinsDialog();

    abstract public void showOutputPinsDialog();

    abstract public void showConfigDialog();
}
