# Odds

Used for retrieving all information about all available odds.

* GET /api/odds
#
**URL** ``` /api/odds ```

**METHOD** ``` GET ```

**Description** Gets all odds of future matches to bet on.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
[
    {
        "id": 5,
        "type": "Basic",
        "odd1": 2.1,
        "odd2": 2.2,
        "odd3": 3.0,
        "odd4": 2.5,
        "odd5": 1.05,
        "odd6": 90.0,
        "match": {
            "id": 5,
            "matchdate": "2020-05-24T17:00:00Z",
            "home": "Mike Tyson",
            "away": "Muhammand Ali",
            "types": {
                "id": 5,
                "name": "Boxing",
                "type1": "1",
                "type2": "2",
                "type3": "Nockout",
                "type4": "Full Match",
                "type5": "Blood",
                "type6": "No Blood"
            }
        }
    },
    {
        "id": 4,
        "type": "Basic",
        "odd1": 2.2,
        "odd2": 2.0,
        "odd3": 1.9,
        "odd4": 1.7,
        "odd5": 2.4,
        "odd6": 100.0,
        "match": {
            "id": 4,
            "matchdate": "2020-03-23T17:00:00Z",
            "home": "Mladost",
            "away": "Jug",
            "types": {
                "id": 4,
                "name": "Waterpolo",
                "type1": "1",
                "type2": "X",
                "type3": "2",
                "type4": ">15e",
                "type5": "<15e",
                "type6": "Canceled"
            }
        }
    }
]
```