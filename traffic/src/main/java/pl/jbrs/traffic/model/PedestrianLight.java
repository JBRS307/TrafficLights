package pl.jbrs.traffic.model;

public class PedestrianLight {
    private boolean buttonPressed = false;
    private LightColor color = LightColor.RED;

    public void pressButton() {
        buttonPressed = true;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void changeColor() {
        color = color.nextPedestrian();
        if (color == LightColor.GREEN) {
            buttonPressed = false;
            // Light changed so button must be canceled
        }
    }
}
