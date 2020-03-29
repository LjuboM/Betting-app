# Types

Used for retrieving information about all odd types.

* GET /api/types
#
**URL** ``` /api/types ```

**METHOD** ``` GET ```

**Description** Gets all types.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
[
    {
        "id": 1,
        "name": "Football",
        "type1": "1",
        "type2": "X",
        "type3": "2",
        "type4": "1X",
        "type5": "X2",
        "type6": "12"
    },
    {
        "id": 2,
        "name": "Basketball",
        "type1": "1X",
        "type2": "X2",
        "type3": ">=200p",
        "type4": "<200p",
        "type5": "1H10",
        "type6": "2H10"
    }
]
```