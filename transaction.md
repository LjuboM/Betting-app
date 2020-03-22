# Transaction

Used for retrieving information about transactions.

* GET /api/transactions
* POST /api/transaction
#
**URL** ``` /api/transactions ```

**METHOD** ``` GET ```

**Description** Gets all transactions sorted by Date, from newer to older.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
[
    {
        "id": 1,
        "transactiondate": "2020-02-19T17:00:00Z",
        "transactiontype": false,
        "money": 77,
        "user": {
            "id": 1,
            "name": "John Doe",
            "location": "Croatia",
            "age": 24,
            "money": 700
        }
    },
    {
        "id": 0,
        "transactiondate": "2020-02-18T17:00:00Z",
        "transactiontype": true,
        "money": 12,
        "user": {
            "id": 1,
            "name": "John Doe",
            "location": "Croatia",
            "age": 24,
            "money": 200
        }
    }
]
```
#
**URL** ``` /api/transaction ```

**METHOD** ``` POST ```

**Description** Creates a transaction of type false (Adding money to account).

**Data example** 

```
    {
        "type": false,
        "money": 5555,
        "user": {
            "id": 1
        }
    }
```
## Success Response

**CODE** ``` 200 OK ```

**Content example**

```
{
    "id": 6,
    "transactiondate": "2020-03-18T19:45:49.583Z",
    "transactiontype": false,
    "money": 5555.0,
    "user": {
        "id": 1,
        "name": "John Doe",
        "location": "Croatia",
        "age": 24,
        "money": 6055
    }
}
```

## Error Response

|Condition|Code|Description|
|:---:|:---:|:---:|
|Not adding enough money|400 BAD REQUEST|You have to add at least 1 HRK to your account.|
|JSON schema is not valid|400 BAD REQUEST||