# TicketOdds

Used for retrieving all information about all played tickets.

* GET /api/tickets
* POST /api/ticket
#
**URL** ``` /api/tickets ```

**METHOD** ``` GET ```

**Description** Gets all played tickets.

## Success Response

**CODE** ``` 200 OK ```

**Content example**
```
[
    {
        "@id": 1,
        "id": 1,
        "ticket": {
            "@id": 2,
            "id": 1,
            "totalodd": 7.776,
            "possiblegain": 311.4,
            "transaction": {
                "id": 2,
                "transactiondate": "2020-02-16T17:00:00Z",
                "transactiontype": true,
                "money": 40.0,
                "user": {
                    "id": 1,
                    "name": "Ljubo Mamic",
                    "location": "Split",
                    "age": 24,
                    "money": 22720.0
                }
            }
        },
        "odds": {
            "id": 1,
            "type": "Basic",
            "odd1": 1.8,
            "odd2": 2.0,
            "odd3": 2.8,
            "odd4": 1.4,
            "odd5": 1.8,
            "odd6": 1.7,
            "match": {
                "id": 1,
                "matchdate": "2020-02-20T17:00:00Z",
                "home": "FC Barcelona",
                "away": "Real Madrid C.F.",
                "types": {
                    "id": 1,
                    "name": "Football",
                    "type1": "1",
                    "type2": "X",
                    "type3": "2",
                    "type4": "1X",
                    "type5": "X2",
                    "type6": "12"
                }
            }
        },
        "odd": 1.8,
        "type": "1"
    },
    {
        "@id": 3,
        "id": 2,
        "ticket": 2,
        "odds": {
            "id": 2,
            "type": "Basic",
            "odd1": 2.2,
            "odd2": 2.4,
            "odd3": 5.0,
            "odd4": 1.4,
            "odd5": 2.5,
            "odd6": 2.8,
            "match": {
                "id": 2,
                "matchdate": "2020-02-21T17:00:00Z",
                "home": "Cibona",
                "away": "Cedevita",
                "types": {
                    "id": 2,
                    "name": "Basketball",
                    "type1": "1X",
                    "type2": "X2",
                    "type3": ">=200p",
                    "type4": "<200p",
                    "type5": "1H10",
                    "type6": "2H10"
                }
            }
        },
        "odd": 2.4,
        "type": "X2"
    },
    {
        "@id": 4,
        "id": 3,
        "ticket": 2,
        "odds": {
            "id": 3,
            "type": "Basic",
            "odd1": 2.0,
            "odd2": 1.9,
            "odd3": 1.4,
            "odd4": 1.9,
            "odd5": 1.8,
            "odd6": 2.0,
            "match": {
                "id": 3,
                "matchdate": "2020-02-22T17:00:00Z",
                "home": "Rafael Nadal",
                "away": "Roger Federer",
                "types": {
                    "id": 3,
                    "name": "Tennis",
                    "type1": "1",
                    "type2": "2",
                    "type3": ">23.5g",
                    "type4": "<23.5g",
                    "type5": ">=2.5h",
                    "type6": "<2.5h"
                }
            }
        },
        "odd": 1.8,
        "type": ">=2.5h"
    }
]
```
#
**URL** ``` /api/ticket ```

**METHOD** ``` POST ```

**Description** Creates a ticket.

**Data example** 

```
[
    {
        "ticket": {
            "totalodd": 7.776,
            "possiblegain": 311.4,
            "transaction": {
                "money": 10.0,
                    "user": {
                    "id": 1
                }
            }
        },
        "odds": {
            "id": 1,
            "type": "Special offer",
            "match": {
            	"id": 1,
            	"matchdate": "2020-04-20T17:00:00.000Z"
            }
        },
        "odd": 1.0,
        "type": "1"
    },
        {
        "ticket": {
            "totalodd": 7.776,
            "possiblegain": 311.4,
            "transaction": {
                "money": 10.0,
                    "user": {
                    "id": 1
                }
            }
        },
        "odds": {
            "id": 2,
            "type": "Basic",
            "match": {
            	"id": 2,
            	"matchdate": "2020-04-20T17:00:00.000Z"
            }
        },
        "odd": 2.0,
        "type": "X"
    },
        {
        "ticket": {
            "totalodd": 7.776,
            "possiblegain": 311.4,
            "transaction": {
                "money": 10.0,
                    "user": {
                    "id": 1
                }
            }
        },
        "odds": {
            "id": 3,
            "type": "Basic",
            "match": {
            	"id": 3,
            	"matchdate": "2020-04-20T17:00:00.000Z"
            }
        },
        "odd": 3.0,
        "type": "2"
    }
]
```
## Success Response

**CODE** ``` 200 OK ```

**Content example**

```
Successfully placed a bet!
```

## Error Response

|Condition|Code|Description|
|:---:|:---:|:---:|
|JSON schema is not valid|400 BAD REQUEST||
|Not enough money in wallet.|400 BAD REQUEST|You don't have enough money in your wallet.|
|Played more than 1 Special offer|400 BAD REQUEST|You didn't place a valid bet.|
|Played at least one match that already started|400 BAD REQUEST|You didn't place a valid bet.|
|Played same match in Special offer and basic offer|400 BAD REQUEST|You didn't place a valid bet.|
|Played Special offer without at least 5 odds > 1.10|400 BAD REQUEST|You didn't place a valid bet.|