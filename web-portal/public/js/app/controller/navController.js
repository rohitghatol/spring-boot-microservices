'use strict';

angular.module('oauthApp')
    .controller('navigationCtrl', ['$rootScope', '$scope', '$http', '$location', 'dataService', function ($rootScope, $scope, $http, $location, dataService) {

        var assignAuthenticationStatus = function (data) {
            if (data.name) {
                $rootScope.userAuthenticated = true;
                $rootScope.loggedInUserName = data.name;
            } else {
                $rootScope.userAuthenticated = false;
            }
        };

        var handleError = function (error) {
            console.log(error);
            $rootScope.userAuthenticated = false;
        };

        dataService.getLoggedInUser().then(assignAuthenticationStatus, handleError);

        $scope.logout = function () {
            $http.post('logout', {}).success(function () {
                $rootScope.userAuthenticated = false;
                $location.path("/");
            }).error(function (data) {
                $rootScope.userAuthenticated = false;
            });
        }
    }]);
