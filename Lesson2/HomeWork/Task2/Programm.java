class Programm{
	public static void main(String[] args){
		Report report = new Report();
		report.calculate();
		report.output(new PrintReport());
		report.output(new ScreenReport());
	}
}