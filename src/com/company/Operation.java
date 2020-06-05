package com.company;

public class Operation {
    private String operationType, operationId, operationDate, payeeId, payerId;
    private double operationValue;
    private double payeeAccountNumber, payerAccountNumber;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public double getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(double operationValue) {
        this.operationValue = operationValue;
    }

    public double getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(double payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public double getPayerAccountNumber() {
        return payerAccountNumber;
    }

    public void setPayerAccountNumber(double payerAccountNumber) {
        this.payerAccountNumber = payerAccountNumber;
    }
}
