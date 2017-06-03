app.directive('gameTile', [ 'scoreboard', function(scoreboard) {
	return {
		restrict : 'E',
		scope : {
			gametile : '=',
			team : '=',
			callback: '='
		},
		link : function(scope, elm, attr) {

			//Checks to see if value is set and sets the color
			//When clicked, sets the color
			
			scope.style = {}
			var clicked = scope.gametile.selected;
			var colorMap = {
				RED : 'RED',
				BLUE : '#3379ea',
				ASSASSIN : 'BLACK',
				NEUTRAL : 'TAN'
			}
			var tileTeam = scope.gametile.team;
			
			function setStyle(color) {
				scope.style['background-color'] = colorMap[color]
			}
			
			function scoreTeam(team){
				switch (tileTeam) {
					case 'RED':
						if (!clicked)
							scoreboard.scoreRed()
						clicked = true
						break
					case 'BLUE':
						if (!clicked)
							scoreboard.scoreBlue()
						clicked = true
						break
				}
			}
			
			if (scope.gametile.selected) {
				setStyle(tileTeam)
			}

			scope.check = function() {
				scope.gametile.selected = true;

				setStyle(tileTeam);
				
				scoreTeam(tileTeam);
				
				if(scope.callback && typeof scope.callback == 'function'){
					scope.callback();
				}
			}
		},
		templateUrl : '/javascripts/directives/gameTile.html'
	}
} ]);
