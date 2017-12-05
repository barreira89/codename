var app = angular.module('Codenames', ['ui.bootstrap', 'ngRoute', 'ui.router', 'ngStomp']);
app.constant('USER_ROLES',{
  all: '*',
  admin: 'admin',
  user: 'user'
});
app.config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES', function($stateProvider, $urlRouterProvider, USER_ROLES){
  $stateProvider
   .state('codemaster', {
      url: '/gameroom/:gameId/codemaster',
      controller: 'GameRoomController',
      templateUrl: '/views/codemaster.html',
      params:{
          currentRound:	null
      },
      data: {authorizedRoles: [USER_ROLES.all]},
      resolve: {
        currentGame: ['$stateParams', 'games', function($stateParams, games) {
            return games.getGameById($stateParams.gameId)
            }],
        currentRound:['$stateParams', function($stateParams){return $stateParams.currentRound}]
      }
	})
   .state('gameroom', {
      url: '/gameroom/:gameId',
      controller: 'GameRoomController',
      templateUrl: '/views/gameroom.html',
      params: {
    	  currentRound: null
      },
      data: {authorizedRoles: [USER_ROLES.all]},
      resolve: {
      	currentGame: ['$stateParams', 'games', function($stateParams, games) {
      		return games.getGameById($stateParams.gameId)
      		}],
      	currentRound:['$stateParams', function($stateParams){return $stateParams.currentRound}]
      }
    })
  	.state('login', {
  	    url: '/login',
  	    templateUrl: '/views/login.html',
  	    controller: 'LoginController',
  	    data: {
  	        authorizedRoles: [USER_ROLES.all]
  	    }
  	})
  	.state('lobby', {
  		url: '/lobby',
  		templateUrl: '/views/lobby.html',
  		controller: 'LobbyController',
  		data: {
  			authorizedRoles: [USER_ROLES.all]
  		}
  	})
    $urlRouterProvider.otherwise('/lobby');
}])
app.constant('AUTH_EVENTS', {
  loginSuccess: 'auth-login-success',
  loginFailed: 'auth-login-failed',
  logoutSuccess: 'auth-logout-success',
  sessionTimeout: 'auth-session-timeout',
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
});
app.run(function ($rootScope, AUTH_EVENTS, USER_ROLES){
  // $rootScope.$on('$stateChangeStart', function (event, next){
  //   var authorizedRoles = next.data && next.data.authorizedRoles || USER_ROLES.all  ;
  //     if(!auth.isAuthorized(authorizedRoles)){
  //       event.preventDefault();
  //     };
  // })
})
