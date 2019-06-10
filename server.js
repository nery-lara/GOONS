var express = require('express');
var mongo = require('mongodb');
var bodyParser = require("body-parser")
var tools = require('./calculate')
var squads = require('./squads')
var rankings = require('./rankings')
var user = {
    userid: 'angel',
    password: 'castro',
    squad: 'north',
    queued: 'yes',
    rank : 'goon',
    location: { lat: -119.853748, long: 34.410404},
    wins: 0,
    losses: 0
}

var user1 = {
    userid: 'alejandro',
    password: 'vasquez',
    squad: 'west',
    queued: 'yes',
    rank: 'goon',
    location: { lat: -119.853735, long: 34.409773 },
    wins: 0,
    losses: 0
}

var user2 = {
    userid: 'nery',
    password: 'lara',
    squad: 'east',
    queued: 'yes',
    rank: 'goon',
    location: { lat: -119.855532, long: 34.411214 },
    wins: 0,
    losses: 0
}

var mongoClient = mongo.MongoClient
var url = "mongodb://localhost:27017/goonsdb";
mongoClient.connect(url, (err, db) => {
    if(err) throw err
    var db0 = db.db("goonsdb")
    db0.collection("goons").drop( (err, res) => {
        if (err) console.log(err)
        if (res) console.log("Collection goons deleted");
    });
    db0.collection("squads").drop((err, res) => {
        if (err) console.log(err)
        if (res) console.log("Collection squads deleted");
    });
    db0.collection('squads').insertOne(squads.north)
    db0.collection('squads').insertOne(squads.east)
    db0.collection('squads').insertOne(squads.south)
    db0.collection('squads').insertOne(squads.west)
    db0.collection('goons').insertOne(user)
    db0.collection('goons').insertOne(user1)
    db0.collection('goons').insertOne(user2)
    db0.collection('squads').insertOne(squads.north)
    console.log("database created")
    var cursor = db0.collection('goons').find()
    cursor.each((err,doc)=> {
        console.log(doc)
    })
    var cursor = db0.collection('squads').find()
    cursor.each((err, doc) => {
        console.log(doc)
    })
})
var app = express();
app.use(bodyParser.json())

app.get('/', (req, res) => {
    console.log("homepage")
    res.send("Hello Goons and Goonettes");
})

app.get('/users',(req, res) => {
    console.log("fetching all users")
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        var cursor = db0.collection('goons').find({}).toArray((err, result) => {
            if (err) throw err;
            res.send(result)
        })
        db.close();
    })
})

app.get('/squads', (req, res) => {
    console.log("fetching all squads")
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        var cursor = db0.collection('squads').find({}).toArray((err, result) => {
            if (err) throw err;
            res.send(result)
        })
        db.close();
    })
})

app.post('/active', (req, res) => {
    console.log("setting active status")
    var userid = req.body.userid
    var status = req.body.status
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        var cursor = db0.collection('goons').UpdateOne({"userid": userid}, {$set: {"queued": status}},(err, result) => {
            if (err) throw err;
            var data = {
                type:"active",
                res: 200
            }
            res.send(data)
        })
        db.close();
    })
})

app.post('/joinsquad', (req, res) => {
    console.log("joinsquad")
    console.log(req.body)
    console.log(req.body.userid)
    console.log(req.body.squad)
    var userid = req.body.userid
    var squad = req.body.squad
    var fullUrl = req.protocol + '://' + req.get('host') + req.originalUrl
    console.log(fullUrl)
    mongoClient.connect(url, (err, db) => {
        if (err) {
            var data = {
                "type": "joinsquad",
                "res": 403
            }
        }
        else{
            var data = {
                "type": "joinsquad",
                "res": 200
            }
            var db0 = db.db("goonsdb");
            db0.collection('goons').updateOne({ "userid": userid }, { $set: { "squad": squad } },(err, result) => {
                console.log("updated user squad")
                console.log(result)
                if(result.modifiedCount) {
                    console.log("pushing member to squad")
                    db0.collection('squads').updateOne({ "name": squad }, { $push: { "members": userid } },(err, result) => {
                        db.close();
                    })
                }
            })
            
            
        }
        res.send(data)
    })
})


app.post('/location', (req, res) => {
    console.log("location")
    console.log(req)
    console.log(req.body)
    console.log(req.body.userid)
    console.log(req.body.location)
    var userid = req.body.userid
    var location = req.body.location
    var fullUrl = req.protocol + '://' + req.get('host') + req.originalUrl
    console.log(fullUrl)
    mongoClient.connect(url, (err, db) => {
        if (err) {
            var data = {
                "type": "location",
                "res": 403
            }
        }
        else {
            var data = {
                "type": "location",
                "res": 200
            }
            var db0 = db.db("goonsdb");
            db0.collection('goons').updateOne({ "userid": userid }, { $set: { "location": location } })
            db.close();
        }
        res.send(data)
    })
})

app.get('/profile', (req, res) => {
    console.log("profile")
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        db0.collection('goons').findOne({ "userid" : req.body.userid},(err, result) => {
            if (result === null) {
                data = {
                    "type": "profile",
                    "res": 403
                }
            }else {
                data = {
                    "type": "profile",
                    "res": 200,
                    "user": result
                }
            }
            res.send(data)
            db.close();
        })
    })
})

