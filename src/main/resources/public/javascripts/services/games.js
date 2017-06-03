app.factory('games', [ '$http', function($http) {
	var gameServices = {};

	gameServices.getGames = function() {
		return $http({
			method : 'GET',
			url : '/api/games'
		})
	}

	gameServices.getGameById = function(gameId) {
		return $http({
			method : 'GET',
			url : '/api/games/' + gameId
		})
	}
	
	gameServices.createGame = function () {
		return $http({
			method: 'POST',
			url: '/api/games'
		})
	}
	
	gameServices.createNewRoundForGame = function (id) {
		var gameId = id		
		return $http({
			method: 'POST',
			url: '/api/games/' + gameId + '/rounds'
		})
	}
	
	return gameServices;


} ]);
