app.directive('gameControls', [ 'scoreboard', 'gameboard', function(scoreboard, gameboard) {
	return {
		restrict : 'E',
		scope : {
			updateGameBoardC : '=update',
			refreshGameBoardC : '=refresh',
			getNewRound: '=newround',
			game: '=',
			currentGameBoard: '=currentboard',
			selectedRoundNumber: '=roundnumber',
			codemaster: '='
		},
		link : function(scope, elm, attr) {
			scope.refreshGameBoard = function (gameBoardId, gameId, roundnumber){
				gameboard.refreshGameboard(gameBoardId, gameId, roundnumber)
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
					.then((gameBoard) => {
						scope.gameboard = gameBoard
					})
					.catch((err) => {
						console.log(err)
					})
			}
			scope.gameOrCodeMaster = (function() {
			    console.log(scope.gameroom)
				return (scope.codemaster) ? "codemaster" : "gameroom"
			})()
		},
		templateUrl : '/javascripts/directives/gameControls.html'
	}
} ]);
