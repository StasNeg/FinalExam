package finalExam.model.restaurant;


import finalExam.model.IdNamedAbstractClass;
import finalExam.model.meal.Meal;
import finalExam.model.menu.Menu;
import finalExam.model.vote.Vote;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r "),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id ")
})

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"address"}, name = "" +
        "restaurants_unique_address_idx")})
public class Restaurant extends IdNamedAbstractClass {
    public static final String ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant",
            cascade = {CascadeType.REMOVE})
    @BatchSize(size = 200)
    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId(),restaurant.getName());
        this.address = restaurant.getAddress();
    }

    public Restaurant(String name, String address) {
        super(name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        if (!super.equals(o)) return false;

        Restaurant that = (Restaurant) o;

        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
