package narif.poc.spring.microservices.employeeservice.events.model;

public class DepartmentsChangeModel {
	private String action;
	private Long deptartmentId;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Long getDeptartmentId() {
		return deptartmentId;
	}
	public void setDeptartmentId(Long deptartmentId) {
		this.deptartmentId = deptartmentId;
	}
	public DepartmentsChangeModel() {
		super();
	}
	public DepartmentsChangeModel(String action, Long deptartmentId) {
		super();
		this.action = action;
		this.deptartmentId = deptartmentId;
	}
	@Override
	public String toString() {
		return "DepartmentsChangeModel [action=" + action + ", deptartmentId=" + deptartmentId + "]";
	}
	
	

}
