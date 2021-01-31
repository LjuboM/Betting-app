# Transaction

Used for retrieving information about transactions.

* GET /api/transactions
* GET /api/transactions/{transactionType}
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
        "transactiontype": 1,
        "money": 40,
        "taxes": 10,
        "manipulativespends": 2,
        "user": {
            "id": 1,
            "name": "John Doe",
            "location": "Croatia",
            "age": 24,
            "money": 700
        },
        "ticket": {
            "id": 1,
            "totalodd": 7.776,
            "possiblegain": 311.4,
            "status": 0
        }
    },
    {
        "id": 0,
        "transactiondate": "2020-02-18T17:00:00Z",
        "transactiontype": 2,
        "money": 77,
        "taxes": 14,
        "manipulativespends": 4,
        "user": {
            "id": 1,
            "name": "John Doe",
            "location": "Croatia",
            "age": 24,
            "money": 200
        },
        "ticket": {
            "id": 2,
            "totalodd": 7.25,
            "possiblegain": 558.25,
            "status": 0
        }
    }
]
```
#
**URL** ``` /api/transactions/{transactionType} ```

**METHOD** ``` GET ```

**Description** Gets all transactions sorted by Date, from newer to older of type transactionType.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
[
    {
        "id": 1,
        "transactiondate": "2020-02-19T17:00:00Z",
        "transactiontype": 1,
        "money": 40,
        "taxes": 10,
        "manipulativespends": 2,
        "user": {
            "id": 1,
            "name": "John Doe",
            "location": "Croatia",
            "age": 24,
            "money": 700
        },
        "ticket": {
            "id": 1,
            "totalodd": 7.776,
            "possiblegain": 311.4,
            "status": 0
        }
    },
    {
        "id": 0,
        "transactiondate": "2020-02-18T17:00:00Z",
        "transactiontype": 1,
        "money": 77,
        "taxes": 14,
        "manipulativespends": 4,
        "user": {
            "id": 1,
            "name": "John Doe",
            "location": "Croatia",
            "age": 24,
            "money": 200
        },
        "ticket": {
            "id": 2,
            "totalodd": 7.25,
            "possiblegain": 558.25,
            "status": 0
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
    "ticket": null
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
    "taxes": 555,
    "manipulativespends": 50,
    "user": {
        "id": 1,
        "name": "John Doe",
        "location": "Croatia",
        "age": 24,
        "money": 6055
    },
    "ticket": null
}
```

## Error Response

|Condition|Code|Description|
|:---:|:---:|:---:|
|Not adding enough money|400 BAD REQUEST|You have to add at least 1 HRK to your account.|
|JSON schema is not valid|400 BAD REQUEST||