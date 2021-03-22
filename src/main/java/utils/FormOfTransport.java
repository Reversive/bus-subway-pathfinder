package utils;

public enum FormOfTransport {
    WALK(10,0,1.34),
    BUS(4, Graph.calculateWeight(200, FormOfTransport.WALK),16.67),
    SUBWAY(1, Graph.calculateWeight(30, FormOfTransport.WALK),12.5);

    private final int PENALTY;
    private final double FIXED_PENALTY;
    private final double SPEED;

    FormOfTransport(int penalty, double fixedPenalty, double speed) {
        this.PENALTY = penalty;
        this.FIXED_PENALTY = fixedPenalty;
        this.SPEED = speed;
    }

    public double getFIXED_PENALTY() {
        return FIXED_PENALTY;
    }

    public double getSPEED() {
        return SPEED;
    }

    public int getPENALTY() {
        return PENALTY;
    }
}
