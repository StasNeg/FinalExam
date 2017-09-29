package finalExam.model.users;


import finalExam.model.IdNamedAbstractClass;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends IdNamedAbstractClass{

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;



    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    private String password;


    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate registered = LocalDate.now();

    public User(Set<Role> role) {
        this.role = role;
    }

    public User(String name, Set<Role> role) {
        super(name);
        this.role = role;
    }

    public User(Integer id, String name, Set<Role> role) {
        super(id, name);
        this.role = role;
    }

    public User() {
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
