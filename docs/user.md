# User

Used for retrieving information about user.
Only one user in application, with ID of 1...

* GET /api/user/{id}
#
**URL** ``` /api/user/{id} ```

**METHOD** ``` GET ```

**Description** Gets the user with selected ID.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
{
    "id": 1,
    "name": "John Doe",
    "location": "Corona Quarantine",
    "age": 21,
    "money": 2020
}
```
## Error Response

|Condition|Code|Description|
|:---:|:---:|:---:|
|User with that ID doesn't exist|404 NOT FOUND||