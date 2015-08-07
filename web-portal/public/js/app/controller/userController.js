'use strict';

/**
 * @ngdoc function
 * @name oauthApp.controller:UserCtrl
 * @description
 * # PreviewPlaceHolderCtrl
 * Controller of the alertApp
 */
angular.module('oauthApp')
    .controller('userCtrl', function ($scope, $location, $http, dataService) {
        
        // Assigns data from the user service into "token"
        // variables in controller scope. 
        var assignAllUserDataToScope = function (data) {
            $scope.userDataArray = data;
        };

        var logError = function (error) {
            console.log(error);
        };

		// Assign data for a single user
		var assignUserDataToScope = function (data) {
            $scope.userData = data;
        };
            
        // Method exposed to get specific user data
        this.getUserDataByUserName = function (userName) {
        	dataService.getUserDataByUserName(userName)
        		.then(assignUserDataToScope, logError);
        };   
        
        //When the script loads, get all the user's data
        dataService.getAllUserData()
            .then(assignAllUserDataToScope, logError);
    });