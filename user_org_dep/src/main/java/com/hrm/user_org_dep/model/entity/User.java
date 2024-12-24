package com.hrm.user_org_dep.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username; // This acts as the primary key.
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "userId")
    private String userId;
    
    @Column(name = "salary")
    private BigDecimal salary; // Changed to BigDecimal for precision
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "age")
    private BigDecimal age; // Changed to BigDecimal for precision
    
    @Column(name = "totalLeave", precision = 5, scale = 2) // Precision and scale
    private BigDecimal totalLeave; // Changed to BigDecimal for precision
    
    @ManyToOne
    @JoinColumn(name = "depId", nullable = false)
    @ToString.Exclude
    private Department department;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "experiance")
    private String experiance;
    
    @Column(name = "isLocked")
    private boolean isLocked;
}