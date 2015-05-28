package model;

public class Button extends Component {

    private boolean isActiveLow = false;
    private String pin = "";

    public Button(String name, Component input, Component output, String pin, boolean isActiveLow) {
        super(name, input, output);
        this.isActiveLow = isActiveLow;
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

    @Override
    public String getComponentsCode() {
        return getButtonStartTemplate(pin, isActiveLow);
    }

    private String getButtonStartTemplate(String pin, boolean isActiveLow) {
        String x;
        if (isActiveLow) {
            x = "if(!" + pin + "){\r\n";
        } else {
            x = "if(" + pin + "){\r\n";
        }
        return x;
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
    }

    @Override
    public void showConfigDialog() {
    }

    @Override
    public String getPrintedValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
