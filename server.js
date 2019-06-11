var express = require('express');
var mongoose = require('mongoose')
var bodyParser = require("body-parser")
var tools = require('./calculate')
var squads = require('./squads')
var rankings = require('./rankings')
var app = express();
var dbUrl = 'mongodb://localhost:27017/goonsdb'
var User = require('./models/user')
var Squad = require('./models/squad')
var Game = require('./models/game')

mongoose.connect(dbUrl, { useNewUrlParser: true })
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/', (req, res) => {
    console.log("homepage")
    res.send("Hello Goons and Goonettes");
})

app.get('/users',(req, res) => {
    console.log("fetching all users")
    User.find().exec().then(users => {
        if(users){
            res.json(users)
        }else{
            res.json({
                message:"cant get users"
            })
        }
    })
})

app.get('/squads', (req, res) => {
    console.log("fetching all squads")
    console.log(req.body)
    Squad.find().exec().then(squads => {
        if(squads){
            res.json({
                type:'squads',
                res: 200,
                squads: squads
            })
        }else{
            res.json({
                type: 'squads',
                res: 403
            })
        }
    }).catch(err => {
        console.log(err)
        res.json({
            type: 'squads',
            res: 403
        })
    })
})

app.post('/active', (req, res) => {
    console.log("setting active status")
    console.log(req.body)
    User.findOneAndUpdate({userid: req.body.userid}, {$set: {queued: req.body.status}}).exec().then(user => {
        if(user) {
            res.json({
                type: "active",
                res: 200
            })
        }else{
            res.json({
                type: "active",
                res: 403
            })
        }
    }).catch(err => {
        console.log(err)
        res.json({
                type: "active",
                res: 403
            })
    })

})

app.post('/joinsquad', (req, res) => {
    console.log("joinsquad")
    console.log(req.body)
    var fullUrl = req.protocol + '://' + req.get('host') + req.originalUrl
    console.log(fullUrl)
    User.findOneAndUpdate({userid: req.body.userid}, {$set: {squad: req.body.squad}}).exec().then(user => {
        if(user){
            Squad.findOneAndUpdate({name: req.body.squad},{$push: {members: user._id}}, {$inc: {count: 1}}).exec().then(squad => {
                if(squad){
                    return res.json({
                        "type": "joinsquad",
                        "res": 200
                    })
                }
            })
            res.json({
                "type": "joinsquad",
                "res": 400
            })
        }else{
            res.json({
                "type": "joinsquad",
                "res": 403
            })
        }
    }).catch(err => {
        console.log(err)
        res.json({
            "type": "joinsquad",
            "res": 403
        })
    })
})


app.post('/location', (req, res) => {
    console.log("location")
    console.log(req.body)
    console.log(req.body.location)
    var fullUrl = req.protocol + '://' + req.get('host') + req.originalUrl
    console.log(fullUrl)
    User.findOneAndUpdate({userid: req.body.userid}, { $set: { location: req.body.location}}).exec().then(user => {
        if(user){
            res.json({
                    "type": "location",
                    "res": 200
            })
        }else {
            res.json({
                "type": "location",
                "res": 403
            })
        }
    }).catch(err => {
        console.log(err)
        res.json({
            "type": "location",
            "res": 403
        })
    })

})

app.get('/profile', (req, res) => {
    console.log(req.body)
    console.log("profile")
    User.findOne({userid: req.body.userid}).exec().then(user => {
        if(user){
            res.json({
                "type": "profile",
                "res": 200,
                "user": user
            })
        }else{
            res.json({
                "type": "profile",
                "res": 403
            })
        }
    }).catch(err => {
        console.log(err)
        res.json({
            "type": "profile",
            "res": 403
        })
    })
})

app.get('/map', (req, res) => {
    console.log(req.body)
    if(!req.body.userid){
        return res.json({
            "type": "map",
            "res": 403,
            "message": "userid is empty"
        })
    }
    console.log("map")
    User.find().exec().then(users => {
        var user = users.find({userid: req.body.userid})
        console.log(user)
        var inRangeUsers = []
        result.forEach(element => {
            console.log("result ")
            console.log(result1)
            console.log("elem")
            console.log(element)
            if (result1.userid != element.userid) {
                if (tools.withinRange(result1.location, element.location)) {
                    inRangeUsers.push(element)
                    console.log("users in range")
                }
            }
        })
        res.json({
            "type": "map",
            "users": inRangeUsers
        })
    }).catch(err => {
        console.log(err)
    })
})

app.get('/squad', (req, res) => {
    console.log("squad")
    console.log(req.body)
    Squad.findOne({name: req.body.squad}).exec().then(squad => {
        if(squad){
            res.json({
                "type": "squad",
                "res": 200,
                "squad": squad
            })
        }else{
            res.json({
                "type": "squad",
                "res": 403
            })
        }
    }).catch(err => {
        console.log(err)
    })
})

app.post('/signup', (req, res) => {
    console.log(req.body)
    console.log("signup user:" + req.body.userid)
    if(!req.body.userid){
        return res.json({
            "message": "userid empty",
            "type": "signup",
            "res": 403
        })
    }
    
    User.findOne({userid: req.body.userid}).exec().then(user => {
        if(user){
            console.log("user with userid exist")
        }else{
            var user = User({
                _id: new mongoose.Types.ObjectId(),
                userid: req.body.userid,
                password: req.body.password,
                imagenum: req.body.imagenum
            })
            user.save().then(result => {
                console.log(result)
                res.json({
                    type: 'signup',
                    res: 200
                })
            }).catch(err => {
                console.log(err)
                console.log('could not save user')
            })
        }
    }).catch(err => {
        console.log(err)
        console.log('could not search db')
    })
})

app.post('/propose', (req, res) => {
    console.log(req.body)
    console.log("propose id:" + req.body.proposeid)

    User.findOne({userid: req.body.proposeid}).exec().then(user => {
        if(user){
            console.log("id taken")
            res.json({
                type: "propose",
                verify: false,
                res: 403
            })
        }else{
            console.log("id available")
            res.json({
                type: "propose",
                verify: true,
                res: 403
            })
        }
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

})

app.post('/login', (req, res) => {
    console.log(req.body)
    console.log("user id:" + req.body.userid)
    User.findOne({userid: req.body.userid}).exec().then(user => {
        if(user){
            if(user.password == req.body.password){
                console.log("match")
                res.json({
                    type: "login",
                    res: 200,
                    verify: true
                })
            }else{
                console.log("no match")
                res.json({
                    type: "login",
                    res: 403,
                    verify: false,
                    message: "wrong password"
                })
            }
            
        }else{
            res.json({
                type: "login",
                res: 403,
                verify: false,
                message: "user not found"
            })

        }
    })
})


var server = app.listen(3000, () => {
    var host = server.address().address
    var port = server.address().port
    console.log("server", host, port)
})

