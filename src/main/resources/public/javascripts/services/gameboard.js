app.factory('gameboard', ['$http', '$q', function($http, $q) {
    var gameservices = {};

    gameservices.getNewGameBoard = function() {
        return $http({
            method: 'GET',
            url: '/api/gameboards'
        })
    }
    return gameservices;
}]);
