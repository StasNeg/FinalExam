package finalExam.model.meal;


import finalExam.model.IdNamedAbstractClass;

public class Meal extends IdNamedAbstractClass {

    private double cost;

    public Meal() {
    }

    public Meal(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Meal(Integer id, String name, double cost) {
        super(id, name);
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
