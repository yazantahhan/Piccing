package model;

public class Button extends Component {

    private boolean isActiveLow = false;
    private String port = "";
    private String pin = "";

    public Button(String name, Component input, Component output, String port, String pin, boolean isActiveLow) {
        super(name, input, output);
        this.isActiveLow = isActiveLow;
        this.port = port;
        this.pin = pin;
    }

    public boolean isIsActiveLow() {
        return isActiveLow;
    }

    public void setIsActiveLow(boolean isActiveLow) {
        this.isActiveLow = isActiveLow;
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
