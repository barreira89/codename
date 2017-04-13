app.factory('games', ['$http', function($http) {
 var gameServices = {};

gameServices.getGames = function (){
	 return $http({
		 method: 'GET',
		 url: '/api/games'
	 })
 }

gameServices.getGameById = function (gameId){
	return $http({
		method: 'GET',
		url: '/api/games/' + gameId
	})
}
gameServices.getWeekList = function (){
	return $http({
		method: 'GET',
		url: '/api/games/weeklists'
	})

}

gameServices.getGamesByWeek = function (weekNumber, season) {
  var seasonQuery = addSeasonQuery(season);
	return $http({
			method: 'GET',
			url: '/api/games' + '?weeknumber=' + parseInt(weekNumber) + seasonQuery
	})
}

gameServices.getGamesByTeam = function (team){
	return $http({
		method: 'GET',
		url: '/api/games' + '?team=' + team
	})
}

gameServices.updateGamesByWeek = function (week, gameData) {
	return $http({
		method : 'PUT',
		url : '/api/games' + '?weeknumber=' + parseInt(week),
		data : gameData
	})
}
gameServices.updateGame = function (gameId, data){
	return $http({
		method: 'PUT',
		url: '/api/game/' + gameId,
		data: data
	})
}

 return gameServices;

function addSeasonQuery(season){
  var seasonQuery = '';
  if(season){
    seasonQuery = '&season=' + season;
  }
  return seasonQuery;
}

}]);
