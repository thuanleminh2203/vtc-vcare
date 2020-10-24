package com.venesa.ctvvcare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author thuanlm
 * @created at 10/22/2020
 */
@Entity
@Table(name = "user", schema = "ctv-vcare", catalog = "")
public class User {
    private int id;
    private String password;
    private Timestamp timeToken;
    private String username;
    private String introductionCode;
    private Boolean isActive;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    @Getter
    @Setter
    @Column(name = "is_reset_password")
    private boolean isResetPassword;
    @Getter
    @Setter
    @Column(name = "role")
    private String role;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "time_token", nullable = true)
    public Timestamp getTimeToken() {
        return timeToken;
    }

    public void setTimeToken(Timestamp timeToken) {
        this.timeToken = timeToken;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "introduction_code", nullable = true, length = 30)
    public String getIntroductionCode() {
        return introductionCode;
    }

    public void setIntroductionCode(String introductionCode) {
        this.introductionCode = introductionCode;
    }

    @Basic
    @Column(name = "is_active", nullable = true)
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "created_date", nullable = true)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_by", nullable = true, length = 255)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "updated_date", nullable = true)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "updated_by", nullable = true, length = 255)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(password, that.password) &&
                Objects.equals(timeToken, that.timeToken) &&
                Objects.equals(username, that.username) &&
                Objects.equals(introductionCode, that.introductionCode) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedDate, that.updatedDate) &&
                Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, timeToken, username, introductionCode, isActive, createdDate, createdBy, updatedDate, updatedBy);
    }
}
