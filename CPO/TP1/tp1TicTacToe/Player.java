public enum Player {
    EMPTY, ONE, TWO;

    private Player nextPlayer;

    static {
        TWO.nextPlayer = ONE;
        ONE.nextPlayer = TWO;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
