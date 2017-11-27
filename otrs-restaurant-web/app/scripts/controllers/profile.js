'use strict';

/**
 * @ngdoc function
 * @name otrsRestaurantWebApp.controller:ProfileController
 * @description
 * # ProfileController
 * Controller of the otrsRestaurantWebApp
 */
angular.module('otrsRestaurantWebApp')
  .controller('ProfileController', function ($scope, $http, $mdToast, $rootScope, $filter, $mdDialog, UIConstants) {

    $rootScope.user = {
      "email": "kkishor@outlook.com",
      "firstName": "Kundan",
      "lastName": "Kishor"
    };

    $scope.cities = ('Select Bangalore Delhi Kolkata Mumbai Muzaffarpur').split(' ').map(function (city) {
      return {
        name: city
      };
    });

    $scope.proceedToBooking = false;
    $scope.booking = {};

    function getBookingsForUser(user) {
      $http.get(UIConstants.BOOKING_SERVICE_HOST + '/api/bookings/' + user)
        .then((response) => {
          if (response.data.length > 0) {
            $scope.bookings = [].concat(response.data);
          } else {
            $scope.bookings = null;
          }
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
      $http.get(UIConstants.RESTAURANT_SERVICE_HOST + '/api/restaurant/' + city)
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
      var confirm = $mdDialog.confirm()
        .title('You have selected a booking for cancellation')
        .textContent('Are you sure you want to cancel?')
        .ariaLabel('Cancel Booking')
        .ok('Please do it!')
        .cancel('Not yet');

      $mdDialog.show(confirm).then(function () {
        $http.delete(UIConstants.BOOKING_SERVICE_HOST + '/api/bookings/' + $scope.selectedBooking.bookingId)
          .then((response) => {
            var pinTo = 'bottom right';
            var toast = $mdToast.simple()
              .textContent('Booking Cancelled Successfully')
              .action('Close')
              .highlightAction(true)
              .highlightClass('md-accent') // Accent is used by default, this just demonstrates the usage.
              .position(pinTo);

            $mdToast.show(toast).then(function (response) {
              if (response === 'ok') {
                $mdToast.hide();
              }
            });
            getBookingsForUser($rootScope.user.email);
            $scope.selectedBooking = null;
          }, (error) => {
            var pinTo = 'bottom right';
            var toast = $mdToast.simple()
              .textContent('We could not cancel it. Please try again!')
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
      }, function () {
        $mdDialog.hide();
      });

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

      $http.get(UIConstants.PRICING_SERVICE_HOST + '/api/pricing/' + $scope.selectedRestaurant.restaurantId)
        .then((response) => {
          $scope.booking.price = response.data[0].pricePerTable * $scope.booking.noOfBookedTables;

          $http.post(UIConstants.BOOKING_SERVICE_HOST + '/api/bookings/', $scope.booking)
            .then((response) => {
              $mdDialog.show(
                $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Booking Confirmed at ' + $scope.selectedRestaurant.name)
                .textContent('Total payable is ' + $scope.booking.price)
                .ariaLabel('Booking Confirmed')
                .ok('Got it!')
              );
              $scope.restaurants = null;
              $scope.proceedToBooking = false;
              $scope.booking = {};
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
