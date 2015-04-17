package cloudigrate.api.domain;

public class AWSServiceRateItem {
	private String productName;
	private String operation;
	private Double rate;
	public String getProductName() {
		return productName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
}
