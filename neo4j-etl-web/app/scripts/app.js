'use strict';

/**
 * @ngdoc overview
 * @name neo4jEtlWebApp
 * @description
 * # neo4jEtlWebApp
 *
 * Main module of the application.
 */
angular
    .module('neo4jEtlWebApp', [
    'ngResource',
    'ngRoute'
  ])
    .constant('_', window._)
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl',
                controllerAs: '$ctrl'
            })
            .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl',
                controllerAs: 'about'
            })
            .otherwise({
                redirectTo: '/'
            });
    });