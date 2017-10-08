package finalExam.util;

import finalExam.model.meal.Meal;
import finalExam.model.restaurant.Restaurant;
import finalExam.to.RestaurantTO;

import java.util.List;
import java.util.stream.Collectors;


public class RestaurantUtil {
    public static List<RestaurantTO> getWithSumTotal(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantUtil::getWithSumTotal).collect(Collectors.toList());
    }
    public static Restaurant getFromSumTotal(RestaurantTO restaurant) {
        return new Restaurant(restaurant.getId(),restaurant.getName(),restaurant.getAddress(),restaurant.getDate());
    }

    public static RestaurantTO getWithSumTotal(Restaurant restaurant){
        return new RestaurantTO(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getDate(),
                restaurant.getMeals().stream().mapToDouble(Meal::getPrice).sum());
    }

}
