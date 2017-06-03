app.controller('GameRoomController', ['$scope', 'gameboard', 'scoreboard', 'currentGame', 'socket', function($scope, gameboard, scoreboard, currentGame, socket) {
   
  var mRoundNumber;
  
  function calculateScore(){
	  if($scope.gameBoard) scoreboard.calculateScores($scope.gameBoard)
  }
  
  function refreshGameBoard (){
	  if($scope.gameBoard) {
		  gameboard.getGameBoardById($scope.gameBoard.id)
		  	.success((data)=>{
		  		$scope.gameBoard = data;
		  		gameboard.getGameCluesByGameAndRound($scope.game.id, mRoundNumber)
		  			.success((data) => {
		  				$scope.clueListServer = gameboard.mapClues(data);
		  			})
		  			.error((err)=>{
		  				console.log(err);
		  			})
		  		calculateScore()
		  	})
		  	.error((err) => {
		  		console.log(err)
		  	})
	  }
  }
  
  function updateGameBoard() {
	  if($scope.gameBoard) {
		  gameboard.updateGameBoard($scope.gameBoard.id, $scope.gameBoard)
		  	.success((data)=>{
		  		$scope.gameBoard = data;
		  		calculateScore()
		  	})
		  	.error((err) => {
		  		console.log(err)
		  	})
	  } 
  }
  
  // Scope Set Up
  
  $scope.game = currentGame.data;
  $scope.blueRemaining = scoreboard.blue;
  $scope.redRemaining = scoreboard.red;
  $scope.clueList = socket.clueList;
  $scope.updateGameBoard = updateGameBoard;
  $scope.refreshGameBoard = refreshGameBoard;
  
  $scope.callback = function(){
	  updateGameBoard();
	  socket.sendUpdate(mRoundNumber);
  }
  
  // Code Master View
  $scope.sendClue = function(){
	  var clue = $scope.clue.word || 'default'
	  var numberOfWords = $scope.clue.numberOfWords
	  var team = $scope.selectedTeam || 'RED'
	  var postClue = {
		  clue: clue,
		  numberOfWords: numberOfWords,
		  team: team
	  }
	  gameboard.addGameClueByGameAndRound($scope.game.id, mRoundNumber, postClue);
	  //socket.sendClue(clue, numberOfWords, team, mRoundNumber);
  }
  
  $scope.selectRoundNumber = function (roundNumber) {
	  mRoundNumber = roundNumber;
	  if($scope.game && $scope.game.rounds) {
		 var currentRound =  $scope.game.rounds.find((round) => {
			  return round.roundNumber == roundNumber
		  })
		  $scope.gameBoard = currentRound.gameBoard;
		  refreshGameBoard()
		  
		  //Handle Clues Clues
		  $scope.clueListServer = gameboard.mapClues(currentRound.gameClues);
		  console.log($scope.clueListServer)
		  var redClue = gameboard.mapClues(currentRound.gameClues);
		  console.log(redClue);
		  calculateScore()
	  }
  }
  
  $scope.getNewRound = function() {
	  gameboard.getNewRound($scope.game.id)
	  	.then((game)=>{
	  		$scope.game = game;
	  	})
	  	.catch((err)=>{
	  		console.log(err);
	  	})
  }
  
  var recieveClue = function (message) {
	  console.log(message);
	  $scope.$apply();
  }

  var receiveUpdate = function () {
	  refreshGameBoard();
  }
  
  socket.start($scope.game.id, recieveClue, receiveUpdate);
  
}]);
