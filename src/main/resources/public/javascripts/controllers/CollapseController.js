app.controller('CollapseCtrl', function ($scope) {
  $scope.isCollapsed = true;
  $scope.toggleCollapse = function (){
	  $scope.isCollapsed = !$scope.isCollapsed;
  }
  $scope.menuCollapse = function (){
	  $scope.isCollapsed = true;
  }
  
});