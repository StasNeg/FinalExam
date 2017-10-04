package finalExam.repository;

import finalExam.model.restaurant.Restaurant;
import finalExam.model.users.User;
import finalExam.model.votes.Vote;


import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote get(Integer id);

    void delete(Integer id);

    Vote save(Vote vote);

    List<Vote> getAll();

    List<User> getAllUserByDay(LocalDate date);

    List<Restaurant> getAllRestaurantByDay(LocalDate date);

}
