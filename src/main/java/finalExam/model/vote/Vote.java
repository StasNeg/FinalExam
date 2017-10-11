package finalExam.model.vote;

import finalExam.model.IdAbstractClass;
import finalExam.model.restaurant.Restaurant;
import finalExam.model.user.User;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Vote.ALL, query = "SELECT v FROM Vote v  Left JOIN FETCH v.user LEFT JOIN FETCH v.restaurant"),
        @NamedQuery(name = Vote.GET_BY_USER_AND_DATE, query = "SELECT v FROM Vote v WHERE v.date=:voteDate AND v.user.id =:userId"),
        @NamedQuery(name = Vote.GET_ALL_USERS_BY_DATE, query = "SELECT new User(v.user) FROM Vote v WHERE v.date=:date"),
        @NamedQuery(name = Vote.GET_ALL_RESTAURANT_BY_DATE, query = "SELECT new Restaurant(v.restaurant) FROM Vote v WHERE v.date=:date")
})


@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "user_id", "date_vote"}, name = "votes_unique_restaurantId_userId__dateVoteidx")})
public class Vote extends IdAbstractClass {
    public static final String ALL = "Vote.getAll";
    public static final String GET_ALL_USERS_BY_DATE = "Vote.getUserByDay";
    public static final String GET_ALL_RESTAURANT_BY_DATE = "Vote.getRestaurantByDay";
    public static final String GET_BY_USER_AND_DATE = "Vote.getByUserIdAndDate";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    @BatchSize(size = 200)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    @BatchSize(size = 200)
    private User user;

    @Column(name = "date_vote", nullable = false)
    private LocalDate date;

    public Vote() {
    }

    public Vote(User user, Restaurant restaurant, LocalDate date) {
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.date = date;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
