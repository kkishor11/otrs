'use strict';

/**
 * @ngdoc overview
 * @name otrsRestaurantWebApp
 * @description
 * # otrsRestaurantWebApp
 *
 * Main module of the application.
 */
angular
  .module('otrsRestaurantWebApp', [
    'ngMaterial',
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider, $mdThemingProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/profile', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileController'
      })
      .when('/booking', {
        templateUrl: 'views/booking.html',
        controller: 'BookingController'
      })
      .when('/billing', {
        templateUrl: 'views/billing.html',
        controller: 'BillingController'
      })
      .otherwise({
        redirectTo: '/'
      });

    $mdThemingProvider.theme('default').primaryPalette('teal').accentPalette('light-blue');
  });
