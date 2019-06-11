const mongoose = require('mongoose')

const squadSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    name: { type: String, required: true, unique: true },
    rep: {type: Number, default: 0},
    wins: { type: Number, default: 0 },
    losses: { type: Number, default: 0 },
    count: {type: Number, default: 0},
    members:[{type: mongoose.Schema.Types.ObjectId, ref:'User'}]
})

module.exports = mongoose.model('Squad', squadSchema)