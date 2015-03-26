package model;

public class LED extends Component {

    private boolean willTurnOn = false;
    private String port = "";
    private String pin = "";

    public LED(String name, Component input, Component output, String port, String pin, boolean willTurnOn) {
        super(name, input, output);
        this.port = port;
        this.pin = pin;
        this.willTurnOn = willTurnOn;
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
        CodeStructure.localVars.append("R").append(port).append(pin).append("=0;\r\n");
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
