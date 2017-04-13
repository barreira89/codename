app.factory('session', ['$http',  function($http) {
    var userName;
    var userId;

    return sessionObject = {
      userName:null,
      userId:null
    }

    return sessionObject;
}]);
