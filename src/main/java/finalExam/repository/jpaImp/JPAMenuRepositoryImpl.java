package finalExam.repository.jpaImp;

import finalExam.model.menu.Menu;
import finalExam.repository.AbstractDaoImpl;
import finalExam.repository.MenuRepository;
import finalExam.repository.RestaurantRepository;
import finalExam.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JPAMenuRepositoryImpl extends AbstractDaoImpl<Menu> implements MenuRepository {

    public JPAMenuRepositoryImpl() {
        super(Menu.class);
    }

    @Autowired
    RestaurantRepository restaurantRepository;

    @Cacheable("menu")
    @Override
    public Menu get(Integer id, Integer restaurantId) {
        Menu menu = super.get(id);
        if (menu == null || !menu.getRestaurant().getId().equals(restaurantId))
            throw new NotFoundException("Menu with id " + id + " and with Restaurant Id "
                    + restaurantId + " is not available");
        return menu;
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    @Override
    public void delete(Integer id, Integer restaurantId) {
        if (em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() == 0) throw new NotFoundException("Menu with id " + id + " and with Restaurant Id "
                + restaurantId + " is not available ");
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    @Override
    public Menu save(Menu menu, Integer restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            throw new NotFoundException("Can't create/update " + menu + " for menu id " + restaurantId);
        }
        menu.setRestaurant(restaurantRepository.get(restaurantId));
        if (menu.isNew()) {
            return super.create(menu);
        } else {
            return super.update(menu);
        }
    }

    @Cacheable("menu")
    @Override
    public List<Menu> getAll(Integer restaurantId) {
        return em.createNamedQuery(Menu.ALL, Menu.class)
                .setParameter(1, restaurantId)
                .getResultList();
    }

    @Cacheable("menu")
    @Override
    public List<Menu> getByNameWithMeals(String name) {
        return em.createNamedQuery(Menu.ALL_BY_ADDRESS, Menu.class)
                .setParameter(1, name)
                .getResultList();
    }

    @Cacheable("menu")
    @Override
    public List<Menu> getByNameBetweenDates(String name, LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Menu.GET_BY_ADDRESS_BETWEEN_DATES, Menu.class)
                .setParameter(1, name)
                .setParameter(2, startDate)
                .setParameter(3, endDate).getResultList();
    }

    @Cacheable("menu")
    @Override
    public List<Menu> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Menu.GET_BETWEEN_DATES, Menu.class)
                .setParameter(1, startDate)
                .setParameter(2, endDate).getResultList();
    }


    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void evictCache() {
        // only for evict cache
    }


}
