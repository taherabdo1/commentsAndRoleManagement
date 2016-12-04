'use strict';

var app = angular.module('myApp.comments', [ 'ngRoute' ]);

app.controller('userCommentsCtrl', function($scope, $rootScope, $http, $log,
		$location, $routeParams, $filter) {

	// check if the user is logged in
	$scope.authorize = function() {
		if ($rootScope.userEmail == undefined || $rootScope.userEmail == "")
			$location.path("/login");
	}

	$scope.getCommets = function() {

		console.log("inside getComments function in the comments page");
		$http({
			method : 'POST',
			url : 'http://localhost:8080/getAllCommentsForUser',
		}).success(function(response) {
			$scope.comments = response;
			console.log(response);
		}).error(function(response) {
			$location.path("/login");
		});
	}

	$scope.addCommentAction = function() {
		$location.path("/addComment");
	}
	$scope.logout = function() {

		$http({
			method : 'GET',
			url : 'http://localhost:8080/logout',
		}).success(function(response) {
			$location.path("/login");
			$rootScope.userEmail = undefined;
			console.log("user loged out");
			console.log(response);
		});
	}

	// call the authorize finction
	$scope.authorize();
	$scope.getCommets();

});

app
		.controller(
				'addCommentCtrl',
				function($scope, $rootScope, $http, $log, $location,
						$routeParams, $filter) {

					// check if the user is logged in
					$scope.authorize = function() {
						if ($rootScope.userEmail == undefined
								|| $rootScope.userEmail == "")
							$location.path("/login");
					}
					$scope.add = function() {


						if ($scope.comment  == undefined) {
							console.log("description is empty!!");
							$scope.showError = "true";
						}

						else {
							var reqData = {
									description : $scope.comment.description,
									userEmail : $rootScope.userEmail
								}
							$scope.showError = "false";
							
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
							}).error(function(response) {
								$location.path("/login");
							});
						}
					}
					$scope.cancel = function() {
						$location.path("/userComments");
					}
					// call the authorize finction
					$scope.authorize();

				});