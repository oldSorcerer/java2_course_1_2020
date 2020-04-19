package org.levelup.application.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name= "users")
public class UserEntity {

    @Id    // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false) //varchar(100) not null
    private String name;
    @Column(name = "last_name", length = 100, nullable = false) //varchar(100) not null
    private String lastName;
    @Column(length = 50, nullable = false, unique = true) //varchar(50) not null unique
    private String passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private List<UserAddressEntity> addresses;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
