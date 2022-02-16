# Base Project Spring 3.0

# Exaples of the acess - API localhost

### Method create - POST

<p>Content-Type = application/json</p>
<p>URL: localhost:8080/api/persons</p>

Body: 

```JSON
{
    "name" : "Jackson",
    "email" : "jackson@gmail.com",
    "birthDate" : "2000-11-26"
}
```

Return:

```JSON
{
    "id": 1,
    "name": "Jackson",
    "email": "jackson@gmail.com",
    "birthDate": "2000-11-26"
}
```

### Method update - PUT

<p>Content-Type = application/json</p>
<p>URL: localhost:8080/api/persons</p>

Body: 

```JSON
{
        "id": 1,
        "name": "Jackson",
        "email": "jackson@gmail.com",
        "birthDate": "2000-11-26",
    "address" : {
        "street" : "Street Test 2",
        "district" : "District Test 2",
        "number" : 123
    }
}
```

Return:

```JSON
{
    "id": 1,
    "name": "Jackson",
    "email": "jackson@gmail.com",
    "birthDate": "2000-11-26",
    "address": {
        "id": 2,
        "street": "Street Test 2",
        "district": "District Test 2",
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
            "id": 2,
            "name": "Marian Lemans",
            "email": "marian@gmail.com",
            "birthDate": "1997-09-23",
            "address": {
                "id": 1,
                "street": "Street Teste",
                "district": "District Teste",
                "number": 123
            }
        },
        {
            "id": 8,
            "name": "Martina",
            "email": "martina@gmail.com",
            "birthDate": "2022-02-16",
            "address": {
                "id": 8,
                "street": "Street Teste",
                "district": "District Teste yyyy",
                "number": 34
            }
        },
        {
            "id": 10,
            "name": "Robbin Williama",
            "email": "robbin@gmail.com",
            "birthDate": "2022-02-07",
            "address": {
                "id": 10,
                "street": "Street Test 4",
                "district": "District Teste yyyy",
                "number": 56
            }
        },
        {
            "id": 9,
            "name": "Denis Rodman",
            "email": "denis@gmail.com",
            "birthDate": "2022-02-02",
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
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 4,
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

<p>URL: http://localhost:8080/api/persons/0/10/ma</p>

Return:

```JSON
{
    "content": [
        {
            "id": 9,
            "name": "Denis Rodman",
            "email": "denis@gmail.com",
            "birthDate": "2022-02-02",
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
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
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

<p>URL: localhost:8080/api/persons/1</p>

Return:

```JSON
{
    "id": 1,
    "name": "Jackson",
    "email": "jackson@gmail.com",
    "birthDate": "2000-11-26",
    "address": {
        "id": 2,
        "street": "Street Test 2",
        "district": "District Test 2",
        "number": 123
    }
}
```

### Method delete - DELETE

<p>URL: localhost:8080/api/persons/1</p>

Return: No content
