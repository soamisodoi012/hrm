package com.hrm.leave_mgnt.model.entity;
import javax.print.DocFlavor.STRING;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name = "Leave_type")
    private String Leave_type;
}

