const mongoose = require('mongoose')

const userSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    userid: { type: String, required: true, unique: true },
    password: { type: String, required: true },
    loggedin: { type: Boolean, default: false },
    imagenum: Number,
    squad: String,
    queued: {type: Boolean, default: false},
    rank: String,
    location: {
        lat: {type:Number, default: 0},
        long: {type:Number, default: 0}
    },
    wins: {type: Number, default: 0},
    losses: {type: Number, default: 0}
})

module.exports = mongoose.model('User', userSchema)
