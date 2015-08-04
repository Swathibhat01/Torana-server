package com.torana.quartz.api.ws;


public class PurchaseOrderAcknowledgment {
	
	private String orderRejectionMessage;

	private String purchaseOrderDate;

	private String purchaseOrderNumber;

	private String shipDate;

	private String labelTrackingNumber;
	
	private String fileContent;

	private String orderStatus;

	private String fileName;
	
	

	public String getOrderRejectionMessage() {
		return orderRejectionMessage;
	}

	public void setOrderRejectionMessage(String orderRejectionMessage) {
		this.orderRejectionMessage = orderRejectionMessage;
	}

	public String getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(String purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getLabelTrackingNumber() {
		return labelTrackingNumber;
	}

	public void setLabelTrackingNumber(String labelTrackingNumber) {
		this.labelTrackingNumber = labelTrackingNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
