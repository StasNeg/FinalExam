package finalExam.repository.jpaImp;


import finalExam.model.meal.Meal;
import finalExam.model.menu.Menu;
import finalExam.repository.AbstractDaoImpl;
import finalExam.repository.MealRepository;
import finalExam.util.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class JPAMealRepositoryImpl extends AbstractDaoImpl<Meal> implements MealRepository {

    public JPAMealRepositoryImpl() {
        super(Meal.class);
    }

    public List<Meal> getAll(Integer menutId) {
        return em.createNamedQuery(Meal.ALL, Meal.class)
                .setParameter("menuId", menutId)
                .getResultList();
    }


    public Meal get(Integer id, Integer menuId) {
        Meal meal = super.get(id);
        if(meal == null || !meal.getMenu().getId().equals(menuId))
            throw new NotFoundException("Meal with id " + id + " and with Menu Id "
                    + menuId + " is not available");
        return meal;
    }

    @Transactional
    public void delete(Integer id, Integer menuId) {
        if (em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("menuId", menuId)
                .executeUpdate() == 0) throw new NotFoundException("Meal with id " + id + " and with Menu Id "
                + menuId + " is not available ");
    }

    @Transactional
    public Meal save(Meal meal, Integer menuId) {
        if (!meal.isNew() && get(meal.getId(), menuId) == null) {
            throw new NotFoundException("Can't create/update " + meal + " for menu id " + menuId);
        }
        meal.setMenu(em.getReference(Menu.class, menuId));
        if (meal.isNew()) {
            return super.create(meal);
        } else {
            return super.update(meal);
        }
    }




}
