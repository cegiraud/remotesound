(function () {
    'use strict';

    angular.module('remotesound.player', ['remotesound.core']).config(config);

    function config($stateProvider) {
        $stateProvider.state('main.player', {
            url: '/player',
            views: {
                'content@layout': {
                    templateUrl: 'app/player/player.html',
                    controller: 'PlayerController as player'
                }
            }
        });
    }


})();
