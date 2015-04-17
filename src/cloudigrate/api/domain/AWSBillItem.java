package cloudigrate.api.domain;

public class AWSBillItem {

	private String productName;
	private String usageType;
	private String operation;
	private String availabilityZone;
	private Double usageQuantity;
	private Double cost;
	private String resourceId;
	
	
	
	public AWSBillItem(String productName, String usageType, String operation,
			String availabilityZone, Double usageQuantity, Double cost,
			String resourceId) {
		super();
		this.productName = productName;
		this.usageType = usageType;
		this.operation = operation;
		this.availabilityZone = availabilityZone;
		this.usageQuantity = usageQuantity;
		this.cost = cost;
		this.resourceId = resourceId;
	}
	
	
	public AWSBillItem(String productName) {
		// TODO Auto-generated constructor stub
		this.productName = productName;
	}


	public AWSBillItem() {
		// TODO Auto-generated constructor stub
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUsageType() {
		return usageType;
	}
	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getAvailabilityZone() {
		return availabilityZone;
	}
	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}
	public Double getUsageQuantity() {
		return usageQuantity;
	}
	public void setUsageQuantity(Double usageQuantity) {
		this.usageQuantity = usageQuantity;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	
	
}
