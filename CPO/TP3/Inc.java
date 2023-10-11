public class Inc extends Instruction {
	private int index1;
	private int index2;

	Inc(int n, int v) {
		index1 = n;
		index2 = v;
	}

	@Override
	public String toString() {
		return "Inc (" + index1 + ", " + index2 + ")";
	}

	@Override
	boolean execute(Machine m) {
		System.out.println(this);
		if (m.setMemory(index1, m.getMemoryElmt(index1) + m.getMemoryElmt(index2)))
			return m.nextInstruction();
		else
			return false;
	}
}
