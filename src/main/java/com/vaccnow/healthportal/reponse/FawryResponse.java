package com.vaccnow.healthportal.reponse;

public class FawryResponse {
	private String type;
    private String referenceNumber;
    private String merchantRefNumber;
    private double orderAmount;
    private double paymentAmount;
    private int fawryFees;
    private String paymentMethod;
    private long expirationTime;
    private String customerMobile;
    private String customerMail;
    private String customerProfileId;
    private String signature;
    private int statusCode;
    private String statusDescription;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getMerchantRefNumber() {
		return merchantRefNumber;
	}
	public void setMerchantRefNumber(String merchantRefNumber) {
		this.merchantRefNumber = merchantRefNumber;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public int getFawryFees() {
		return fawryFees;
	}
	public void setFawryFees(int fawryFees) {
		this.fawryFees = fawryFees;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public long getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public String getCustomerProfileId() {
		return customerProfileId;
	}
	public void setCustomerProfileId(String customerProfileId) {
		this.customerProfileId = customerProfileId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
}
