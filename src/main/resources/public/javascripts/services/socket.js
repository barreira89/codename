app.factory('socket', [ '$http', function($http) {
	
	var socketCallback;
	var socketUpdateCallback;
	var host;
	
	var socketService = {
			itemList: {items:[]},
			clueList: {
				red: [],
				blue: []
			},
			start: function (gameId, callback, updateCallback) {
				if(!gameId) return;
				connect(gameId);
				socketCallback = callback;
				socketUpdateCallback = updateCallback;
			}
	};
	
	$http({
		method: 'GET',
		url: '/',
	}).then((data) => {
		host = data.data.ip;
	})
	
	function connect(gameId) {
		var sock = new SockJS("/ws/");
		var stompClient = Stomp.over(sock);

		stompClient.debug = null;
		stompClient.connect({}, function(frame){
			
			//Connect to Clue Topic
			stompClient.subscribe('/topic/gameclues/' + gameId, function(message){
	            socketService.itemList.items.push(JSON.parse(message.body));
	            addToList(JSON.parse(message.body));
	            socketCallback(message);
	        });
			
			//Connect to Changes Topic
			stompClient.subscribe('/topic/gamechange/' + gameId, function(message){
				console.log('MESSAGE from Game Change' + message);
				socketUpdateCallback();
			});
		})
		
		//Send Clue
		socketService.sendClue = function(clue, numberOfWords, team, roundNumber){
			stompClient.send("/app/gameclues/" + gameId + '/' + roundNumber, {}, JSON.stringify({'clue':clue ,  'numberOfWords': numberOfWords, 'team': team }));
		}
		
		//Notify of Update to Game
		socketService.sendUpdate = function (roundNumber){
			stompClient.send("/app/gamechange/" + gameId + '/' + roundNumber, {}, "TEST");
		}
		
	}
	
	function addToList(message){
		var team = message.team;
		if(team == 'BLUE'){
			socketService.clueList.blue.push(message)
		} else if (team == 'RED'){
			socketService.clueList.red.push(message)
		}
	}
	
	return socketService;


} ]);
