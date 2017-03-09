(function () {
    'use strict';

    angular.module('remotesound.statistiques', ['remotesound.core']).config(config);

    function config($stateProvider) {
        $stateProvider.state('main.statistiques', {
            url: '/statistiques',
            views: {
                'content@layout': {
                    templateUrl: 'app/statistiques/statistiques.html',
                    controller: 'StatistiquesController as statistiques'
                }
            }
        });
    }

})();

