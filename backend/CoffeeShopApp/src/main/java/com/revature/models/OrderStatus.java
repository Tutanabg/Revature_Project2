package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "order_statuses")
public class OrderStatus {
    @Id
    @Column(name = "status_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int statusID;
    @Column(name = "status")
    private String status;

    public OrderStatus() {
    }

    public OrderStatus(int statusID, String status) {
        this.statusID = statusID;
        this.status = status;
    }

    public int getstatusID() {
        return statusID;
    }

    public void setstatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderStatus)) return false;
        OrderStatus that = (OrderStatus) o;
        return getstatusID() == that.getstatusID() && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getstatusID(), getStatus());
    }

    @Override
    public String toString() {
        return "OrderStatuses{" +
                "statusID=" + statusID +
                ", status='" + status + '\'' +
                '}';
    }
}
