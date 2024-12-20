package com.hrm.user_org_dep.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Organization")
public class Organization {
    @Id
    @Column(name = "org_id", nullable = false, length = 50)
    private String orgId = "GOH";

    @Column(name = "org_name", nullable = false, length = 100)
    private String orgName;

    @Column(name = "org_address", length = 200)
    private String orgAddress;

    @Column(name = "org_description", columnDefinition = "TEXT")
    private String orgDescription;

    // One-to-Many relationship with departments
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments;
    
}
