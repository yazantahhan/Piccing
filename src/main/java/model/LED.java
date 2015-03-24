package model;

public class LED extends Component {

    private boolean willTurnOn = false;
    private String port = "";
    private String pin = "";
    private String name = "";
    private Component input = null;
    private Component output = null;

    public LED(String name, Component input, Component output, String port, String pin, boolean willTurnOn) {
        this.port = port;
        this.pin = pin;
        this.willTurnOn = willTurnOn;
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

    public boolean isWillTurnOn() {
        return willTurnOn;
    }

    public void setWillTurnOn(boolean willTurnOn) {
        this.willTurnOn = willTurnOn;
    }

    @Override
    public String getComponentsCode() {
        if (willTurnOn) {
            return getTurnOnTemplate(port, pin);
        } else {
            return getTurnOffTemplate(port, pin);
        }
    }

    private String getTurnOnTemplate(String port, String pin) {
        String x = "R" + port + pin + "=1;\r\n";
        return x;
    }

    private String getTurnOffTemplate(String port, String pin) {
        String x = "R" + port + pin + "=0;\r\n";
        return x;
    }
}
