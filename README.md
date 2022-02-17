# Base Project Spring 3.0

# Exaples of the acess - API localhost

### Method create - POST

<p>Content-Type = application/json</p>
<p>URL: localhost:8080/api/persons</p>

Body: 

```JSON
{
    "name": "Marian Lemans",
    "email": "marian@gmail.com",
    "birthDate": "1997-09-23",
    "gender": {
        "description": "Male"
    },
    "address": {
        "street": "Street Teste",
        "district": "District Teste",
        "number": 123
    }
}
```

Return:

```JSON
{
    "id": 2,
    "name": "Marian Lemans",
    "email": "marian@gmail.com",
    "birthDate": "1997-09-23",
    "gender": {
        "description": "Male"
    },
    "address": {
        "id": 2,
        "street": "Street Teste",
        "district": "District Teste",
        "number": 123
    }
}
```

### Method update - PUT

<p>Content-Type = application/json</p>
<p>URL: localhost:8080/api/persons</p>

Body: 

```JSON
{
    "id": 2,
    "name": "Marian Lemans",
    "email": "marian@gmail.com",
    "birthDate": "1997-09-23",
    "gender": {
        "description": "Male"
    },
    "address": {
        "id": 2,
        "street": "Street Teste",
        "district": "District Teste",
        "number": 123
    }
}
```

Return:

```JSON
{
    "id": 2,
    "name": "Marian Lemans",
    "email": "marian@gmail.com",
    "birthDate": "1997-09-23",
    "gender": {
        "description": "Male"
    },
    "address": {
        "id": 2,
        "street": "Street Teste",
        "district": "District Teste",
        "number": 123
    }
}
```

### Method get all pageable - GET

<p>URL: http://localhost:8080/api/persons/0/10</p>

Return:

```JSON
{
    "content": [
        {
            "id": 8,
            "name": "Martina",
            "email": "martina@gmail.com",
            "birthDate": "2022-02-16",
            "gender": {
                "description": "Female"
            },
            "address": {
                "id": 8,
                "street": "Street Teste",
                "district": "District Teste yyyy",
                "number": 34
            }
        },
        {
            "id": 9,
            "name": "Denis Rodman",
            "email": "denis@gmail.com",
            "birthDate": "2022-02-02",
            "gender": {
                "description": "Male"
            },
            "address": {
                "id": 9,
                "street": "Street Test 4",
                "district": "District Teste",
                "number": 67
            }
        },
        {
            "id": 10,
            "name": "Robbin William",
            "email": "robbin@gmail.com",
            "birthDate": "2022-02-07",
            "gender": {
                "description": "Male"
            },
            "address": {
                "id": 10,
                "street": "Street Test 4",
                "district": "District Teste yyyy",
                "number": 56
            }
        },
        {
            "id": 2,
            "name": "Marian Lemans",
            "email": "marian@gmail.com",
            "birthDate": "1997-09-23",
            "gender": {
                "description": "Male"
            },
            "address": {
                "id": 1,
                "street": "Street Teste",
                "district": "District Teste",
                "number": 123
            }
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 4,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 4,
    "empty": false
}
```

### Method get all pageable with word search - GET

<p>URL: http://localhost:8080/api/persons/0/10/de</p>

Return:

```JSON
{
    "content": [
        {
            "id": 9,
            "name": "Denis Rodman",
            "email": "denis@gmail.com",
            "birthDate": "2022-02-02",
            "gender": {
                "description": "Male"
            },
            "address": {
                "id": 9,
                "street": "Street Test 4",
                "district": "District Teste",
                "number": 67
            }
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```

### Method get one - GET

<p>URL: localhost:8080/api/persons/2</p>

Return:

```JSON
{
    "id": 2,
    "name": "Marian Lemans",
    "email": "marian@gmail.com",
    "birthDate": "1997-09-23",
    "gender": {
        "description": "Female"
    },
    "address": {
        "id": 1,
        "street": "Street Teste",
        "district": "District Teste",
        "number": 123
    }
}
```

### Method delete - DELETE

<p>URL: localhost:8080/api/persons/1</p>

Return: No content
