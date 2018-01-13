app.directive('gameControls', [ 'scoreboard', 'gameboard', function(scoreboard, gameboard) {
	return {
		restrict : 'E',
		scope : {
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
			scope.getNewRound = function(gameId){
			    gameboard.getNewRound(gameId)
			        .then((updatedGame)=>{
			            scope.game = updatedGame;
			        })
			        .catch((err)=>{
			            console.error(err)
			        })
			}
		},
		templateUrl : '/javascripts/directives/gameControls.html'
	}
} ]);
