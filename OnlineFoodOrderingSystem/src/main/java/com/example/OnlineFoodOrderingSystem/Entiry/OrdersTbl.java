package com.example.OnlineFoodOrderingSystem.Entiry;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class OrdersTbl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_seq")
    Integer id;
    String productId;
    String custName;
    String custAddress;
    String custPhone;
    String custEmail;
    @CreationTimestamp
    LocalDate curDate;

    public OrdersTbl(String productId, String custName, String custAddress, String custPhone, String custEmail, LocalDate curDate) {
        this.productId = productId;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
        this.custEmail = custEmail;
        this.curDate = curDate;
    }

    public OrdersTbl() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public LocalDate getCurDate() {
        return curDate;
    }

    public void setCurDate(LocalDate curDate) {
        this.curDate = curDate;
    }

    @Override
    public String toString() {
        return "OrdersTbl{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", custName='" + custName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", curDate=" + curDate +
                '}';
    }
}
