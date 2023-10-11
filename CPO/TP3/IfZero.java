public class IfZero extends Instruction {
	private int index;
	private int instr;

	public IfZero(int n, int v) {
		index = n;
		instr = v;
	}

	@Override
	public String toString() {
		return "IfZero (" + index + ", " + instr + ")";
	}

	@Override
	boolean execute(Machine m) {
		System.out.println(this);
		if (m.getMemoryElmt(index) == 0)
			return m.setCurrent(instr);
		else
			return m.nextInstruction();
	}
}
