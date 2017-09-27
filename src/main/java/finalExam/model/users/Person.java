package finalExam.model.users;


import finalExam.model.IdNamedAbstractClass;

public class Person extends IdNamedAbstractClass{
    private Role role;

    public Person(Role role) {
        this.role = role;
    }

    public Person(String name, Role role) {
        super(name);
        this.role = role;
    }

    public Person(Integer id, String name, Role role) {
        super(id, name);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
