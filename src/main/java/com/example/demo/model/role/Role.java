package com.example.demo.model.role;

import com.example.demo.model.common.BaseEntity;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name")
    private RoleName name;

//    @ManyToOne
//    @JoinColumn(name = "parent_member_id")
//    private Role parentRole;

    public Role(RoleName name) {
        this.name = name;
    }
}
