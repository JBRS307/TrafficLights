package pl.jbrs.traffic.jsonparser.inputoptions;

public enum AddPedestrianOption {
    Road;

    @Override
    public String toString() {
        return "road";
    }

    public static AddPedestrianOption fromString(String option) {
        if (option.equals("road")) {
            return Road;
        } else {
            throw new IllegalArgumentException("Unknown option: " + option);
        }
    }
}
