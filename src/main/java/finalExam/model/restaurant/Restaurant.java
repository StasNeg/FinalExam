package finalExam.model.restaurant;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.meal.Meal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})




@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"date","name"}, name = "" +
        "restaurants_unique_date_name_idx")})
public class Restaurant extends IdNamedAbstractClass {
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    @OneToMany(fetch = FetchType.EAGER, mappedBy="restaurant", cascade = CascadeType.REMOVE)
    private List<Meal> meals;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Restaurant(List<Meal> meals, LocalDate date) {
        this.meals = meals;
        this.date = date;
    }

    public Restaurant(Integer id, String name, LocalDate date, List<Meal> meals) {
        super(id, name);
        this.meals = meals;
        this.date = date;
    }

    public Restaurant(String name, LocalDate date, List<Meal> meals) {
        super(name);
        this.meals = meals;
        this.date = date;
    }

    public Restaurant() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        if (!super.equals(o)) return false;

        Restaurant that = (Restaurant) o;

        if (!meals.equals(that.meals)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + meals.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meals=" + meals +
                ", date=" + date +
                '}';
    }
}
