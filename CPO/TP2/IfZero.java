class IfZero implements Instruction{
    private int i, i1;
    public IfZero(int i, int i1) {
        this.i = i;

        this.i1 = i1;
    }

    @Override
    public String toString(){
        return "IfZero " + this.i + " " + this.i1;
    }

    @Override
    public boolean execute(Machine m){
        if(m.getInstructionCounter() >= m.getInstructionLength()){
            return false;
        }
        else if(m.getCaseMemoire(this.i) == 0){
            m.setInstruction_counter(this.i1);
            return true;
        }
        m.setInstruction_counter(m.getInstructionCounter() + 1);
        return true;
    }
}