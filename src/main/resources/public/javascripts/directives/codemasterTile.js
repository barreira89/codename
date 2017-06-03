app.directive('codemasterTile', [ 'scoreboard', function(scoreboard) {
	return {
		restrict : 'E',
		scope : {
			gametile : '=',
			team : '=',
			callback: '='
		},
		link : function(scope, elm, attr) {

			var clicked = scope.gametile.selected;
			var colorMap = {
				RED : 'RED',
				BLUE : 'BLUE',
				ASSASSIN : 'BLACK',
				NEUTRAL : 'TAN'
			}
			
			var tileTeam = scope.gametile.team;
			var parent = elm.parent()[0]
			
			function setStyle(elm, color) {
				var parent = elm.parent()[0];
				parent.bgColor = color
			}
			
			function assignColor(tileTeam){
				switch (tileTeam) {
				case 'RED':
					setStyle(elm, 'RED');
					if (!clicked)
						scoreboard.scoreRed()
					clicked = true
					break
				case 'BLUE':
					parent.bgColor = "BLUE"
					if (!clicked)
						scoreboard.scoreBlue()
					clicked = true
					break
				case 'NEUTRAL':
					parent.bgColor = "TAN"
					break
				case 'ASSASSIN':
					parent.bgColor = "BLACK"
					break
				}
			}
			assignColor(tileTeam);
			
			if (scope.gametile.selected) {
				//Cover
				elm[0].style.color = colorMap[scope.gametile.team]
			}

		},
		templateUrl : '/javascripts/directives/gameTile.html'
	}
} ]);
