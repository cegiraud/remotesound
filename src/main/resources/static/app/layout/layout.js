(function () {
    'use strict';

    angular.module('remotesound.layout', ['remotesound.core']).config(configLayout);

    function configLayout($stateProvider) {
        $stateProvider.state('layout', {
            templateUrl: 'app/layout/layout.html'
        }).state('main', {
            parent: 'layout',
            abstract: true,
            views: {
                'header': {
                    templateUrl: 'app/layout/header/header.html'
                },
                'footer': {
                    templateUrl: 'app/layout/footer/footer.html',
                    controller: 'FooterController as footer'
                }
            }
        });
    }

})();
