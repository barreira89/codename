app.controller('LoginController', ['$scope', 'auth', '$location', function($scope, auth, $location){
	$scope.credentials = {
		username: '',
		password: ''
	};
	$scope.errormessage;
	$scope.reg = false;

	$scope.login = function(credentials){
		auth.login(credentials.username, credentials.password)
			.then(function (){
				$location.path('/');
			})
			.catch(function (){
				$scope.errormessage = "Invalid username/password";
		});
	};

	$scope.register = function(credentials){
		auth.register(credentials.username, credentials.password, credentials.email)
			.then(function (user){
				$scope.error = false;
				$location.path('/');
			})
			.catch(function (err){
				console.log(err);
				$scope.errormessage = err.message;
		});
	};

}]);
