Number.prototype.toRadians = function () { return this * Math.PI / 180; };
Number.prototype.toDegrees = function () { return this * 180 / Math.PI; };


function calcDist(lat1, lon1, lat2, lon2) {
    var R = 6371e3; // metres
    var φ1 = lat1.toRadians();
    var φ2 = lat2.toRadians();
    var Δφ = (lat2 - lat1).toRadians();
    var Δλ = (lon2 - lon1).toRadians();

    var a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2) +
        Math.cos(φ1) * Math.cos(φ2) *
        Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c;
}
module.exports = {

    withinRange: function(location1, location2) {
        dist = calcDist(location1.lat, location1.long,
            location2.lat, location2.long)
            console.log("distance :" + dist)
        if(dist < 500){
            return true
        }
        else{
            return false
        }
    }
}