package finalExam.model.votes;

import finalExam.model.IdAbstractClass;
import finalExam.model.restaurant.Restaurant;
import finalExam.model.users.User;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Vote.ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.GET_ALL_USERS_BY_DATE, query = "SELECT new User(v.user) FROM Vote v WHERE v.date=:date"),
        @NamedQuery(name = Vote.GET_ALL_RESTAURANT_BY_DATE, query = "SELECT new Restaurant(v.restaurant) FROM Vote v WHERE v.date=:date")
})


@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "votes_unique_user_id_date_idx")})
public class Vote extends IdAbstractClass {
    public static final String ALL = "Vote.getAll";
    public static final String DELETE = "Vote.delete";
    public static final String GET_ALL_USERS_BY_DATE = "Vote.getUserByDay";
    public static final String GET_ALL_RESTAURANT_BY_DATE = "Vote.getRestaurantByDay";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Vote() {
    }

    public Vote(Restaurant restaurant, User user, LocalDate date) {
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
    }

    public Vote(Integer id, Restaurant restaurant, User user, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;

        Vote vote = (Vote) o;

        if (restaurant != null ? !restaurant.equals(vote.restaurant) : vote.restaurant != null) return false;
        if (user != null ? !user.equals(vote.user) : vote.user != null) return false;
        return date != null ? date.equals(vote.date) : vote.date == null;
    }

    @Override
    public int hashCode() {
        int result = restaurant != null ? restaurant.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                ", id=" + id +
                ", date=" + date +
                ", user=" + user +
                "restaurant=" + restaurant +
                '}';
    }
}
