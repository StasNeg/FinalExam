package finalExam.model.restaurant;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.meal.Meal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@SuppressWarnings("JpaModelReferenceInspection")
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"date","name"}, name = "" +
        "restaurants_unique_date_name_idx")})
public class Restaurant extends IdNamedAbstractClass {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurants")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Meal> meals;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Restaurant(List<Meal> meals, LocalDate date) {
        this.meals = meals;
        this.date = date;
    }

    public Restaurant(Integer id, String name, List<Meal> meals, LocalDate date) {
        super(id, name);
        this.meals = meals;
        this.date = date;
    }

    public Restaurant(String name, List<Meal> meals, LocalDate date) {
        super(name);
        this.meals = meals;
        this.date = date;
    }

    public Restaurant() {
    }
}
