'use strict';

/**
 * @ngdoc function
 * @name otrsRestaurantWebApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the otrsRestaurantWebApp
 */
angular.module('otrsRestaurantWebApp')
  .controller('MainCtrl', function ($scope) {
    $scope.selectedPage = "signIn";
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
    }
  });
