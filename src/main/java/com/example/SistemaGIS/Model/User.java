package com.example.SistemaGIS.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"person", "userOwner", "userRoles"})
@EqualsAndHashCode(exclude = {"person", "userOwner", "userRoles"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Owner> userOwner;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Integer status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    )
    @JsonManagedReference
    private Collection<Role> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }
}
