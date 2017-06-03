app.factory('config', ['$http', function($http) {
 var query = '?name=defaultConfig';

 var configServices = {}
 configServices.getCurrentSeason = function(){
   return $http({
     method: 'GET',
     url: '/api/configurations' + query
   });
 }
 configServices.updateConfig = function(configData, configId){
  return $http({
    method: 'PUT',
    url: '/api/configurations/' + configId,
    data: configData
  })
 }

 return configServices;
}]);
