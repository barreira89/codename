app.controller('MainController', ['$scope', 'gameboard', 'scoreboard', 'currentGame', function($scope, gameboard, scoreboard, currentGame) {

  $scope.team;
    
  $scope.currentGame = currentGame;
  console.log(currentGame);
  
  $scope.blueRemaining = scoreboard.blue;
  $scope.redRemaining = scoreboard.red;

  gameboard.getNewGameBoard()
    .success((gameBoard)=>{
        $scope.gameBoard = gameBoard;
        scoreboard.leadTeam.value = gameBoard.leadTeam.toUpperCase();
        $scope.team = scoreboard.leadTeam;
        scoreboard.resetScore(gameBoard.leadTeam.toUpperCase());
    })

  $scope.getNewBoard = () => {
    gameboard.getNewGameBoard()
      .success((gameBoard)=>{
          $scope.gameBoard = gameBoard;
          scoreboard.leadTeam.value = gameBoard.leadTeam.toUpperCase();
          $scope.team = scoreboard.leadTeam;
          scoreboard.resetScore(gameBoard.leadTeam.toUpperCase());
      })
  }


}]);
