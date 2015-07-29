'use strict';

angular.module('oauthApp')
    .controller('navigationCtrl', ['$rootScope', '$scope', 'dataService', function ($rootScope, $scope, dataService) {

            var assignAuthenticationStatus = function (data) {
                if (data.name) {
                    $rootScope.userAuthenticated = true;
                } else {
                    $rootScope.userAuthenticated = false;
                }
            };

            var handleError = function (error) {
                console.log(error);
                $rootScope.userAuthenticated = false;
            })

        dataService.getLoggedInUser().then(assignAuthenticationStatus, handleError);
    }]);
