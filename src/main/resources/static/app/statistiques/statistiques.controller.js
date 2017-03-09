(function () {
    'use strict';

    angular.module('remotesound.statistiques').controller('StatistiquesController', StatistiquesController);

    function StatistiquesController($http) {
        var vm = this;
        vm.loadStatistiques = loadStatistiques;

        loadStatistiques();

        function loadStatistiques() {
            $http.get("statistiques").then(function (response) {
                vm.statistiques = response.data;
            });
        }

    }
})();
