package com.example.Chi.models;

import java.io.Serializable;

public class Invoice implements Serializable {
    int InvoiceID;
    String PatientID;
    String PatientName;
    String DepartmentName;
    String AddressHos;
    String CreateDateTime;
    double PriceService;
    String Status;


    public Invoice(int invoiceID, String patientID, String patientName, String departmentName, String addressHos, String createDateTime, double priceService, String status) {
        InvoiceID = invoiceID;
        PatientID = patientID;
        PatientName = patientName;
        DepartmentName = departmentName;
        AddressHos = addressHos;
        CreateDateTime = createDateTime;
        PriceService = priceService;
        Status = status;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        InvoiceID = invoiceID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getAddressHos() {
        return AddressHos;
    }

    public void setAddressHos(String addressHos) {
        AddressHos = addressHos;
    }

    public String getCreateDateTime() {
        return CreateDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        CreateDateTime = createDateTime;
    }

    public double getPriceService() {
        return PriceService;
    }

    public void setPriceService(double priceService) {
        PriceService = priceService;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
