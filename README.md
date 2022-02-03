# Phone Exercise

### Description:
This project allows user resource to be create/update/retrieve/delete.

In-memory database (h2) has been used for implementing this project.

This project also allows Phone resource to be added into the User resource along with delete/retrieve phone resource.

All API's are available over REST Interface.

### Requirements
1.	JDK 1.8 or later
2.	Maven


### How to Install
1.	Download the project and unzip on any machine.
2.	cd phone-service
3.	mvn clean install


### How to Run
1. mvn spring-boot:run

### How to Test
1. mvn clean test

**Note** Any Rest Client can be used to use the Rest API

###Usage
Rest API would be launch on deafult port **http://localhost:8080**

h2 database can be access through **http://localhost:8080/h2-console/**

### Add User

#### Endpoint

```Java
POST: /api/v1/users
```
#### Request Body

```JSON
{
  "userName": "TestUserName",
  "password": "TestPassword",
  "emailAddress": "test@gmail.com",
  "preferredPhoneNumber": "+353123456789"
}
```

### Delete User

#### Endpoint

```Java
DELETE: /api/v1/users/{userId}
```

### List Users

#### Endpoint

```Java
GET: /api/v1/users
```

### Add Phone To User

#### Endpoint

```Java
POST: /api/v1/users/{userId}/phones
```
#### Request Body

```JSON
{
  "phoneName": "TestUserName phone",
  "phoneModel": "IPHONE",
  "phoneNumber": "+353892471846"
}
```

### Delete Phone

#### Endpoint

```Java
DELETE: /api/v1/phones/{phoneId}
```


### List User Phone

#### Endpoint

```Java
GET: /api/v1/users/{userId}/phones
```

### Update User Preferred Phone Number

#### Endpoint

```Java
PUT: /api/v1/users/{userId}
```

#### Request Body

```JSON
{
 "preferredPhoneNumber": "+353987654321
}
```




