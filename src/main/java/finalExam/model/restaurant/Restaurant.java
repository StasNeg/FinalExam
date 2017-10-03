package finalExam.model.restaurant;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.meal.Meal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r "),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_BY_NAME, query = "SELECT r FROM Restaurant r WHERE r.name = ?1"),
        @NamedQuery(name = Restaurant.GET_BY_NAME_BETWEEN_DATES, query = "SELECT r FROM Restaurant r " +
                "WHERE r.name = ?1 AND r.date >= ?2 AND r.date <= ?3 ORDER BY r.date DESC"),
        @NamedQuery(name = Restaurant.GET_BETWEEN_DATES, query = "SELECT r FROM Restaurant r " +
                "WHERE r.date >= ?1 AND r.date <= ?2 ORDER BY r.date DESC")
})

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"date","name"}, name = "" +
        "restaurants_unique_date_name_idx")})
public class Restaurant extends IdNamedAbstractClass {
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_BY_NAME = "Restaurant.getAllByName";
    public static final String GET_BY_NAME_BETWEEN_DATES = "Restaurant.getByNamedBetweenDates";
    public static final String GET_BETWEEN_DATES = "Restaurant.getBetweenDates";

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant",
            cascade = {CascadeType.REMOVE})
    @OrderBy("price ASC")
    private List<Meal> meals = new ArrayList<>();
    @Column(name = "date", nullable = false)
    private LocalDate date;

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

    public Restaurant(Integer id, String name, LocalDate date) {
        super(id, name);
        this.date = date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

        if (meals != null ? !meals.equals(that.meals) : that.meals != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", meals=" + meals +
                '}';
    }
}
