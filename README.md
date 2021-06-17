# KspSpringBoot

## Manage KSP items API.
Springboot project with Postgress DB including items table.

Item example:
```json
{
    "id": 3,
    "name": "Samsung LED TV",
    "description": "Samsung LED 4K Smart TV",
    "price": 2200,
    "inventory": 10,
    "createdOn": "2021-06-16T08:43:29.384+00:00",
    "lastUpdated": "2021-06-16T08:43:29.384+00:00"
}
```

### Supported actions
______
| Method | Endoint | Note |
|---|---|---|
| GET | http://localhost:8080/api/items| | Get all items
| GET | http://localhost:8080/api/item/{itemid} | | Get an item by ID
| POST | http://localhost:8080/api/create | consumes = `application/json` (item) | Create a new item
| DELETE | http://localhost:8080/api/delete/{itemid} | | Delete an item by ID
| PUT | http://localhost:8080/api/update/{itemid} | consumes = `application/json` (item) | Update an item
| GET | http://localhost:8080/api/buy/{itemid}/{amount} | | Buy an item by ID

