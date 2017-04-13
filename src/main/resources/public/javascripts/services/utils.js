app.factory('util', ['$http', '$httpParamSerializer', function($http, $httpParamSerializer) {
 var utils = {};

 // utils.getArgumentNames = function(fun) {
 //     var names = fun.toString().match(/^[\s\(]*function[^(]*\(([^)]*)\)/)[1]
 //         .replace(/\/\/.*?[\r\n]|\/\*(?:.|[\r\n])*?\*\//g, '')
 //         .replace(/\s+/g, '').split(',');
 //     return names.length == 1 && !names[0] ? [] : names;
 // }

 //
 // var paramArray = util.getArgumentNames(this.getPicksByUsernameAndWeek);
 // var object = {};
 // var args = [...arguments];
 // console.log(args);
 // console.log(paramArray);
 // args.forEach(function (value, index) {
 //   object[paramArray[index]] = value;
 // })
 // console.log(object);

  utils.getQueryParams = function(inputParameters) {
    //console.log(getrgumentNames(utils.getQueryParams));

    return($httpParamSerializer(inputParameters));
  }

 utils.attachUserPicksToGames = function (gameList, userModel) {
 	  var userPicks = userModel.pickModel

 		if (gameList && userPicks) {
 			// console.log('loop');
      var userPickLookUp = userPicks.reduce(function (prev, current){
        prev[current.game] = current;
        return prev;
      }, {})
      console.log(pickLookUp);
      console.log(userPickLookUp);

 			gameList.forEach(function (game) {
 				var defaultGame = {
 					user : userModel._id,
 					game : game._id,
 					username : userModel.username,
 					week : game.weekNumber
 				}
 				game.userPicks = userPickLookUp[game._id] || defaultGame;
 			})
 		}
 }

 utils.objectToArray = function(object){
   var arry = []
   for(key in object){
     arry.push(object[key]);
   }
   return arry;
 }

 utils.gatherUserPicks = function (gameList) {
 	var userPicks = [];

 	gameList.forEach(function (game) {
 		userPicks.push(game.userPicks);
 	})

 	return userPicks;

 }

 return utils;
}]);
