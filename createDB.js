var mongoClient = mongo.MongoClient
var squads = require('./squads')
var url = "mongodb://localhost:27017/goonsdb";
mongoClient.connect(url, (err, db) => {
    if (err) throw err
    var db0 = db.db("goonsdb");
    db0.createCollection("goons", (err, res) => {
        if (err) throw err
        console.log("collection goons created")
    })
    db0.collection('goons').insertOne(user)
    db0.collection('goons').insertOne(user1)
    console.log("database created")

    db0.createCollection("squads", (err, res) => {
        if (err) throw err
        console.log("collection squads created")
    })

    db0.collection('squads').insertOne(squads.north)
    db0.collection('squads').insertOne(squads.east)
    db0.collection('squads').insertOne(squads.south)
    db0.collection('squads').insertOne(squads.west)

    var cursor = db0.collection('goons').find()
    cursor.each((err, doc) => {
        console.log(doc)
    })
})