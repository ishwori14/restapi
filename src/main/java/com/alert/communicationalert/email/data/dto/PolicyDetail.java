package com.alert.communicationalert.email.data.dto;
import com.alert.communicationalert.email.data.model.Product;
import java.util.Date;
public class PolicyDetail {
    private String emailAddress;
    private String fullName;
    private String policyNumber;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    private Date date;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    private String type;
    private Product product;
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
