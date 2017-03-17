(function () {
    'use strict';

    angular.module('remotesound.playing').controller('PlayingController', PlayingController);

    function PlayingController(ngstomp) {
        var vm = this;
        vm.messages = {keys: {}, values: []};


        function subscribeToSubject() {
            ngstomp
                .subscribeTo('/topic/playing/start')
                .callback(processMessageStart)
                .and()
                .subscribeTo('/topic/playing/stop')
                .callback(processMessageStop)
                .connect()
        }

        function processMessageStart(message) {
            var body = angular.fromJson(message.body);
            if (!angular.isNumber(vm.messages.keys[body.id])) {
                vm.messages.keys[body.id] = vm.messages.values.length;
                vm.messages.values.push(body);
            }
        }

        function processMessageStop(message) {
            var body = angular.fromJson(message.body);
            var index = vm.messages.keys[body.id];
            if (!angular.isNumber(index)) {
                index = vm.messages.values.length;
                vm.messages.keys[body.id] = index;
                vm.messages.values.push(body);
            }
            vm.messages.values[index].stop = true;
        }

        subscribeToSubject();

    }
})();
