import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class Set extends Instruction {

	private Parametre index;
	private Valeur value;

	Set(Parametre n, Valeur v) {
		index = n;
		value = v;
	}

	@Override
	public String toString() {
		return "Set (" + index + ", " + value + ")";
	}

	@Override
	boolean execute(Machine m) {
		System.out.println(this);
		if (m.setMemory(index, value)){
			return m.nextInstruction();
		}
		else
			return false;
	}

}
	