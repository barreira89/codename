app.factory('scoreboard', ['$http', function($http) {
    // Need to refactor to calculate based on unselected tiles
	
	var scoreboard = {
        red: {
            value: 8
        },
        blue: {
            value: 8
        },
        scoreRed: function() {
             scoreboard.red.value -- ;
        },
        scoreBlue: function() {
            scoreboard.blue.value --;
        },
        resetScore: function(leadTeam) {
            scoreboard.red.value = 8;
            scoreboard.blue.value = 8;
            leadTeam == 'RED' ? scoreboard.red.value++ : scoreboard.blue.value++;
        },
        leadTeam : {
        	value: null
        },
        changeTeam : function() {
        	scoreboard.leadTeam.value == 'RED' ? scoreboard.leadTeam.value = 'BLUE' : scoreboard.leadTeam.value = 'RED'
        },
        calculateScores: function(gameBoard) {
        	if(gameBoard.gameRows && gameBoard.gameRows){      		
        		var score = gameBoard.gameRows
        			.map((row)=>{
	            		return row.rowTiles
		            		.filter((tile) =>{
		            			return (tile.selected == false && (tile.team == 'RED' || tile.team == 'BLUE'))
		            		})
		    				.map((tile) => {
		    					return {team: tile.team, selected: tile.selected}}
		    				)
		    				.reduce((prev, current) =>{
		    					prev[current.team] = prev[current.team] + 1 || 1;
		    					return prev
		    				}, {RED: 0, BLUE:0})
	        			
	        		})
	        		//{blue: 2, red: 2}
        			.reduce((prev, current) => {
        				prev['RED'] = prev['RED'] + current['RED'];
        				prev['BLUE'] = prev['BLUE'] + current['BLUE'];
        				return prev;
        			}, {RED:0, BLUE:0})
        			scoreboard.red.value = score.RED
        			scoreboard.blue.value = score.BLUE
        	}
        }
    };
    return scoreboard;
}]);
