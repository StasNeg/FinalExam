package finalExam.model;



import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@MappedSuperclass

// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class IdNamedAbstractClass {
    public static final int START_SEQ = 100000;
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;

    public IdNamedAbstractClass() {}

    public IdNamedAbstractClass(String name) {
        this.name = name;
    }

    public IdNamedAbstractClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id.equals(null);
    }
}
