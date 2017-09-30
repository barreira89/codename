app.controller('GameRoomController', ['$scope', 'gameboard', 'scoreboard', 'currentGame', 'currentRound', 'socket', function($scope, gameboard, scoreboard, currentGame, currentRound, socket) {
   
  var mRoundNumber;

  /*
   * Local Functions
   * 	Calculate Score
   * 	Refresh Game Board
   *    Select Round Number
   */
  
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
  
  function selectRoundNumber(roundNumber){
	  mRoundNumber = roundNumber;
	  $scope.selectedRoundNumber = roundNumber;
	  if($scope.game && $scope.game.rounds) {
		 var currentRound =  $scope.game.rounds.find((round) => {
			  return round.roundNumber == roundNumber
		  })
		  $scope.gameBoard = currentRound.gameBoard;
		  refreshGameBoard()
		  
		  //Handle Clues
		  $scope.clueListServer = gameboard.mapClues(currentRound.gameClues);
		  calculateScore()
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
  function initScope(){
      $scope.game = currentGame.data;
      $scope.blueRemaining = scoreboard.blue;
      $scope.redRemaining = scoreboard.red;
      $scope.clueList = socket.clueList;
      $scope.selectedRoundNumber = currentRound;
  }

  initScope()

  $scope.gameTileCallBack = function(){
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
  
  $scope.selectRoundNumber = selectRoundNumber
  

  
  $scope.getNewRound = function() {
	  gameboard.getNewRound($scope.game.id)
	  	.then((game)=>{
	  		$scope.game = game;
	  	})
	  	.catch((err)=>{
	  		console.log(err);
	  	})
  }
  
  var receiveClue = function (message) {
	  console.log(message);
	  $scope.$apply();
  }

  var receiveUpdate = function () {
	  refreshGameBoard();
  }

    if(currentRound){
  	  selectRoundNumber(currentRound)
    } else {
  	  selectRoundNumber(1)
    }
  
  socket.start($scope.game.id, receiveClue, receiveUpdate);
  
}]);
