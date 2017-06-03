app.controller('LobbyController', ['$scope', 'games', 'gameboard', function($scope, games, gameboard) {

	$scope.currentGame;
		
	function getGames(){
		games.getGames()
			.success((data) => {
				$scope.listOfGames = data.content;
			})
	}
	
	getGames();
	
	$scope.getGameBoardById = function(id) {
		gameboard.getGameBoardById(id)
			.success((gameboard) => {
				$scope.currentGameBoard = gameboard;
			})
	}
	
	$scope.newGame = function () {
		games.createGame()
			.success((data)=>{
				getGames();
			})
	}

}]);
