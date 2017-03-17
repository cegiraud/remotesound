(function () {
    'use strict';

    angular.module('remotesound.playing', ['remotesound.core']).config(config);

    function config($stateProvider) {
        $stateProvider.state('main.playing', {
            url: '/playing',
            views: {
                'content@layout': {
                    templateUrl: 'app/playing/playing.html',
                    controller: 'PlayingController as playing'
                }
            }
        });
    }

})();

