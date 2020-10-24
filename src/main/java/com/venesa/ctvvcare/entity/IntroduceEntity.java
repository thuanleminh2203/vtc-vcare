package com.venesa.ctvvcare.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
@Entity
@Table(name = "introduce", schema = "ctv-vcare", catalog = "")
public class IntroduceEntity {
    private long introduceId;
    private Long customerId;
    private Long introduceCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "introduce_id", nullable = false)
    public long getIntroduceId() {
        return introduceId;
    }

    public void setIntroduceId(long introduceId) {
        this.introduceId = introduceId;
    }

    @Basic
    @Column(name = "customer_id", nullable = true)
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "introduce_customer_id", nullable = true)
    public Long getIntroduceCustomerId() {
        return introduceCustomerId;
    }

    public void setIntroduceCustomerId(Long introduceCustomerId) {
        this.introduceCustomerId = introduceCustomerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntroduceEntity that = (IntroduceEntity) o;
        return introduceId == that.introduceId &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(introduceCustomerId, that.introduceCustomerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(introduceId, customerId, introduceCustomerId);
    }
}
