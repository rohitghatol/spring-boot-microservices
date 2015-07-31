'use strict';

/**
 * @ngdoc service
 * @name alertApp.dataService
 * @description
 * # dataService
 * Factory in the alertApp.
 */
angular.module('oauthApp')
    .factory('dataService', function ($http, $q) {
        // We always use this angular service within the preview context
        var userApi = '/api/user';

        var taskApi = '/api/task';

        var loggedInUserApi = '/api/loggedinuser/me';

        // This method makes the REST call and the response is parsed by
        // Angular.js by default to convert to JSON. If the response is
        // successfully parsed then the JSON is available as an 'object'
        var makeRestCall = function (url) {
            return $http.get(url)
                .then(function (response) {

                    if (typeof response.data === 'object') {
                        return response.data;
                    } else {
                        // invalid response
                        return $q.reject(response.data);
                    }

                }, function (response) {
                    // something went wrong
                    return $q.reject(response.data);
                });
        };

        return {
            getAllUserData: function () {
                // Make call to the api to get all users	
                return makeRestCall(userApi);
            },

            getAllTaskData: function () {
                // Make call to the api to get all tasks       
                return makeRestCall(taskApi);
            },

            getUserDataByUserName: function (userName) {
                // Make call to the api to get user details by user name
                return makeRestCall(userApi + '/' + userName);
            },

            getTaskDataByTaskId: function (taskId) {
                // Make call to theapi to get task details by task id	        
                return makeRestCall(taskApi + '/' + taskId);
            },

            getTaskDataByUserName: function (userName) {
                return makeRestCall(taskApi + '/' + 'usertask' + '/' + userName);
            },

            getLoggedInUser: function () {
                // Make call to get the current logged in user
                return makeRestCall(loggedInUserApi);
            }
        };
    });