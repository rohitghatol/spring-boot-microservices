'use strict';

/**
 * @ngdoc function
 * @name oauthApp.controller:UserCtrl
 * @description
 * # PreviewPlaceHolderCtrl
 * Controller of the alertApp
 */
angular.module('oauthApp')
    .controller('taskCtrl', function ($scope, $location, $http, dataService) {

        // Assigns data from the task service into "token"
        // variables in controller scope. 
        var assignAllTaskDataToScope = function (data) {
            $scope.taskDataArray = data;
        };

        var logError = function (error) {
            console.log(error);
        };

        // Assign data for a single user
        var assignTaskDataToScope = function (data) {
            $scope.taskData = data;
        };

        var assignUserTaskDataToScope = function (data) {
            $scope.taskDataForUser = data;
        };

        // Method exposed to get specific task data
        this.getTaskDataByTaskId = function (taskId) {
            dataService.getTaskDataByTaskId(taskId)
                .then(assignTaskDataToScope, logError);
        };

        // Method exposed to get specific task data
        this.getTaskDataByUserName = function (userName) {
            dataService.getTaskDataByUserName(userName)
                .then(assignUserTaskDataToScope, logError);
        };

        //When the script loads, get all the user's data
        dataService.getAllTaskData()
            .then(assignAllTaskDataToScope, logError);
    });
