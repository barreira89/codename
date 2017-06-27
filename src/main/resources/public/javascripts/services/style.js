app.factory('style', function() {
	
	var colorMap = {
			RED : 'RED',
			BLUE : 'BLUE',
			ASSASSIN : 'BLACK',
			NEUTRAL : 'TAN'
		}
	
	var styleService = {
			strikeThrough: {
				'text-decoration':'line-through',
				'color':'#c3b4b4'
			},
			colorMap: colorMap,
			getParent: function(elm){
				return elm.parent()[0]
			}
	}

    return styleService;
})
