package pl.jbrs.traffic.model;

public class PedestrianLight {
    private boolean buttonPressed = false;
    private LightColor color = LightColor.RED;

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setColor(LightColor color) {
        if (color != LightColor.RED && color != LightColor.GREEN) {
            throw new IllegalArgumentException("Color should be red or green for pedestrian light");
        }
        this.color = color;
    }

    public LightColor getColor() {
        return color;
    }
}
