# Transaction

Used for retrieving information about tickets.

* GET /api/tickets
#
**URL** ``` /api/tickets ```

**METHOD** ``` GET ```

**Description** Gets all tickets.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
[
    {
        "id": 1,
        "totalodd": 7.776,
        "possiblegain": 311.4,
        "transaction": {
            "id": 2,
            "transactiondate": "2020-02-16T17:00:00Z",
            "transactiontype": true,
            "money": 40,
            "user": {
			    "id": 1,
			    "name": "John Doe",
			    "location": "Corona Quarantine",
			    "age": 21,
			    "money": 2020
            }
        }
    },
    {
        "id": 2,
        "totalodd": 7.25,
        "possiblegain": 558.25,
        "transaction": {
            "id": 5,
            "transactiondate": "2020-02-19T17:00:00Z",
            "transactiontype": true,
            "money": 77,
            "user": {
			    "id": 1,
			    "name": "John Doe",
			    "location": "Corona Quarantine",
			    "age": 21,
			    "money": 2020
            }
        }
    },
    {
        "id": 3,
        "totalodd": 476.0,
        "possiblegain": 5712.0,
        "transaction": {
            "id": 4,
            "transactiondate": "2020-02-18T17:00:00Z",
            "transactiontype": true,
            "money": 12,
            "user": {
			    "id": 1,
			    "name": "John Doe",
			    "location": "Corona Quarantine",
			    "age": 21,
			    "money": 2020
            }
        }
    }
]
```