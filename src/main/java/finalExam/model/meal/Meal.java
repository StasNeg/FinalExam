package finalExam.model.meal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import finalExam.model.IdNamedAbstractClass;
import finalExam.model.menu.Menu;
import finalExam.model.restaurant.Restaurant;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@NamedQueries({
        @NamedQuery(name = Meal.ALL, query = "SELECT m FROM Meal m WHERE m.menu.id=:menuId "),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.menu.id=:menuId")
})
@Entity
@Table(name = "meals",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name","menu_id","price"}, name = "" +
        "meals_unique_name_restaurantID_price_idx")})
public class Meal extends IdNamedAbstractClass {

    public static final String ALL = "Meal.getAll";
    public static final String DELETE = "Meal.delete";

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 1000)
    private double price;

    @CollectionTable(name = "menu")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @NotNull
    @JsonIgnore
    private Menu menu;

    public Meal() {
    }

    public Meal(Integer id, String name, double price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    public Meal(Integer id, String name, double price) {
        super(id, name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        if (!super.equals(o)) return false;

        Meal meal = (Meal) o;

        return Double.compare(meal.price, price) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
