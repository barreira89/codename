app.directive('codemasterTile', [ 'scoreboard', 'style', function(scoreboard, style) {
	return {
		restrict : 'E',
		scope : {
			gametile : '=',
			team : '=',
			callback: '='
		},
		link : function(scope, elm, attr) {
			
			var clicked = scope.gametile.selected;
			var tileTeam = scope.gametile.team;
			
			scope.style = {}
			
			style.getParent(elm).bgColor = style.colorMap[tileTeam]

			if (scope.gametile.selected) {
				scope.style = style.strikeThrough;
			}

		},
		templateUrl : '/javascripts/directives/gameTile.html'
	}
} ]);
