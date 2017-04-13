app.controller('MainController', ['$scope', 'gameboard', 'scoreboard', function($scope, gameboard, scoreboard) {

  $scope.team = 'RED';
  
  $scope.liveload = "LIVE LOADING BABY"

  $scope.blueRemaining = scoreboard.blue;
  $scope.redRemaining = scoreboard.red;

  gameboard.getNewGameBoard()
    .success((gameBoard)=>{
        $scope.gameBoard = gameBoard;
        $scope.team = gameBoard.leadTeam.toUpperCase();
        scoreboard.resetScore(gameBoard.leadTeam.toUpperCase());
    })

  $scope.getNewBoard = () => {
    gameboard.getNewGameBoard()
      .success((gameBoard)=>{
          $scope.gameBoard = gameBoard;
          $scope.team = gameBoard.leadTeam.toUpperCase();
          scoreboard.resetScore(gameBoard.leadTeam.toUpperCase());
      })
  }

}]);
