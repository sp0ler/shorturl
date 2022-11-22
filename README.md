<h1 align="center">Привет, меня зовут Денис</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

### О проекте: ###
REST API сервис коротких ссылок. Позволяет заливать куски текста/кода и получать на них короткую ссылку, которую можно отправлять другим людям.

При загрузке текста/кода можно указать:
1. Срок в течение которого текст/кода будет доступен. Например, 10 мин, 1 час, 3 часа и тд, до неограниченного по времени.
2. Ограничение видимости: public, unlisted.

Также доступна выгрузка последних 10 публчиных коротких ссылок

### Использование: ###
API:
1. GET: "/api/fetch/{short_url}" - Получение JSON по короткой ссылке

Вернется: 
```
{
    "id": "45df593812655",
    "text": "text",
    "timeRegistration": "2022-11-08T14:19:38.128087200Z",
    "timeExpired": "2022-11-08T14:19:38.129087200Z",
    "role": "public",
    "ttl": 1
}
```
2. POST: "/api/save/{time_expired}" - Передать минимальный JSON и время в миллисекундах

Минимальные требования для передачи:
```
{
    "text": "text",
    "role" : "public"
}
```
3. POST: "/api/fetch/last_public" - выведится список последних 10 JSON с ролью public
4. DELETE: "/api/delete/{short_url}" - удалит JSON и вернет HttpStatus.GONE

### Стек технолгиий: ###
> Java 11, Lombok, Redis, Spring
