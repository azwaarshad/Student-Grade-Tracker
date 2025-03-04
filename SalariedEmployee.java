public class SalariedEmployee extends Employee{
	private double salary;
	
	public SalariedEmployee(String id, String name, double salary) {
		super(id, name);
		this.salary = salary;
	}
	
	public double calculatePay() {
		return salary;
	}
}