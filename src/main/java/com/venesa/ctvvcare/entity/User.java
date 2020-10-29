package com.venesa.ctvvcare.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author thuanlm
 * @created at 10/27/2020
 */
@Entity
public class User {
    private int id;
    private String password;
    private String username;
    private String introductionCode;
    private Boolean isActive;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private String role;
    private String tokenResetPassword;
    private Date expiryDateToken;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "token_reset_password")
    public String getTokenResetPassword() {
        return tokenResetPassword;
    }

    public void setTokenResetPassword(String tokenResetPassword) {
        this.tokenResetPassword = tokenResetPassword;
    }

    @Basic
    @Column(name = "expiry_date_token")
    public Date getExpiryDateToken() {
        return expiryDateToken;
    }

    public void setExpiryDateToken(Date expiryDateToken) {
        this.expiryDateToken = expiryDateToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(password, user.password) &&
                Objects.equals(username, user.username) &&
                Objects.equals(introductionCode, user.introductionCode) &&
                Objects.equals(isActive, user.isActive) &&
                Objects.equals(createdDate, user.createdDate) &&
                Objects.equals(createdBy, user.createdBy) &&
                Objects.equals(updatedDate, user.updatedDate) &&
                Objects.equals(updatedBy, user.updatedBy) &&
                Objects.equals(role, user.role) &&
                Objects.equals(tokenResetPassword, user.tokenResetPassword) &&
                Objects.equals(expiryDateToken, user.expiryDateToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, username, introductionCode, isActive, createdDate, createdBy, updatedDate, updatedBy, role, tokenResetPassword, expiryDateToken);
    }
}
