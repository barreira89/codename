app.factory('gameboard', ['$http', '$q', 'games', function($http, $q, games) {
    var gameservices = {};

    gameservices.getNewGameBoard = function() {
        return $http({
            method: 'GET',
            url: '/api/gameboards'
        })
    }
    
    gameservices.getGameBoardById = function(id) {
    	var gameBoardId = id || ''
    	return $http({
    		method: 'GET',
    		url: '/api/gameboards/' + gameBoardId
    	})
    }
    
    gameservices.updateGameBoard = function(gameBoardId, gameBoard) {
    	var gameBoardId = gameBoardId || ''
    	return $http({
    		method: 'PUT',
    		url: '/api/gameboards/' + gameBoardId,
    		data: gameBoard
    	})
    }
    
    gameservices.newGameBoardForGame = function(gameId) {
    	if(!gameId) return false;
    	return $http({
    		method: 'POST',
    		url: '/api/games/' + gameId + '/rounds'
    	})
    }


    gameservices.getNewRound = function(gameId) {
    	var deferred = $q.defer();
    	gameservices.newGameBoardForGame(gameId)
    		.success((data) => {
    			games.getGameById(gameId)
    				.success((game) =>{
    					deferred.resolve(game);
    				})
    				.error((err) =>{
    					console.log(err);
    					deferred.reject(err);
    				})
    		})
    		.error((err) =>{
    			console.log(err);
    			deferred.reject(err);
    		})
    		
    	return deferred.promise;
    }
    
    gameservices.mapClues = function (clueList){
    	if(!clueList) return
    	return {
    		red: clueList.filter((clue) => {return clue.team == 'RED'}),
    		blue: clueList.filter((clue) => {return clue.team == 'BLUE'})
    	}
    }
    
    //Add to Clue Service
    gameservices.getGameCluesByGameAndRound = function(gameId, roundNumber){
    	if(!gameId || !roundNumber) return
    	
    	return $http({
    		method: 'GET',
    		url: '/api/games/' + gameId + '/rounds/' + roundNumber + '/clues'
    	})
    }
    
    gameservices.addGameClueByGameAndRound = function(gameId, roundNumber, clue){
    	if(!gameId || !roundNumber) return
    	
    	return $http({
    		method: 'POST',
    		url: '/api/games/' + gameId + '/rounds/' + roundNumber + '/clues',
    		data: clue
    	})
    }
    
    /*
     *   Returns:
     *   	{gameBoard: gameboard, clueList: mappedCluesForRound}
     * 
     */

    gameservices.refreshGameboard = function (gameBoardId, gameId, roundNumber) {
    	var deferred = $q.defer();
    	var gameBoardResponse = {
    			gameBoard: {},
    			clueList: {}
    	}
    	
    	gameservices.getGameBoardById(gameBoardId)
    		.success((gameBoardResult)=> {
    			gameBoardResponse.gameBoard = gameBoardResult;
    			
    			gameservices.getGameCluesByGameAndRound(gameId, roundNumber)
    				.success((clueResult)=> {
    					gameBoardResponse.clueList = gameservices.mapClues(clueResult);
    					deferred.resolve(gameBoardResponse)
    				})
    				.error((err) => {
    					console.log(err)
    					deferred.reject(err)
    				})
    		})
    		.error((err) => {
    			console.log(err)
    			deferred.reject(err)
    		})
    		
    	  return deferred.promise;
    }
    
    return gameservices;
}]);
