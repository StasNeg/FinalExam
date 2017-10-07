package finalExam.repository;

import finalExam.model.restaurant.Restaurant;
import finalExam.model.users.User;
import finalExam.model.votes.Vote;


import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    List<Vote> getAll();

    Vote save(Restaurant restaurant, User user);

    Vote save(Integer restaurantId, User user);

    List<User> getAllUserByDay(LocalDate date);

    List<Restaurant> getAllRestaurantByDay(LocalDate date);


}
