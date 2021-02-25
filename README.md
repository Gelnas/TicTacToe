Приложение для игры крестики-нолики

1. Для того чтобы запустить придложение необходимо создать базу данных в СУБД Postgres под названием db (Порт стандартный 5432). 
Так же нужно в application.yml заменить username и password на свои.
2. Необходимо в IDEA подключить плагин для lombok
3. К программе добавлен Swagger. Данные для подключения: http://localhost:8080/api/v1/doc/swagger-ui.html
4. В БД добавится User с email: user@gmail.com and password : 12345 с ролью ADMIN. 

Интеграционные тесты работают только  по отдельности, не все вместе. В ближайшее время буду разбиратьсяс этой проблемой.
Не сделана игра с ботом, тоже будет реализовано в ближайшее время. Сам бот находится в БД в таблице player  под  id = 0. Это так же временная мера

Endpoints:
1. Получение игрока по id (GET): http://localhost:8080/api/v1/player/{id}
2. Добавление нового игрока (POST): http://localhost:8080/api/v1/player/registration
3. Обновление данных игрока (PUT): http://localhost:8080/api/v1/player/{id}
4. Удаление игрока(DELETE): http://localhost:8080/api/v1/player/{id}
5. Получение страницы с игроками (GET): http://localhost:8080/api/v1/player
6. Получение 10 лучших игроков (GET): http://localhost:8080/api/v1/player/sort
7. Получение своей статистики (GET): http://localhost:8080/api/v1/player/statistics
8. Получение игры по id (GET): http://localhost:8080/api/v1/game/{id}
9. Обновление данных игры (ходы) (PUT): http://localhost:8080/api/v1/game/{id}
10. Добавление нового игрока (POST): http://localhost:8080/api/v1/game/create
