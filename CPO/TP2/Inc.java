class Inc implements Instruction{
    private int i, i1;
    public Inc(int i, int i1) {
        this.i = i;
        this.i1 = i1;
    }

    @Override
    public String toString(){
        return "Inc " + this.i + " " + this.i1;
    }

    @Override
    public boolean execute(Machine m){
        if(m.getInstructionCounter() >= m.getInstructionLength()){
            return false;
        }
        m.setCaseMemoire(this.i, m.getCaseMemoire(this.i) + m.getCaseMemoire(this.i1));
        m.setInstruction_counter(m.getInstructionCounter() + 1);
        return true;
    }
}