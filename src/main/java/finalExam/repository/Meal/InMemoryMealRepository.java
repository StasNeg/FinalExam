package finalExam.repository.Meal;

import finalExam.model.meal.Meal;
import finalExam.repository.MealRepository;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Stanislav on 27.09.2017.
 */
public class InMemoryMealRepository implements MealRepository {

    private AtomicInteger id = new AtomicInteger(0);
    private Map<Integer, Meal> meals = new ConcurrentHashMap();
    private static final Logger log = getLogger(InMemoryMealRepository.class);

    public InMemoryMealRepository() {
        init();
    }

    private void init() {
        log.debug("Init meals");
        meals.put(id.incrementAndGet(), new Meal(id.intValue(), "Salat", 23.4));
        meals.put(id.incrementAndGet(), new Meal(id.intValue(), "Meat Pork", 34.4));
        meals.put(id.incrementAndGet(), new Meal(id.intValue(), "Meat Sheep", 34.4));
        meals.put(id.incrementAndGet(), new Meal(id.intValue(), "Soup", 12.85));
        meals.put(id.incrementAndGet(), new Meal(id.intValue(), "Steak", 45.4));
        meals.put(id.incrementAndGet(), new Meal(id.intValue(), "Coffee", 10.5));
    }


    @Override
    public List<Meal> getAll() {
        log.info("get All meals");
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal get(Integer mealsId) {
        log.info("Get meal id =" + mealsId);
        return meals.get(mealsId);
    }

    @Override
    public boolean delete(Integer id) {
        log.info("delete meal id=" + id);
        return meals.remove(id) != null;
    }


    @Override
    public Meal save(Meal meal) {
        String debugLog = "";
        int currentId;
        if (meal.isNew()) {
            meals.put(id.incrementAndGet(), new Meal(id.intValue(), meal.getName(), meal.getCost()));
            currentId = id.intValue();
            debugLog = "Create new meal id=";
        } else {
            meals.put(meal.getId(), meal);
            currentId = meal.getId();
            debugLog = "Add meal id=";
        }
        debugLog += currentId;
        log.info(debugLog);
        return get(currentId);
    }
}
