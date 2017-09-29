package finalExam.model.meal;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.restaurant.Restaurant;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meals")
public class Meal extends IdNamedAbstractClass {

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 1000)
    private double price;


    @CollectionTable(name = "restaurants")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurants_id")
    @NotNull
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String name, double cost) {
        this.name = name;
        this.price = cost;
    }

    public Meal(Integer id, String name, double price) {
        super(id, name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double cost) {
        this.price = cost;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", cost=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
