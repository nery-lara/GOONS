var rankings = ['goon', 'leader', 'lieutenant', 'captain', 'colonel', 'general', 'Top Dawg']

module.exports = {

    rank: function (wins, losses) {
        console.log(wins)
        console.log(losses)
        var x = wins - losses
        console.log(x)
        switch (true) {
            case (x < 10):
                return rankings[0]
            case (x < 20):
                return rankings[1]
            case (x < 30):
                return rankings[2]
            case (x < 40):
                return rankings[3]
            case (x < 50):
                return rankings[4]
            case (x < 60):
                return rankings[5]
            default:
                return rankings[6]

        }
    }
}