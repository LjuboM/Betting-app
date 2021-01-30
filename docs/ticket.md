# Ticket

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
        "possiblegain": 311.4
        "status": 0
        }
    },
    {
        "id": 2,
        "totalodd": 7.25,
        "possiblegain": 558.25
        "status": 0
        }
    },
    {
        "id": 3,
        "totalodd": 476.0,
        "possiblegain": 5712.0
        "status": 1
        }
    }
]
```
