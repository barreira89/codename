var app = angular.module('Football', ['ui.bootstrap', 'ngRoute', 'ui.router']);
app.constant('USER_ROLES',{
  all: '*',
  admin: 'admin',
  user: 'user'
});
app.config(['$stateProvider', '$urlRouterProvider', 'USER_ROLES', function($stateProvider, $urlRouterProvider, USER_ROLES){
  $stateProvider
    .state('gameboard', {
        url: '/',
        controller: 'MainController',
        templateUrl: '/views/gameboard.html',
        data: {authorizedRoles: [USER_ROLES.all]}
      })
  	.state('login', {
  	    url: '/login',
  	    templateUrl: '/views/login.html',
  	    controller: 'LoginController',
  	    data: {
  	        authorizedRoles: [USER_ROLES.all]
  	    }
  	})
    $urlRouterProvider.otherwise('/');
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
