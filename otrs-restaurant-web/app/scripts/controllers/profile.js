'use strict';

/**
 * @ngdoc function
 * @name otrsRestaurantWebApp.controller:ProfileController
 * @description
 * # ProfileController
 * Controller of the otrsRestaurantWebApp
 */
angular.module('otrsRestaurantWebApp')
  .controller('ProfileController', function ($scope, $http, $mdToast, $rootScope, $filter) {
    $scope.cities = ('Select Bangalore Delhi Kolkata Mumbai Muzaffarpur').split(' ').map(function (city) {
      return {
        name: city
      };
    });

    $scope.proceedToBooking = false;
    $scope.booking = {};

    function getBookingsForUser(user) {
      $http.get('http://localhost:8082/api/bookings/' + user)
        .then((response) => {
          $scope.bookings = [].concat(response.data);
        }, (error) => {
          console.log(error);
        });
    }

    if (!$rootScope.user) {
      window.location.href = '#!/';
    } else {
      $scope.loggedInUser = $rootScope.user.firstName;
      getBookingsForUser($rootScope.user.email);
    }

    $scope.fetchRestaurant = function (city) {
      $scope.restaurants = [];
      $scope.proceedToBooking = false;
      $http.get('http://localhost:8080/api/restaurant/' + city)
        .then((response) => {
          $scope.restaurants = [].concat(response.data);
        }, (error) => {

        });
    };

    $scope.selectRestaurant = function (restaurant) {
      $scope.selectedRestaurant = restaurant;
    };

    $scope.selectBooking = function (booking) {
      $scope.selectedBooking = booking;
    };

    $scope.cancelBooking = function (booking) {

    };

    $scope.proceed = function () {
      $scope.proceedToBooking = true;
    };

    $scope.confirmBooking = function () {
      $scope.booking.restaurantId = $scope.selectedRestaurant.restaurantId;
      $scope.booking.restaurantName = $scope.selectedRestaurant.name;
      $scope.booking.userId = $rootScope.user.email;
      var hours = $scope.booking.bookingTime.toTimeString().split(':')[0];
      var minutes = $scope.booking.bookingTime.toTimeString().split(':')[1];
      $scope.booking.bookingDate.setHours(hours);
      $scope.booking.bookingDate.setMinutes(minutes);
      $scope.booking.bookingDate.setSeconds(0);

      $http.post('http://localhost:8082/api/bookings/', $scope.booking)
        .then((response) => {
          getBookingsForUser($rootScope.user.email);
          var pinTo = 'bottom right';
          var toast = $mdToast.simple()
            .textContent('Booking Saved Successfully')
            .action('Close')
            .highlightAction(true)
            .highlightClass('md-accent') // Accent is used by default, this just demonstrates the usage.
            .position(pinTo);

          $mdToast.show(toast).then(function (response) {
            if (response === 'ok') {
              $mdToast.hide();
            }
          });
        }, (error) => {
          var pinTo = 'bottom right';
          var toast = $mdToast.simple()
            .textContent($scope.selectedRestaurant.name + ' is not accepting booking at this time. Please try again later')
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

  });
