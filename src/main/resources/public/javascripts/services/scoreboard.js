app.factory('scoreboard', ['$http', function($http) {
    var scoreboard = {
        red: {
            value: 8
        },
        blue: {
            value: 8
        },
        scoreRed: function() {
             scoreboard.red.value -- ;
        },
        scoreBlue: function() {
            scoreboard.blue.value --;
        },
        resetScore: function(leadTeam) {
            scoreboard.red.value = 8;
            scoreboard.blue.value = 8;
            leadTeam == 'RED' ? scoreboard.red.value++ : scoreboard.blue.value++;
        }
    };
    return scoreboard;
}]);
