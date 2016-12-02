'use strict';

var app = angular.module('myApp.comments', [ 'ngRoute']);

app.controller('userCommentsCtrl', function($scope, $rootScope, $http, $log,
		$location, $routeParams, $filter) {

	$scope.getCommets = function() {

		console.log("inside getComments function in the comments page");
		$http({
			method : 'POST',
			url : 'http://localhost:8080/getAllCommentsForUser',
		}).success(function(response) {
			$scope.comments = response;
			console.log(response);
		}).error(function(response){
			$location.path("/login");
		});
	}

	$scope.addCommentAction = function() {
		$location.path("/addComment");
	}
	$scope.logout  = function() {
		
		$http({
			method : 'GET',
			url : 'http://localhost:8080/logout',
		}).success(function(response) {
			$location.path("/login");
			console.log(response);
		});
	}

	$scope.getCommets();

});

app
		.controller(
				'addCommentCtrl',
				function($scope, $rootScope, $http, $log, $location,
						$routeParams, $filter) {

					$scope.add = function() {

						var reqData = {
							description : $scope.comment.description,
							userEmail : $rootScope.userEmail
						}
						console
								.log("inside addComment function in the comments module addCommentCtrl");
						$http({
							method : 'POST',
							url : 'http://localhost:8080/addComment',
							data : reqData
						}).success(function(response) {
							$scope.comments = response;
							$location.path("/userComments");

							console.log(response);
						}).error(function(response){
							$location.path("/login");
						});
					}
					$scope.cancel = function(){
						$location.path("/userComments");
					}

				});