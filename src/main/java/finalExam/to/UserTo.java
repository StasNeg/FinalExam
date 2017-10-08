package finalExam.to;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserTo  {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @Size(min = 5, max = 32, message = "length must between 5 and 32 characters")
    private String password;
    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        this.id =id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
