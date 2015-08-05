'use strict';

angular.module('oauthApp', ['ngRoute'])
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'views/home.html',
            controller: 'homeCtrl'
        }).when('/user', {
            templateUrl: 'views/user.html',
            controller: 'userCtrl',
            controllerAs: 'userController'
        }).when('/task', {
            templateUrl: 'views/task.html',
            controller: 'taskCtrl',
            controllerAs: 'taskController'
        }).otherwise('/');

        //Custom header is needed starting angular 1.3; else Spring security might pop authentication dialog
        // by sending the WWW-Authenticate header field in the 401 Unauhorized HTTP response
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    })
    .directive("taskComments", function () {
        return {
            restrict: 'E',
            scope: {
                taskComments: '=comments'
            },
            templateUrl: "views/task-comments.html"
        };
    })
    .directive("taskDetails", function () {
        return {
            restrict: 'E',
            templateUrl: "views/task-details.html"
        };
    });
