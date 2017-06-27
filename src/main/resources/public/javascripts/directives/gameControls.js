app.directive('gameControls', [ 'scoreboard', function(scoreboard) {
	return {
		restrict : 'E',
		scope : {
			updateGameBoard : '=update',
			refreshGameBoard : '=refresh',
			getNewRound: '=newround',
			game: '='
		},
		link : function(scope, elm, attr) {
			console.log(scope)
		},
		templateUrl : '/javascripts/directives/gameControls.html'
	}
} ]);
