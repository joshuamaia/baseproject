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

### Method get all - GET

<p>URL: localhost:8080/api/persons/0/1</p>

Return:

```JSON
[
    {
        "id": 2,
        "name": "Marian",
        "email": "marian@gmail.com",
        "birthDate": "1997-09-30",
        "address": {
            "id": 1,
            "street": "Street Teste",
            "district": "District Teste",
            "number": 123
        }
    }
]
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
