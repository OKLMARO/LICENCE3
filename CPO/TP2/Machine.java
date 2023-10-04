class Machine {
    private final int[] cases_memoires;
    private Instruction[] instructions;

    private int instruction_counter;
    public Machine(int cases_memoires){
        this.cases_memoires = new int [cases_memoires];
        this.instruction_counter = 0;
    }

    public void setProgram(Instruction[] instructions){
        this.instructions = instructions;
    }

    public void printProgram(){
        for (Instruction instruction : this.instructions) {
            System.out.println(instruction);
        }

    }

    public void printMemory(){
        for (int i = 0; i < this.cases_memoires.length; i++) {
            System.out.println("Case " + i + " : " + this.cases_memoires[i]);
        }
    }

    public int getInstructionCounter(){
        return this.instruction_counter;
    }

    public void setInstruction_counter(int instruction_counter) {
        this.instruction_counter = instruction_counter;
    }

    public int getInstructionLength(){
        return this.instructions.length;
    }

    public boolean step(){
        if(this.instruction_counter >= this.instructions.length){
            return false;
        }
        return this.instructions[this.instruction_counter].execute(this);
    }

    public static void main(String[] args) {
        /*Machine m = new Machine(4); // une machine avec 4 cases mémoires
        Instruction[] program = {
                new Set (0, 3),
                new Set (1, 1),
                new Set (2, -1),
                new IfZero (0, 7),
                new Inc (0, 2),
                new Inc (1, 1),
                new IfZero (3, 3)
        } ;
        m.setProgram(program);
        m.printProgram();*/

        Machine m = new Machine(4); // une machine avec 4 cases mémoires
        Instruction[] program = {
                new Set (0, 3),
                new Set (1, 1),
                new Set (2, -1),
                new IfZero (0, 7),
                new Inc (0, 2),
                new Inc (1, 1),
                new IfZero (3, 3)
        } ; // Même programme qu'à la question précédente
        m.setProgram( program );
        do {
            m.printMemory(); // affiche la valeur des cases mémoires
            System.out.println(); // et un saut de ligne pour séparer
        } while (m.step()); // exécute une instruction, boucle si tout est ok
    }

    public int getCaseMemoire(int i) {
        return this.cases_memoires[i];
    }

    public void setCaseMemoire(int i, int j) {
        this.cases_memoires[i] = j;
    }
}