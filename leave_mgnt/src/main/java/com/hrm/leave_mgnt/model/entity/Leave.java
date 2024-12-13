package com.hrm.leave_mgnt.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;

import java.util.Date;

import com.hrm.leave_mgnt.constants.leave_type;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="leave")
public class Leave {
    @Id // This acts as the primary key.
    @Column (name = "leaveId",nullable = false,unique = true)
    private String leaveId;
    @Column(name = "username")
    private String username;
    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private leave_type leave_type;
    @Column(name = "status")
    private String status;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date  endDate;

    
}

