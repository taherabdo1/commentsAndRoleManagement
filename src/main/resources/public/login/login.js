'use strict';

angular
		.module('myApp.login', [ 'ngRoute' ])

		.controller(
				'LoginCtrl',
				function($scope, $http, $rootScope, $location) {
					$scope.login = function() {

						var loginData = {
							"username" : btoa($scope.userName),
							"password" : btoa($scope.pasword)
						};
						/*
						 * $http.post("http://localhost:8080/login"
						 * ,{params:{"username": $scope.userName, "password":
						 * $scope.pasword}}) .then(function(response) {
						 * $log.log(response.data);
						 * 
						 * });
						 */

						$http(
								{
									method : 'POST',
									url : 'http://localhost:8080/login',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									transformRequest : function(obj) {
										var str = [];
										for ( var p in obj)
											str
													.push(encodeURIComponent(p)
															+ "="
															+ encodeURIComponent(obj[p]));
										return str.join("&");
									},
									data : {
										username : $scope.userName,
										password : $scope.pasword
									}
								})
								.success(
										function(response) {

											console.log("200");

											$http(
													{
														method : 'GET',
														url : 'http://localhost:8080/user',
														headers : {
															'Content-Type' : 'application/x-www-form-urlencoded'
														},
														data : {}
													})
													.success(
															function(response) {

																$rootScope.userEmail = response.name;
																if (response.authorities[0].authority == 'ROLE_USER') {
																	// go to the
																	// ordinary
																	// users
																	// page
																	console
																			.log("this is a normal user");
																	$location
																			.path("/userComments");

																} else {
																	// go to the
																	// monitor
																	// page
																	$location
																	.path("/moderatorComments");

																}
																console
																		.log(response);
															});

										});

					};
				});

/*
 * var headers ={authorization : "Basic " + btoa($scope.username + ":" +
 * $scope.pasword) };
 * 
 * $http.post('http://localhost:8080/login', {headers :
 * headers}).then(function(response) { if (response.data.name) {
 * console.log("authenticated!!!!!!!!!!!!!!!!!!!!"); //$rootScope.authenticated =
 * true; } else { console.log("unauthenticated!!!!!!!!!!!!!!!!!!!!");
 * //$rootScope.authenticated = false; } //callback && callback(); }, function() {
 * //$rootScope.authenticated = false; // callback && callback(); });
 */
/*
 * var headers ={authorization : "Basic " + btoa( $scope.userName + ":" +
 * $scope.pasword) };
 * 
 * $http.get("http://localhost:8080/login", {headers :
 * headers}).then(function(response) { $log.log(response.data); }
 * 
 */

// to prevent from log in if he is already logged in
// $log.log("from login token from rootScope: "+$rootScope.token);
// if ($rootScope.token != 'undefined' && typeof($rootScope.token) !=
// 'undefined'){
// $log.log("from login: " + $rootScope.token);
// $location.path("/notes");
// }
