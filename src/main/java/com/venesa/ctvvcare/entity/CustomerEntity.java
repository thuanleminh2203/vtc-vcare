package com.venesa.ctvvcare.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author thuanlm
 * @created at 10/27/2020
 */
@Entity
@Table(name = "customer", schema = "landingpage", catalog = "")
public class CustomerEntity {
    private long customerId;
    private String introductionCode;
    private String phoneNumber;
    private String address;
    private String identifyCard;
    private String email;
    private String bankName;
    private String bankAccountNumber;
    private String bankAccountName;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private String customerName;
    private Boolean isActive;
    private String introduceCustomerCode;

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "introduction_code")
    public String getIntroductionCode() {
        return introductionCode;
    }

    public void setIntroductionCode(String introductionCode) {
        this.introductionCode = introductionCode;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "identify_card")
    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Basic
    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    @Column(name = "bank_account_number")
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Basic
    @Column(name = "bank_account_name")
    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    @Basic
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "updated_date")
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Basic
    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "introduce_customer_code")
    public String getIntroduceCustomerCode() {
        return introduceCustomerCode;
    }

    public void setIntroduceCustomerCode(String introduceCustomerCode) {
        this.introduceCustomerCode = introduceCustomerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return customerId == that.customerId &&
                Objects.equals(introductionCode, that.introductionCode) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(address, that.address) &&
                Objects.equals(identifyCard, that.identifyCard) &&
                Objects.equals(email, that.email) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(bankAccountNumber, that.bankAccountNumber) &&
                Objects.equals(bankAccountName, that.bankAccountName) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(introduceCustomerCode, that.introduceCustomerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, introductionCode, phoneNumber, address, identifyCard, email, bankName, bankAccountNumber, bankAccountName, createdDate, updatedDate, createdBy, updatedBy, customerName, isActive, introduceCustomerCode);
    }
}
