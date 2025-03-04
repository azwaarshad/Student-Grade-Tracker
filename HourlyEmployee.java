public class HourlyEmployee extends Employee{
	private double hourlyRate;
	private int hoursWorked;
	
	public HourlyEmployee(String id, String name, double hourlyRate, int hoursWorked) {
		super(id, name);
		this.hourlyRate = hourlyRate;
		this.hoursWorked = hoursWorked;
	}
	
	public double calculatePay() {
		return hourlyRate * hoursWorked;
	}
}