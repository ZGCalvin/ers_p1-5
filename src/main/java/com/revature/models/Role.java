package com.revature.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import javax.persistence.Table;

@Entity
@Table(name = "ers_user_roles")
public enum Role {
    ADMIN("Admin"),
    FINANCE_MANAGER("Finance Manager"),
    EMPLOYEE("Employee"),
    DELETED("Deleted");

    @Column(name="role_name")
    private String roleName;


//    @OneToOne(mappedBy = "user")
//    private User user;

    Role(String name) {
        this.roleName = name;
    }

    public static Role getByName(String name) {

        for (Role role : Role.values()) {
            if (role.roleName.equals(name)) {
                return role;
            }
        }
        return EMPLOYEE;
    }

    @Override
    public String toString() {
        return roleName;
    }

}
