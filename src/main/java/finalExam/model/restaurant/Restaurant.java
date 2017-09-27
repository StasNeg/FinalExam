package finalExam.model.restaurant;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.meal.Meal;

import java.time.LocalDateTime;
import java.util.List;

public class Restaurant extends IdNamedAbstractClass {
    private List<Meal> dinner;
    private LocalDateTime orderDate;

    public Restaurant(List<Meal> dinner, LocalDateTime orderDate) {
        this.dinner = dinner;
        this.orderDate = orderDate;
    }

    public Restaurant(Integer id, String name, List<Meal> dinner, LocalDateTime orderDate) {
        super(id, name);
        this.dinner = dinner;
        this.orderDate = orderDate;
    }

    public Restaurant(String name, List<Meal> dinner, LocalDateTime orderDate) {
        super(name);
        this.dinner = dinner;
        this.orderDate = orderDate;
    }
}
