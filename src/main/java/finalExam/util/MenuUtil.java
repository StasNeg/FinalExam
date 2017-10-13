package finalExam.util;

import finalExam.model.meal.Meal;
import finalExam.model.menu.Menu;
import finalExam.model.restaurant.Restaurant;
import finalExam.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;


public class MenuUtil {
    public static List<MenuTo> getWithSumTotal(List<Menu> menu) {
        return menu.stream().map(MenuUtil::getWithSumTotal).collect(Collectors.toList());
    }
    public static Menu getFromSumTotal(MenuTo menuTo) {
        return new Menu(menuTo.getId(),menuTo.getDate());
    }

    public static MenuTo getWithSumTotal(Menu menu){
        return new MenuTo(menu.getId(), menu.getRestaurant().getName(), menu.getRestaurant().getAddress(), menu.getDate(),
                menu.getMeals().stream().mapToDouble(Meal::getPrice).sum());
    }

}
