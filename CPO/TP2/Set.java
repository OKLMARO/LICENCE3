class Set implements Instruction{
    private int i, i1;
    public Set(int i, int i1) {
        this.i = i;
        this.i1 = i1;
    }

    @Override
    public String toString() {
        return "Set " + this.i + " " + this.i1;
    }

    @Override
    public boolean execute(Machine m){
        m.setCaseMemoire(this.i, this.i1);
        m.setInstruction_counter(m.getInstructionCounter() + 1);
        return true;
    }
}