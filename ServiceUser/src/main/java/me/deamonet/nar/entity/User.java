package me.deamonet.nar.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user", schema = "read_and_note")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "username", length = 30, unique = true, nullable = false)
    private String username;
    @Column(name = "email", length = 30, unique = true, nullable = true)
    private String email;
    @Column(name = "phone_number", length = 20, unique = true, nullable = true)
    private String phoneNumber;
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @Column(name = "registration", columnDefinition = "datatime default now()")
    private Date registration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