app.get('/map', (req, res) => {
    console.log("map")
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        db0.collection('goons').findOne({userid: req.body.userid}, (err, result1) => {
            db0.collection('goons').find({}).toArray((err, result) => {
                var inRangeUsers = []
                if (err) throw err;
                result.forEach(element => {
                    console.log("result ")
                    console.log(result1)
                    console.log("elem")
                    console.log(element)
                    if(result1.userid != element.userid){
                        if (tools.withinRange(result1.location, element.location)) {
                            inRangeUsers.push(element)
                            console.log("users in range")
                        }
                    }
                });
                var data = {
                    "type": "map",
                    "users": inRangeUsers
                }
                res.send(data)
            })
            
            db.close();

        })
     
    })
    var users = [{
        "userid" : "angelcastro",
        "long"   : 134.24,
        "lat"    : 222.21,
        "squad"  : "north",
        "rank"   : "goon"
     }, {
            "userid": "nerylara",
            "long": 234.24,
            "lat": 222.21,
            "squad": "east",
            "rank": "goon"
     }, {
            "userid": "vasqueezy",
            "long": 234.24,
            "lat": 122.21,
            "squad": "west",
            "rank": "goon"
     } ]
})

app.get('/squad', (req, res) => {
    console.log("squad")
    console.log(req.body)
    mongoClient.connect(url, (err, db) => {
        var data = {}
        if (err) throw err
        var db0 = db.db("goonsdb");
        db0.collection('squads').findOne({ "name": req.body.squad }, (err, result) => {
            if (result) {
                data = {
                    "type": "squad",
                    "res": 200,
                    "squad": result
                }
            } else {
                data = {
                    "type": "squad",
                    "res": 403
                }
            }
            res.send(data)
            db.close();
        })
    })
})

app.post('/signup', (req, res) => {
    console.log("signup user:" + req.body.userid)
    var user = {
        userid: req.body.userid,
        password: req.body.password,
        squad: "",
        queued: 'no',
        rank: 'goon',
        location: { lat: 0, long: 0 },
        wins: 0,
        losses: 0
    }
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        console.log("looking for user:" + req.body.userid)
        db0.collection('goons').findOne({ userid: req.body.userid })
        .then(result => {
            if(result) {
                console.log("user already exist")
                var data = {
                    "type": "signup",
                    "res": 403
                }
                res.send(data)
            }else {
                console.log("user does not already exist")
                db0.collection('goons').insertOne(user)
                console.log(user)
                var data = {
                    "type": "signup",
                    "res": 200
                }
                res.send(data)
                db.close();
            }
        }).catch(reason => {
            console.log(reason)
        })
    })
})

app.post('/propose', (req, res) => {
    console.log(req.body)
    console.log("propose id:" + req.body.proposeid)
    mongoClient.connect(url, (err, db) => {
        if (err) throw err
        var db0 = db.db("goonsdb");
        var user = db0.collection('goons').findOne({ userid: req.body.proposeid },(err, result) =>{
            var data = { "type": "propose" }
            if (result) {
                console.log(result.proposeid)
                console.log(result)
                data.res = 403
            } else {
                data.res = 200
            }
            res.send(data)

            db.close();
        })
    })
})

app.post('/game', (req, res) => {
    console.log("game")
    console.log(req.body)
    console.log(req.body.userid)
    console.log(req.body.squad)
    var userid = req.body.userid
    var squad = req.body.squad
    var gameResult = req.body.result
    var fullUrl = req.protocol + '://' + req.get('host') + req.originalUrl
    console.log(fullUrl)
    mongoClient.connect(url, (err, db) => {
        var data = {
            "type": "game",
            "res": 200
        }
        var db0 = db.db("goonsdb");
        if (gameResult == "win") {
            db0.collection('goons').updateOne({ "userid": userid }, { $inc: { "wins": 1 } }, (err, result) => {
                console.log("updated user result")
                console.log(result)
                if (result.modifiedCount) {
                    console.log("pushing result to squad")
                    db0.collection('squads').updateOne({ "name": squad }, { $inc: { "wins": 1 } }, (err, result) => {
                        db0.collection('goons').findOne({ "userid": userid },(err, result) => {
                            console.log(result.wins)
                            db0.collection('goons').updateOne({ "userid": result.userid }, { $set: { "rank": rankings.rank(result.wins, result.losses) } }, (err, result) => {
                                db.close();
                            })

                        })
                    })
                }
            })
        }
        else {
            db0.collection('goons').updateOne({ "userid": userid }, { $inc: { "losses": 1 } }, (err, result) => {
                console.log("updated user result")
                console.log(result)
                if (result.modifiedCount) {
                    console.log("pushing result to squad")
                    db0.collection('squads').updateOne({ "name": squad }, { $inc: { "losses": 1 } }, (err, result) => {
                        db0.collection('goons').findOne({ "userid": userid }, (err, result) => {
                            db0.collection('goons').updateOne({ "userid": result.userid }, { $set: { "rank": rankings.rank(result.wins, result.losses) } }, (err, result) => {
                                db.close();
                            })

                        })
                    })
                }
            })
        }
        res.send(data)
    })
})

var server = app.listen(3000, () => {
    var host = server.address().address
    var port = server.address().port
    console.log("server", host, port)
})

