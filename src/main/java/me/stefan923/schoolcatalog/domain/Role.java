package me.stefan923.schoolcatalog.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    public Role() { }

    public Role(int id, RoleType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType role) {
        this.type = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return getId() == role1.getId() && getType() == role1.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + type +
                '}';
    }

}
