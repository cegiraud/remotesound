(function () {
    'use strict';

    angular.module('remotesound.layout').controller('FooterController', FooterController);

    function FooterController() {
      var vm = this;
      vm.version='0.0.2';
      vm.date = new Date();
    }

})();

