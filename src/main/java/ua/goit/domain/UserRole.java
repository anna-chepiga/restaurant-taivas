package ua.goit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return getName() != null ? getName().equals(userRole.getName()) : userRole.getName() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}