package finalExam.model.user;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.vote.Vote;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=?1"),
        @NamedQuery(name = User.ALL, query = "SELECT u FROM User u"),

})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends IdNamedAbstractClass{
    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String ALL = "User.getAll";
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER )
    @BatchSize(size = 200)
    private Set<Role> roles;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    private String password;


    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate registered = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.REMOVE})
    Set<Vote> votes;

    public User(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String name, Set<Role> roles) {
        super(name);
        this.roles = roles;
    }

    public User(Integer id, String name, Set<Role> roles) {
        super(id, name);
        this.roles = roles;
    }

    public User(Integer id, String name, String email, Set<Role> roles) {
        super(id, name);
        this.roles = roles;
        this.email = email;
    }

    public User(Integer id, String name, String email, String password, LocalDate registered, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.registered = registered;
    }
    public User(Integer id, String name, String email, String password, Role role, Role ... roles) {
        super(id, name);
        this.email = email;
        this.roles = EnumSet.of(role, roles);
        this.password = password;

    }

    public User(User user) {
        super(user.getId(), user.getName());
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.password = user.getPassword();
    }
    public User(Integer id, String name, String email, String password, Set<Role> role) {
        super(id, name);
        this.email = email;
        this.roles = role;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }
    public User() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return registered != null ? registered.equals(user.registered) : user.registered == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", password='" + password + '\'' +
                ", registered=" + registered +
//                ", vote=" + vote +
                '}';
    }
}
