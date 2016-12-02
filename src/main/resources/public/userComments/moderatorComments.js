'use strict';

var app = angular.module('myApp.comments');

app.controller('moderatorCommentsCtrl', function($scope, $rootScope, $http,
		$log, $location, $routeParams, $filter) {

	$scope.getCommets = function() {

		console.log("inside getComments function in the comments page");
		$http({
			method : 'POST',
			url : 'http://localhost:8080/getAllUnconfirmedComments',
		}).success(function(response) {
			$scope.comments = response;
			console.log(response);
		}).error(function(response) {
			$location.path("/login");
		});
	};

	$scope.confirmCommet = function(index) {

		var commentToBeConfirm = $scope.comments[index];

		$http({
			method : 'POST',
			url : 'http://localhost:8080/confirmComment',
			data : commentToBeConfirm.id
		}).success(function(response) {
			console.log("confirmed");
			// get the new list
			$scope.getCommets();
			$location.path("/moderatorComments");
		}).error(function(response) {
			console.log(response);
			$location.path("/login");
		});
	};

	$scope.logout = function() {

		$http({
			method : 'GET',
			url : 'http://localhost:8080/logout',
		}).success(function(response) {
			$location.path("/login");
			console.log(response);
		});
	}

	// call getComments once the page is loaded
	$scope.getCommets();
});