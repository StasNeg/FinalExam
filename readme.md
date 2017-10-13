# Voting System API Documentation

### Содержание
1. [Пользователи](#Пользователи)
    * [Получение конкретного пользователя](#Получение-конкретного-пользователя)
    * [Получение текущего пользователя](#Получение-текущего-пользователя)
    * [Получение всех пользоавтелей](#Получение-всех-пользователей)
    * [Создание нового пользователя](#Создание-пользователя)
    * [Редактирование пользователей](#Редактирование-пользователей)
    * [Редактирование текущего пользователя](#Редактирование-текущего-пользователя)
    * [Удаление пользователя](#Удаление-пользователя)
2. [Рестораны](#Рестораны)
    * [Получение конкретного ресторана](#Получение-конкретного-ресторана)
    * [Получение всех ресторанов](#Получение-всех-ресторанов)
    * [Создание ресторана](#Создание-ресторана)
    * [Редактирование ресторана](#Редактирование-ресторана)
    * [Удаление ресторана](#Удаление-ресторана)    
3. [Ланчи](#Ланчи)
    * [Получение всех ланчей ресторана](#Получение-всех-ланчей-ресторана)
    * [Получение всех ланчей ресторана за период](#Получение-всех-ланчей-ресторана-за-период)
    * [Получение всех ланчей ресторана по адресу ресторана](#Получение-всех-ланчей-ресторана-по-адресу-ресторана)
    * [Получение всех ланчей ресторана по адресу за период](#Получение-всех-ланчей-ресторана-по-адресу-за-период)
    * [Создание ланча](#Создание-ланча)
    * [Редактирование ланча](#Редактирование-ланча)
    * [Удаление ланча](#Удаление-ланча)
4. [Блюдо](#Блюдо)
    * [Получение всех блюд меню](#Получение-всех-блюд-меню)
    * [Получение конкретного блюдо](#Получение-конкретного-блюдо)
    * [Создание блюдо](#Создание-блюдо)
    * [Редактирование блюдо](#Редактирование-блюдо)
    * [Удаление блюдо](#Удаление-блюдо)
5. [Голосование](#Голосование)    
    * [Проголосовать](#Проголосовать)
6. [Ограничения](#Ограничения)


## Пользователи
### Получение конкретного пользователя
*Пользователь должен иметь роль **Admin***
`GET /rest/admin/user/{id}`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/admin/user/100000 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: 9df329f6-a15e-8c03-aa77-7488c39777f3'

Пример ответа:
>{"id": 100000,"name": "User","email": "user@yandex.ru","roles": ["ROLE_USER"],"password": "password","registered": "2017-10-12"}

### Получение текущего пользователя
`GET /rest/profile`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/profile \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: 96ca97bd-6596-8d14-947c-a6b55ae72c25'
   
Пример ответа:
>{"id": 100001,"name": "Admin","email": "admin@gmail.com","roles": ["ROLE_ADMIN"],"password": "password","registered": "2017-10-12"}

### Получение всех пользователей
*Пользователь должен иметь роль **Admin***
`GET /rest/admin/user/`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/admin/user \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: 9555dd5c-3b6d-3a01-7dba-f6804c31c5e8'
   
Пример ответа:
>[{"id": 100000,"name": "User","email": "user@yandex.ru","roles": ["ROLE_USER"],"password": "password","registered": "2017-10-12"},
     {"id": 100001,"name": "Admin","email": "admin@gmail.com","roles": ["ROLE_ADMIN"],"password": "password","registered": "2017-10-12"}]

### Создание нового пользователя
*Пользователь должен иметь роль **Admin*** 

`POST /rest/admin/user/`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
user   | {id: null, name:"userName", email:"email", roles:["roles"], password:"password" | id only null, name, email, roles and password not empty | YES

Пример запроса:
>curl -X POST \
   http://localhost:8080/rest/admin/user/ \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 7cce7517-5a5c-05c7-7717-7d594429224c' \
   -d '{
         "id":null,
         "name": "User",
         "email": "user123@yandex.ru",
         "roles": [
             "ROLE_USER"
         ],
         "password": "password"
     }'
     
Пример ответа:
>{"id": 100021,"name": "User","email": "user123@yandex.ru","roles": ["ROLE_USER"],"password": "password","registered": "2017-10-12"}}

### Редактирование пользователей
*Пользователь должен иметь роль **Admin*** 

`PUT /rest/admin/user/{id}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
user   | {id: {id}, name:"userName", email:"email", roles:["roles"], password:"password" | id equals {id}, name, email, roles and password not empty | YES

Пример запроса:
>curl -X PUT \
   http://localhost:8080/rest/admin/user/100021 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 78db8020-1c45-5057-a091-9f2208b31b3f' \
   -d '{
         "id":100021,
         "name": "Edit User",
         "email": "editUser@yandex.ru",
         "roles": [
             "ROLE_USER"
         ],
         "password": "password"
     }'
     
Пример ответа:
>{"id": 100021,"name": "Edit User","email": "editUser@yandex.ru","roles": ["ROLE_USER"],"password": "password","registered": "2017-10-12"}

### Редактирование пользователей
`PUT /rest/user/`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
user   | {id: currentId or null, name:"userName", email:"email", password:"password" | id equals {id or null}, name, email and password not empty | YES

Пример запроса:
>curl -X PUT \
   http://localhost:8080/rest/profile \
   -H 'authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 45fe8c0b-6c43-97fe-6e50-4d4e62bdbedd' \
   -d '{
         "id": 100000,
         "name": "Edit User",
         "email": "user@yandex.ru",
         "password": "password"
     }'
         
Пример ответа:
>{"id": 100000,"name": "Edit User","email": "user@yandex.ru","roles": ["ROLE_USER"],"password": "password","registered": "2017-10-12"}

### Удаление пользователя
*Пользователь должен иметь роль **Admin*** 

`DELETE /rest/admin/user/{id}`

Пример запроса:
>curl -X DELETE \
    http://localhost:8080/rest/admin/user/100021 \
    -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: c4068905-340f-18da-f153-ae48b7900484' \

---------------------------------------

## Рестораны
### Получение конкретного ресторана
`GET /rest/restaurants/{id}`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/restaurants/100002 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: 3a48e40e-bc40-0d80-a5bb-75fb458a2e80'

Пример ответа:
>{"id": 100002,"name": "Континенталь","address": "Декабристов 14"}

### Получение всех ресторанов
`GET /rest/restaurants/`

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

`POST /rest/admin/restaurants/`

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

`PUT /rest/admin/restaurants/{id}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
restaurant   | {id: "id", name:"Имя ресторана", address:"Address" | id equals {id}, name and address not empty | Да

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
>{"id": 100021,"name": "Астория","address": "New address 178"}

### Удаление ресторана
*Пользователь должен иметь роль **Admin*** 

`DELETE /rest/admin/restaurants/{id}`

Пример запроса:
>curl -X DELETE \
   http://localhost:8080/rest/admin/restaurants/100021 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: d2932253-41f9-f06c-ddf8-078cf9a3b538'

---------------------------------------

## Ланчи
### Получение всех ланчей ресторана
`GET localhost:8080/rest/restaurant/{restaurantId}`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/restaurant/100002 \
   -H 'authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 3d528585-40d3-ee49-b456-c6db603495c4'

Пример ответа:
>[{"id": 100004,"name": "Континенталь","address": "Декабристов 14","date": "2015-05-30","priceTotal": 157},{"id": 100006,"name": "Континенталь","address": "Декабристов 14","date": "2015-05-31","priceTotal": 95}]


### Получение всех ланчей ресторана за период
`GET localhost:8080/rest/restaurant//AllBetween`

Пример запроса:
>curl -X GET \
   'http://localhost:8080/rest/restaurant/AllBetween?dateStart=2015-05-30&dateEnd=2015-05-31' \
   -H 'authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: ee03d978-2e19-5bd0-9e94-c0c28f5efe75'


Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
dateStart   | Date to start | format: yyyy-MM-dd | Да 
dateEnd   | Date to end | format: yyyy-MM-dd | Да

Пример ответа:
>[{"id":100006,"name":"Континенталь","address":"Декабристов 14","date":"2015-05-31","priceTotal":95.0},{"id":100007,"name":"Астория","address":"Первого восстания 78","date":"2015-05-31","priceTotal":95.0},{"id":100004,"name":"Континенталь","address":"Декабристов 14","date":"2015-05-30","priceTotal":157.0},{"id":100005,"name":"Астория","address":"Первого восстания 78","date":"2015-05-30","priceTotal":173.0}]

### Получение всех ланчей ресторана по адресу ресторана
`GET localhost:8080/rest/restaurant/ByAddress`

Пример запроса:
>curl -X GET \
   'http://localhost:8080/rest/restaurant/ByAddress?address=%D0%B2%D0%BE%D1%81' \
   -H 'authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: 2393dd01-14f6-a7bf-1cde-e98507ffa873'

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
address   | part of field.address | not empty | Да

Пример ответа:
>[{"id":100005,"name":"Астория","address":"Первого восстания 78","date":"2015-05-30","priceTotal":173.0},{"id":100007,"name":"Астория","address":"Первого восстания 78","date":"2015-05-31","priceTotal":95.0}]

### Получение всех ланчей ресторана по адресу за период
`GET localhost:8080/rest/restaurant/ByAddressBetween`

Пример запроса:
>curl -X GET \
   'http://localhost:8080/rest/restaurant/ByAddressBetween?address=%D0%B2%D0%BE%D1%81&dateStart=2015-05-30&dateEnd=2015-05-30' \
   -H 'authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
   -H 'cache-control: no-cache' \
   -H 'postman-token: b5b194c7-d8c0-53ba-9109-db1d9c74271f'

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
address   | part of field.address | not empty | Да
dateStart   | Date to start | format: yyyy-MM-dd | Да 
dateEnd   | Date to end | format: yyyy-MM-dd | Да

Пример ответа:
>[{"id":100005,"name":"Астория","address":"Первого восстания 78","date":"2015-05-30","priceTotal":173.0}]

### Создание ланча
*Пользователь должен иметь роль **Admin*** 

`POST /rest/admin/{restaurantId}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
menu   | {id: null, "date":"yyyy-MM-dd" | id only null, date format:"yyyy-MM-dd"  not empty | Да
restaurantId   | ID restaurant | integer number | Да


Пример запроса:
>curl -X POST \
   http://localhost:8080/rest/admin/100002 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: cc9995d9-f388-ae31-e7d1-fdd2e30f15da' \
   -d '{
         "id": null,
         "date": "2017-10-13"
        
     }'

Пример ответа:
>{"id":100021,"name":"Континенталь","address":"Декабристов 14","date":"2017-10-13","priceTotal":0.0}

### Редактирование ланча
*Пользователь должен иметь роль **Admin*** 
`PUT /rest/admin/{restaurantId}/menu/{id}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
menu   | {id: null, "date":"yyyy-MM-dd" | id equal {id}, date format:"yyyy-MM-dd"  not empty | Да
restaurantId   | ID restaurant | integer number | Да
id   | ID menu | integer number | Да

Пример запроса:
>curl -X PUT \
   http://localhost:8080/rest/admin/100002/menu/100021 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 5172c947-5704-fdb9-9512-a132a1fe3948' \
   -d '{
         "id": 100021,
         "date": "2017-12-13"
        
     }'

Пример ответа:
>{"id":100021,"name":"Континенталь","address":"Декабристов 14","date":"2017-12-13","priceTotal":0.0}

### Удаление ланча
*Пользователь должен иметь роль **Admin*** 

`DELETE /rest/admin/{restaurantId}/menu/{id}`

Пример запроса:
>curl -X DELETE \
   http://localhost:8080/rest/admin/100002/menu/100021 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 1df413c1-73e9-80ee-ccd4-2d8d53abf7d0'

---------------------------------------

## Блюдо
### Получение всех блюд меню
`GET localhost:8080/rest/menu/{menuId}`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/menu/100004 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 69e75faa-32ae-f860-49af-1fea48403fed'
   
Пример ответа:
>[{"id":100008,"name":"Сок","price":33.0},{"id":100009,"name":"Суп Харчо","price":62.0},{"id":100010,"name":"Пельмени","price":62.0}]

### Получение конкретного блюдо
`GET localhost:8080/rest/menu/{menuId}/meals/{id}`

Пример запроса:
>curl -X GET \
   http://localhost:8080/rest/menu/100004/meals/100008 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 05da6131-f5e3-7ebd-ece4-477ad970f7c5'
     
Пример ответа:
>{"id":100008,"name":"Сок","price":33.0}

###Создание блюдо
*Пользователь должен иметь роль **Admin*** 

`POST /rest/admin/menu/{menuId}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
meal   | {id: null, "name":"name", "price":Double | id only null, name not empty| Да
menuId   | ID menu | integer number | Да


Пример запроса:
>curl -X POST \
   http://localhost:8080/rest/admin/menu/100004 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 425fe035-0478-9464-5443-d0521b84165e' \
   -d '{"id":null,"name":"Apple Juice","price":44.0}'

Пример ответа:
>{"id":100021,"name":"Континенталь","address":"Декабристов 14","date":"2017-10-13","priceTotal":0.0}

###Редактирование блюдо
*Пользователь должен иметь роль **Admin*** 

`POST /rest/admin/menu/{menuId}/meals/{id}`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
meal   | {id: {id}, "name":"name", "price":Double | id equals {id}, name not empty| Да
menuId   | ID menu | integer number | Да
id   | ID meals | integer number | Да

Пример запроса:
>curl -X PUT \
   http://localhost:8080/rest/admin/menu/100005/meals/100023 \
   -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 1f342599-57e8-d30c-2909-4bfd1ac862c7' \
   -d '{"id":100023,"name":"Apple Juice 12","price":44.0}'

Пример ответа:
>{"id":100023,"name":"Apple Juice 12","price":44.0}

###Удаление блюдо
*Пользователь должен иметь роль **Admin*** 

`DELETE /rest/admin/menu/{menuId}/meals/{id}`

>curl -X DELETE \
  http://localhost:8080/rest/admin/menu/100005/meals/100023 \
  -H 'authorization: Basic YWRtaW5AZ21haWwuY29tOnBhc3N3b3Jk' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 83efc8dc-3729-a28c-72ab-14e1f0394dfc'
  


## Голосование
### Проголосовать
`POST rest/vote/`

Параметр | Описание | Ограничения | Обязательный
---------|----------|----------|----------
restaurantId   | ID ресторана | Целое число | Да

Пример запроса:
>curl -X POST \
   'http://localhost:8080/rest/vote/?restaurantId=100003' \
   -H 'authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 6f11065a-dcb0-2b29-2bff-fd8521252157'

Пример ответа:
>{"id":100022,"restaurant":{"id":100003,"name":"Астория","address":"Первого восстания 78"},"user":{"id":100000,"name":"User","email":"user@yandex.ru","roles":["ROLE_USER"],"password":"password","registered":"2017-10-13"},"date":"2017-10-13"}

---------------------------------------
### Ограничения
1. Пользователи.
    - Нельзя создавать пользователей с одинаковым email.
2. Ресторан.
    - Нельзя создавать рестораны с одинаковыми адресами.
3. Ланч.
    - Нельзя создавать ланчи с одинаковым рестораном и датой ланча.
4. Блюдо.
    - Нельзя создавать блюдо с одинаковым наименованием, ценой и ланчем.
5. Голосование.
    - Пользователь может проголосовать один раз в день.
    - Менять свой голос в этот же день можно до 11-00.