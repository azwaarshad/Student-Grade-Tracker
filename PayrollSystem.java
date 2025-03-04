import java.util.List;
import java.util.ArrayList;

public class PayrollSystem {
	private List<Employee> employees;
	
	public PayrollSystem() {
		this.employees = new ArrayList<>();
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public double CalculatePayroll() {
		double totalPayroll = 0.0;
		for(Employee employee : employees) {
			totalPayroll += employee.calculatePay();
		}
		return totalPayroll;
	}
}