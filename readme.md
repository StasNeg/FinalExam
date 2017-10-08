Реализация выпускного проекта Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

База данных:
Postgre : database.url=jdbc:postgresql://localhost:5432/final

Реализация:
Таблицы: users, restaurants, meals, votes.

Автоматическое заполнение: 
initDB - create DB
populateDB - set start values

Реализация REST API:

Все REST REQUEST действуют для авторизированных пользователей. Для Admin Role доступны все запросы User Role

1. USERS
Для USER:
URL: /rest/profile
GET - return Authorized user, DELETE - delete Authorized user, PUT - edit Authorized user

Для ADMIN:
POST and PUT do with Class UserTo.
URL: /rest/admin/user

GET(/rest/admin/user) - GetAllUsers
GET(/rest/admin/user/{id}) - GetUserById
GET(/rest/admin/user/by?email=) - Get User By Email
POST(/rest/admin/user) - Add new User
PUT(/rest/admin/user/{id}) - Edit User by ID
DELETE(GET(/rest/admin/user/{id}) - Delete user by Id

2. RESTAURANTS

Для USER:
URL: /rest/restaurants
GET(/rest/admin/restaurants) - GetAllRestaurants
GET(/rest/admin/restaurants{id}) - GetRestaurantsById

Для ADMIN:
POST and PUT do with Class RestaurantTO.

URL: /rest/admin/restaurants
POST(/rest/admin/restaurants) - Add new Restaurant
PUT(/rest/admin/restaurants/{id}) - Edit Restaurant by ID
DELETE(/rest/admin/restaurants/{id}) - Delete Restaurant by Id

3. MEALS
Для USER:
GET(/rest/meals/{restaurantId}/{id} Get Meal For Restaurant ID By Id
GET(/rest/meals/{restaurantId} Get All Meal For Restaurant ID


Для ADMIN:
POST and PUT do with Class Meals.
URL: /rest/admin/meals
POST(/rest/admin/meals{restaurantId}/) - Add new Meals to Restaurant {restaurantId}
PUT(/rest/admin/meals/{restaurantId}/{id}) - Edit Meal For Restaurant {restaurantId} by Id
DELETE(/rest/admin/meals/{restaurantId}/{id}) - Delete Meals for Restaurant {restaurantId} by Id

4. VOTES
POST and PUT do with RequestParam Integer Restaurant Id - Its id restaurant which Authorised User votes.
for All Authorized users :
URL: /rest/vote
POST(/rest/vote) - Add new Vote 
PUT(/rest/vote) - Edit Vote by Id

Ограничения:
1. Нельзя создать Юзера с одинаковой электронной почтой.
2. Нельзя создать два ресторана в один день с одинаковым адресом
3. Нельзя создать еду с одинаковым рестораном названием и ценой
4. Голосование: 
    Один день только одно голосование.
    Нельзя голосовать в разные дни за те же рестораны.
    Переголосовать можно только до времени установленной в глобальной переменной: TIME_VOTE_LIMIT(установлена на 11:00).