'use strict';

var app = angular.module('myApp.comments', [ 'ngRoute' ]);
app.controller('userCommentsCtrl', function($scope, $rootScope, $http, $log,
		$location, $routeParams, $filter) {
	
	
	$scope.getCommets = function() {
		
		console.log("inside getComments function in the comments page");
		$http(
				{
					method : 'POST',
					url : 'http://localhost:8080/getAllCommentsForUser',
				}).success(
				function(response) {
					$scope.comments = response;
					console.log(response);
				});
	}
	
	$scope.getCommets();

});