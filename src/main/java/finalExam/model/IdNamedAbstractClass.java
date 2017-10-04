package finalExam.model;



import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@MappedSuperclass

// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class IdNamedAbstractClass extends IdAbstractClass{

    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;

    public IdNamedAbstractClass() {}

    public IdNamedAbstractClass(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public IdNamedAbstractClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdNamedAbstractClass)) return false;

        IdNamedAbstractClass that = (IdNamedAbstractClass) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
