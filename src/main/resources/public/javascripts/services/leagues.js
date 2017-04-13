app.factory('leagues', ['$http', '$q', function($http, $q) {
 var leagueServices = {};

leagueServices.getLeagues = function (){
	 return $http({
		 method: 'GET',
		 url: '/api/leagues'
	 })
 }

leagueServices.getLeagueById = function (leagueId){
	return $http({
		method: 'GET',
		url: '/api/leagues/' + leagueId
	})
}

leagueServices.createLeague = function (username) {
	return $http({
			method: 'POST',
			url: '/api/leagues'
	})
}

leagueServices.getLeaguesByUsername = function (username){
	return $http({
		method: 'GET',
		url: '/api/leagues?username=' + username
	})
}

leagueServices.getLeaguesByUsernameWithSummary = function (username){
      var deferred = $q.defer();

      //Get All the leagues a user is in
      leagueServices.getLeaguesByUsername(username).success(function(userLeagues){
        var outputArray = []

        //Get the summary for each league
        userLeagues.forEach(function(league, index){
            leagueServices.getLeagueSummary(league._id).success(function(leagueSummary){
              //Map the values
              var outputValue = {id: league._id, name: league.name, summary: leagueSummary};
              //Add them to the summary
              outputArray.push(outputValue);
              //If last league, return the values to the promise
              if(index == userLeagues.length - 1){
                deferred.resolve(outputArray);
              }
            }).error(function(err){
                deffered.reject();
            })
        })
      }).error(function(err){
            deferred.reject();
      })

      return deferred.promise;
}

leagueServices.updateLeague = function (leagueId, data){
	return $http({
		method: 'PUT',
		url: '/api/leagues/' + leagueId,
		data: data
	})
}

leagueServices.removeUserFromLeague = function (username, league){
	var i = league.users.indexOf(username);
	if (i != -1) {
		league.users.splice(i, 1);
	}
	console.log(league.users);

	return leagueServices.updateLeague(league._id, league);

}

 leagueServices.getLeagueSummary= function(leagueId){
   return $http({
     method: 'GET',
     url: '/api/leagues/' + leagueId + '/summary'
   })

 }

 return leagueServices;
}]);
