package finalExam.model.menu;

import finalExam.model.IdAbstractClass;

import finalExam.model.meal.Meal;
import finalExam.model.restaurant.Restaurant;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Menu.ALL, query = "SELECT m FROM Menu m WHERE m.restaurant.id = ?1"),
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id = :restaurantId"),
        @NamedQuery(name = Menu.ALL_BY_ADDRESS, query = "SELECT m FROM Menu m WHERE m.restaurant.address LIKE CONCAT('%',?1,'%')"),
        @NamedQuery(name = Menu.GET_BY_ADDRESS_BETWEEN_DATES, query = "SELECT m FROM Menu m " +
                "WHERE m.restaurant.address LIKE CONCAT('%',?1,'%') AND m.date >= ?2 AND m.date <= ?3 ORDER BY m.date DESC"),
        @NamedQuery(name = Menu.GET_BETWEEN_DATES, query = "SELECT m FROM Menu m " +
                "WHERE m.date >= ?1 AND m.date <= ?2 ORDER BY m.date DESC")
})

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurants_id"}, name = "" +
        "restaurants_unique_date_address_idx")})
public class Menu extends IdAbstractClass {
    public static final String ALL = "Menu.getAll";
    public static final String DELETE = "Menu.delete";
    public static final String ALL_BY_ADDRESS = "Menu.getAllByAddress";
    public static final String GET_BY_ADDRESS_BETWEEN_DATES = "Menu.getByAddressBetweenDates";
    public static final String GET_BETWEEN_DATES = "Menu.getBetweenDates";

    public Menu() {
    }

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @CollectionTable(name = "restaurants")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id")
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu",
            cascade = {CascadeType.REMOVE})
    @BatchSize(size = 200)
    @NotNull
    private Set<Meal> meals = new HashSet<>();

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Menu(Integer id, LocalDate date, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> menu) {
        this.meals = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        if (date != null ? !date.equals(menu.date) : menu.date != null) return false;
        return restaurant != null ? restaurant.equals(menu.restaurant) : menu.restaurant == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                '}';
    }
}
