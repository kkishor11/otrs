'use strict';

/**
 * @ngdoc function
 * @name otrsRestaurantWebApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the otrsRestaurantWebApp
 */
angular.module('otrsRestaurantWebApp')
  .controller('MainCtrl', function ($scope, $http, $mdToast, $rootScope, $location) {

    if ($rootScope.user) {
      window.location.href = "#!/profile";
    }

    $scope.user = {};
    $scope.loggedInUser = null;

    $scope.selectedPage = 'signIn';
    $scope.signInPage = true;
    $scope.signUpPage = false;
    $scope.guestPage = false;

    $scope.goto = function (page) {
      $scope.selectedPage = page;
      if (page === 'signIn') {
        $scope.signInPage = true;
        $scope.signUpPage = false;
        $scope.guestPage = false;
      } else if (page === 'signUp') {
        $scope.signInPage = false;
        $scope.signUpPage = true;
        $scope.guestPage = false;
      } else {
        $scope.signInPage = false;
        $scope.signUpPage = false;
        $scope.guestPage = true;
      }
    };

    $scope.signin = function () {
      $http.post('http://localhost:8083/oauth/token?grant_type=password&scope=write&username=' + $scope.user.email + '&password=' + $scope.user.password, '', {
          headers: {
            'Authorization': 'Basic b3Rycy10cnVzdGVkLWNsaWVudDppcy10aGlzLWEtc2VjcmV0LWFueW1vcmU='
          }
        })
        .then(function (response) {
          if (response === null || response === '') {
            return [];
          } else {
            $rootScope.user = {};
            $rootScope.user.authToken = response.data.access_token;
            $rootScope.user.tokenType = response.data.token_type;
            $rootScope.user.email = $scope.user.email;
            $http.get('http://localhost:8083/api/users/profile/' + $scope.user.email, {
              headers: {
                'Authorization': response.data.token_type + ' ' + response.data.access_token
              }
            }).then(function (response) {
              $rootScope.user.firstName = response.data[0].firstName;
              $rootScope.user.lastName = response.data[0].lastName;
              $scope.loggedInUser = response.data[0].firstName;
              window.location.href = "#!/profile";
            }, function (error, response) {
              console.log(error);
              console.log(response);
            });
            return response;
          }
        }, function (response, error) {
          var pinTo = 'bottom right';
          var toast = $mdToast.simple()
            .textContent('Wrong Email or Password. Please try again')
            .action('Close')
            .highlightAction(true)
            .highlightClass('md-accent') // Accent is used by default, this just demonstrates the usage.
            .position(pinTo);

          $mdToast.show(toast).then(function (response) {
            if (response === 'ok') {
              $mdToast.hide();
            }
          });
        });
    };

    /*
    *{
          "grant_type": "password",
          "scope": "all",
          "username": "kkishor@outlook.com",
          "password": "kundan"
        }
    */

    $scope.register = function () {
      $http.post('http://localhost:8083/api/users/register', $scope.user)
        .then(function (response) {
          if (response === null || response === '') {
            return [];
          } else {
            $scope.selectedPage = 'signIn';
            $scope.signInPage = true;
            $scope.signUpPage = false;
            $scope.guestPage = false;

            var email = $scope.user.email;
            $scope.user = {};
            $scope.user.email = email;
            var pinTo = 'top right';
            var toast = $mdToast.simple()
              .textContent('User registered successfully')
              .action('Close')
              .highlightAction(true)
              .highlightClass('md-accent') // Accent is used by default, this just demonstrates the usage.
              .position(pinTo);

            $mdToast.show(toast).then(function (response) {
              if (response === 'ok') {
                $mdToast.hide();
              }
            });
            return response;
          }
        }, function (response, error) {
          return error;
        });
    };

    $scope.continueGuest = function () {
      $http.post('http://localhost:8083/api/users/register', $scope.user)
        .then(function (response) {
          if (response === null || response === '') {
            return [];
          } else {
            $scope.selectedPage = 'signIn';
            $scope.signInPage = true;
            $scope.signUpPage = false;
            $scope.guestPage = false;

            var email = $scope.user.email;
            $scope.user = {};
            $scope.user.email = email;
            $rootScope.user = {};
            $rootScope.user.authToken = response.data.access_token;
            $rootScope.user.tokenType = response.data.token_type;
            $rootScope.user.email = $scope.user.email;
            $rootScope.user.firstName = $scope.user.firstName;
            $rootScope.user.lastName = $scope.user.lastName;
            $scope.loggedInUser = $scope.user.firstName;
            var pinTo = 'bottom right';
            var toast = $mdToast.simple()
              .textContent('Guest account created.')
              .action('Close')
              .highlightAction(true)
              .highlightClass('md-accent') // Accent is used by default, this just demonstrates the usage.
              .position(pinTo);

            $mdToast.show(toast).then(function (response) {
              if (response === 'ok') {
                $mdToast.hide();
              }
            });
            window.location.href = "#!/profile";
            return response;
          }
        }, function (response, error) {
          return error;
        });
    };
  });
