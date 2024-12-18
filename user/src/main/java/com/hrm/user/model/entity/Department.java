package com.hrm.user.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Department")
public class Department {
 @Id
    @Column(name = "dep_id", nullable = false, length = 50)
    private String depId;

    @Column(name = "dep_name", columnDefinition = "TEXT")
    private String depName;

    @ManyToOne
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    @JsonIgnore // Prevent Jackson from serializing parentDepartment to avoid the cycle
    private Department parentDepartment;

    @OneToMany(mappedBy = "parentDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Prevent Jackson from serializing subDepartments to avoid the cycle
    private List<Department> subDepartments;
}