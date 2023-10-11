public class App {

	public static void main(String[] args) {
		Machine m = new Machine(4); 
		m.setProgram( new Set(0, 3), 
				      new Set(1, 1), 
				      new Set(2, -1),
				      new IfZero(0, 7), 
				      new Inc(0, 2),
				      new Inc(1, 1),
				      new IfZero(3, 3));
		m.printProgram();
		System.out.println();
		do {
			m.printMemory();
			System.out.println();
		} while (m.step());
		m.printMemory();
	}
}
