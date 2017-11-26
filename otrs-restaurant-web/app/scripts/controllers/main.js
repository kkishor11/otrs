'use strict';

/**
 * @ngdoc function
 * @name otrsRestaurantWebApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the otrsRestaurantWebApp
 */
angular.module('otrsRestaurantWebApp')
  .controller('MainCtrl', function ($scope, $http, $mdToast) {

    $scope.user = {};

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
      $http.post('http://localhost:8083/oauth/token?grant_type=password&scope=all&username=kkishor@outlook.com&password=kundan', '', {
          headers: {
            'Authorization': 'Basic b3Rycy10cnVzdGVkLWNsaWVudDppcy10aGlzLWEtc2VjcmV0LWFueW1vcmU='
          }
        })
        .then(function (response) {
          if (response === null || response === '') {
            return [];
          } else {
            console.log(response);
            return response;
          }
        }, function (response, error) {
          return error;
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
  });
