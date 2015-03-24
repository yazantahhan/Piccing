package model;

public class Button extends Component {

    private boolean isActiveLow = false;
    private String port = "";
    private String pin = "";
    private String name = "";
    private Component input = null;
    private Component output = null;

    public Button(String name, Component input, Component output, String port, String pin, boolean isActiveLow) {
        this.isActiveLow = isActiveLow;
        this.port = port;
        this.pin = pin;
        this.name = name;
        this.input = input;
        this.output = output;
    }

    public Component getInput() {
        return input;
    }

    public void setInput(Component input) {
        this.input = input;
    }

    public boolean isIsActiveLow() {
        return isActiveLow;
    }

    public void setIsActiveLow(boolean isActiveLow) {
        this.isActiveLow = isActiveLow;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String getComponentsCode() {
        return getButtonStartTemplate(port, pin, isActiveLow);
    }

    private String getButtonStartTemplate(String port, String pin, boolean isActiveLow) {
        String x;
        if (isActiveLow) {
            x = "if(!R" + port + pin + "){\r\n";
        } else {
            x = "if(R" + port + pin + "){\r\n";
        }
        return x;
    }
}
