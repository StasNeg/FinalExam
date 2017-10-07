package finalExam.util;

import finalExam.model.restaurant.Restaurant;
import finalExam.to.RestaurantWithPriceTotal;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class RestaurantUtil {
    public static List<RestaurantWithPriceTotal> getWithSumTotal(List<Restaurant> restaurants) {
        return restaurants.stream().map(x -> {
            final double[] total = {0};
            x.getMeals().forEach(y -> total[0] += y.getPrice());
            return new RestaurantWithPriceTotal(x.getId(), x.getName(), x.getAddress(), x.getDate(), total[0]);
        }).collect(Collectors.toList());
    }
    public static Restaurant getFromSumTotal(RestaurantWithPriceTotal restaurant) {
        return new Restaurant(restaurant.getId(),restaurant.getName(),restaurant.getAddress(),restaurant.getDate());
    }
}
