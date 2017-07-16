app.directive('gameControls', [ 'scoreboard', 'gameboard', function(scoreboard, gameboard) {
	return {
		restrict : 'E',
		scope : {
			updateGameBoardC : '=update',
			refreshGameBoardC : '=refresh',
			getNewRound: '=newround',
			game: '=',
			gameboard: '=',
			selectedRoundNumber: '=roundnumber',
			codemaster: '='
		},
		link : function(scope, elm, attr) {
			console.log(scope)
			scope.refreshGameBoard = function (gameBoardId, gameId, roundNumber){
				gameboard.refreshGameboard(gameBoardId, gameId, roundNumber)
					.then((gameBoardResponse) => {
						scope.gameboard = gameBoardResponse.gameBoard
						scope.clueListServer = gameBoardResponse.clueList
					})
					.catch((err) =>{
						console.log(err)
					})
			}
			scope.updateGameBoard = function (gameBoardId, gameBoard){
				gameboard.updateGameBoard(gameBoardId, gameBoard)
					.success((gameBoard) => {
						scope.gameboard = gameBoard
					})
					.error((err) => {
						console.log(err)
					})
			}
			$scope.gameOrCodeMaster = (function() {
				return (codemaster) ? "codemaster" : "gameroom"
			})()
		},
		templateUrl : '/javascripts/directives/gameControls.html'
	}
} ]);
