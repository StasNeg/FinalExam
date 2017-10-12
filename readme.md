# Voting System API Documentation

### Содержание

1. [Рестораны](#Рестораны)
    * [Получение конкретного ресторана](#Получение-конкретного-ресторана)
    * [Получение всех ресторанов](#Получение-всех-ресторанов)
    * [Создание ресторана](#Создание-ресторана)
    * [Редактирование ресторана](#Редактирование-ресторана)
    * [Удаление ресторана](#Удаление-ресторана)    
2. [Ланчи](#Ланчи)
    * [Получение всех ланчей ресторана на определенную дату](#Получение-всех-ланчей-ресторана-на-определенную-дату)
    * [Создание ланча](#Создание-ланча)
    * [Удаление ланча](#Удаление-ланча)
3. [Голосование](#Голосование)
    * [Получение всех голосов за определенную дату](#Получение-всех-голосов-за-определенную-дату)
    * [Проголосовать](#Проголосовать)
4. [Сортировка](#Сортировка)
5. [Коды ошибок и ответов](#Коды-ошибок-и-ответов)


## Рестораны

### Получение конкретного ресторана
`GET /api/restaurants/{id}`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/restaurants/100002 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: 3a48e40e-bc40-0d80-a5bb-75fb458a2e80'

Пример ответа:
>{"id": 100002,"name": "Континенталь","address": "Декабристов 14"}

### Получение всех ресторанов
`GET /api/restaurants`

Пример запроса:
>curl -X GET \
    http://localhost:8080/rest/restaurants/ \
    -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
    -H 'cache-control: no-cache' \
    -H 'postman-token: 3a48e40e-bc40-0d80-a5bb-75fb458a2e80'

Пример ответа:
>[{"id": 100002,"name": "Континенталь","address": "Декабристов 14"},{"id": 100003,"name": "Астория","address": "Первого восстания 78"}]

### Создание ресторана
*Пользователь должен иметь роль **Admin*** 

`POST /api/restaurants`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
restaurant   | {id: null, name:"Имя ресторана", address:"Address" | id only null, name and address not empty | Да

Пример запроса:
>curl -X POST \
   http://localhost:8080/rest/admin/restaurants/ \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 25a2e68f-8f7f-d6dd-8d6e-977b431265ad' \
   -d '{
         "id": null,
         "name": "Астория",
         "address": "New Ad 78"
     }'

Пример ответа:
>{"id": 100021,"name": "Астория","address": "New Ad 78"}

### Редактирование ресторана
*Пользователь должен иметь роль **Admin*** 

`PUT /api/restaurants/{id}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
restaurant   | {id: "id", name:"Имя ресторана", address:"Address" | id equals id restaurant, name and address not empty | Да

Пример запроса:
>curl -X PUT \
   http://localhost:8080/rest/admin/restaurants/100021 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: c10c888b-7554-27e3-215c-b2ad20150432' \
   -d '{
     "id": 100021,
     "name": "Астория",
     "address": "New address 178"
 }'

Пример ответа:
>{"id": 100021,"name": "Астория","address": "New Ad 78"}

### Удаление ресторана
*Пользователь должен иметь роль **Admin*** 

`DELETE /api/restaurants/{id}`

Пример запроса:
>curl --user admin:password -X DELETE http://localhost:8080/api/restaurants/100019




---------------------------------------

## Ланчи
### Получение всех ланчей ресторана на определенную дату
`GET /api/lunches`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
restaurantId   | ID ресторана | Целое число | Да 
date   | Дата | Строка формата "yyyy-MM-dd" | Да
sort   | Сортировка возвращаемых данных по полям | [См. Сортировка](#Сортировка) | Нет


Пример запроса:
>curl --user user:password -X GET -G http://localhost:8080/api/lunches -d "restaurantId=100003" -d "date=2017-08-08" -d "sort=-name"

Пример ответа:
>[{"id":100007,"name":"Маргарита","price":30000,"lunchDate":"2017-08-08","restaurantId":100003},{"id":100006,"name":"Карбонара","price":35000,"lunchDate":"2017-08-08","restaurantId":100003}]

### Создание ланча
*Пользователь должен иметь роль **Admin*** 

`POST /api/lunches`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
name   | Название | Непустая строка | Да 
price   | Цена | Неотрицательное целое число | Да
lunchDate   | Дата | Строка формата "yyyy-MM-dd" | Да
restaurantId   | ID ресторана | Целое число | Да


Пример запроса:
>curl --user admin:password -X POST -G http://localhost:8080/api/lunches -d "name=new" -d "price=10000" -d "lunchDate=2017-08-08" -d "restaurantId=100003"

Пример ответа:
>{"id":100019,"name":"new","price":10000,"lunchDate":"2017-08-08","restaurantId":100003}

### Удаление ланча
*Пользователь должен иметь роль **Admin*** 

`DELETE /api/lunches/{id}`

Пример запроса:
>curl --user admin:password -X DELETE http://localhost:8080/api/lunches/100019

---------------------------------------

## Голосование
### Получение всех голосов за определенную дату

`GET /api/votes`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
date   | Дата | Строка формата "yyyy-MM-dd" | Да
sort   | Сортировка возвращаемых данных по полям | [См. Сортировка](#Сортировка) | Нет


Пример запроса:
>curl --user user:password -X GET -G http://localhost:8080/api/votes -d "date=2017-08-08" -d "sort=%2BvoteTime"

Пример ответа:
>[{"id":100015,"voteTime":"10:00:00","pollDate":"2017-08-08","userId":100000,"restaurantId":100003},{"id":100016,"voteTime":"11:00:01","pollDate":"2017-08-08","userId":100002,"restaurantId":100003}]

### Проголосовать

`POST /api/votes`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
restaurantId   | ID ресторана | Целое число | Да


Пример запроса:
>curl --user user:password -X POST -G http://localhost:8080/api/votes -d "restaurantId=100003"

Пример ответа:
>{"id":100020,"voteTime":"10:33:37","pollDate":"2017-09-20","userId":100000,"restaurantId":100003}

---------------------------------------

## Сортировка

Необязательный параметр **sort**. Значение параметра может иметь следующий вид:

`"+pollDate,-id,+voteTime"`

На примере выше сначала идет сортировка по возрастанию на основе **pollDate**, потом по убыванию по **id**, затем по возрастанию по **voteTime**. 

В случае с одним аргументом значение параметра будет иметь вид:

`"+pollDate"`

Если значение парамента не соответствует этому формату, вернется ошибка с кодом **400 (Bad request)**.

---------------------------------------

## Коды ошибок и ответов

Код | Текст | Описание
---------|----------|----------
200 | OK | Запрос выполнился успешно
201 | Created | Ресурс создан
204 | No content | Ресурс успешно удален или обновлен
400 | Bad request | Неверный формат запроса
404 | Not found | Ресурс не найден
409 | Conflict | Ошибка, возникающая при попытке повторно проголосовать после 11:00

