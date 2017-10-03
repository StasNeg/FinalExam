package finalExam.model.meal;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.restaurant.Restaurant;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@NamedQueries({
        @NamedQuery(name = Meal.ALL, query = "SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId "),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "meals",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name","restaurants_id","price"}, name = "" +
        "meals_unique_name_restaurantID_price_idx")})
public class Meal extends IdNamedAbstractClass {

    public static final String ALL = "Meal.getAll";
    public static final String DELETE = "Meal.delete";


    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 1000)
    private double price;


    @CollectionTable(name = "restaurants")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurants_id")
//    @NotNull
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", cost=" + price +
                ", name='" + name + '\'' +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        if (!super.equals(o)) return false;

        Meal meal = (Meal) o;

        if (Double.compare(meal.price, price) != 0) return false;
        return restaurant != null ? restaurant.equals(meal.restaurant) : meal.restaurant == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }
}
