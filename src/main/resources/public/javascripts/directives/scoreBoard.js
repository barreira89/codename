app.directive('scoreBoard', ['scoreboard', function(scoreboard){
	return {
		restrict: 'E',
		scope: {
		},
		link: function(scope, elm, attr){
			
			scope.redRemaining = scoreboard.red
			scope.blueRemaining = scoreboard.blue

		},
		templateUrl: '/javascripts/directives/scoreBoard.html'
}}]);
