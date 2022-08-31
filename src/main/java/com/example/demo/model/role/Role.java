package com.example.demo.model.role;

import com.example.demo.model.common.BaseEntity;
import com.example.demo.model.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * @Author Eric
 * @Description
 * @Since 22. 8. 30.
 **/
@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "name", nullable = false, unique = true)
//    private RoleName name;
//
//
//    public Role(RoleName name) {
//        this.name = name;
//    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}
