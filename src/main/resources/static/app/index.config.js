(function () {
    'use strict';

    angular
        .module('remotesound')
        .filter('total', filterTotal)
        .config(routeConfig)
        .config(stompConfig);

    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/player');
    }

    function stompConfig(ngstompProvider) {
        ngstompProvider
            .url('/remotesound')
            .class(SockJS)
            .autoConnect(true);
    }
    function filterTotal () {
        return function (input, property) {
            var i = input instanceof Array ? input.length : 0;
            if (typeof property === 'undefined' || i === 0) {
                return i;
            } else if (isNaN(input[0][property])) {
                throw 'filter total can count only numeric values';
            } else {
                var total = 0;
                while (i--)
                    total += input[i][property];
                return total;
            }
        };
    }
})();