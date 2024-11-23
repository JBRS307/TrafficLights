package pl.jbrs.traffic.jsonparser.configoptions;

public enum ModelOption {
    StateLength,
    PriorityMultiplier,
    YellowTime,
    MinStateLength;

    @Override
    public String toString() {
        return switch (this) {
            case StateLength -> "stateLength";
            case PriorityMultiplier -> "priorityMultiplier";
            case YellowTime -> "yellowTime";
            case MinStateLength -> "minStateLength";
        };
    }
}
