(function () {
    'use strict';

    angular
        .module('remotesound')
        .config(config);

    function config($urlRouterProvider) {
        $urlRouterProvider.otherwise('/player');
    }
})();