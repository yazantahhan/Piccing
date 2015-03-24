package model;

public abstract class Component {

    private String name = "";
    private Component input = null;
    private Component output = null;

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
    
    
}
