package finalExam.model;


public abstract class IdNamedAbstractClass {
    protected Integer id;
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
