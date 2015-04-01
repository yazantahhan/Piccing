package model;

public class LED extends Component {

    private boolean willTurnOn = false;
    private String pin = "";
    private String color = "";

    public LED(String name, Component input, Component output, String pin, String color, boolean willTurnOn) {
        super(name, input, output);
        this.pin = pin;
        this.color = color;
        this.willTurnOn = willTurnOn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isWillTurnOn() {
        return willTurnOn;
    }

    public void setWillTurnOn(boolean willTurnOn) {
        this.willTurnOn = willTurnOn;
    }

    @Override
    public String getComponentsCode() {
        CodeStructure.localVars.append("R").append(pin).append("=0;\r\n");
        if (willTurnOn) {
            return getTurnOnTemplate(pin);
        } else {
            return getTurnOffTemplate(pin);
        }
    }

    private String getTurnOnTemplate(String pin) {
        String x = "R" + pin + "=1;\r\n";
        return x;
    }

    private String getTurnOffTemplate(String pin) {
        String x = "R" + pin + "=0;\r\n";
        return x;
    }
}
