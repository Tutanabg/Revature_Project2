package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_types")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id")
    private int paymentTypeID;
    @Column(name = "payment_type_name")
    private String paymentTypeName;

    public PaymentType() {
    }

    public PaymentType(int paymentTypeID, String paymentTypeName) {
        this.paymentTypeID = paymentTypeID;
        this.paymentTypeName = paymentTypeName;
    }

    public int getpaymentTypeID() {
        return paymentTypeID;
    }

    public void setpaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentType)) return false;
        PaymentType that = (PaymentType) o;
        return getpaymentTypeID() == that.getpaymentTypeID() && Objects.equals(getPaymentTypeName(), that.getPaymentTypeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getpaymentTypeID(), getPaymentTypeName());
    }

    @Override
    public String toString() {
        return "PaymentTypes{" +
                "paymentTypeID=" + paymentTypeID +
                ", paymentTypeName='" + paymentTypeName + '\'' +
                '}';
    }
}
