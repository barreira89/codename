app.directive('gameTile', ['scoreboard', function(scoreboard){
	return {
		restrict: 'E',
		scope: {
			gametile: '=',
			team: '='
		},
		link: function(scope, elm, attr){
			scope.check = function () {
				var tileTeam = scope.gametile.team;
				
				if(tileTeam == 'RED'){
					elm.css("color", "red")
					scoreboard.scoreRed()
				}
				if(tileTeam == 'BLUE'){
					elm.css("color", "blue")
					scoreboard.scoreBlue()
				}
				if(tileTeam == 'NEUTRAL'){
					elm.css("color", "grey")
				}
				if(tileTeam == "ASSASSIN"){
					elm.css("color", "yellow")
				}

				if(tileTeam == scope.team){
					scope.correct = "!"
				} else {
					scope.correct = "X"
				}
			}
		},
		templateUrl: '/javascripts/directives/gameTile.html'
}}]);
