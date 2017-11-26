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
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .otherwise({
        redirectTo: '/'
      });

    $mdThemingProvider.theme('default').primaryPalette('teal').accentPalette('light-blue');
  });
