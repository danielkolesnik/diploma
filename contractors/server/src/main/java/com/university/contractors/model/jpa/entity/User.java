package com.university.contractors.model.jpa.entity;

import com.university.contractors.model.jpa.domains.UserRole;
import lombok.*;

import javax.persistence.*;

/**
 * User
 *
 * @author   Barmin Oleg
 * @version  0.1.1
 */
@Entity
@Table(name = "system_users")
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "username"})
@EqualsAndHashCode(of = {"id"})
public class User implements IdEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;


    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

}
