(function () {
    'use strict';

    angular.module('remotesound.player').controller('PlayerController', PlayerController);

    function PlayerController($http) {
        var vm = this;
        vm.search = "";
        vm.page = 0;
        vm.pageSize = 15;
        vm.total = 0;
        vm.totalPages = 0;
        vm.history = JSON.parse(localStorage.getItem('history')) || [];
        vm.favorites = JSON.parse(localStorage.getItem('favorites')) || [];
        vm.play = play;
        vm.addTofavorites = addTofavorites;
        vm.removeFromfavorites = removeFromfavorites;
        vm.fetch = fetch;
        vm.addSongs = addSongs;

        fetch();

        function fetch(page) {
            var url = "sounds?" +
                "title=" + (vm.search || '') +
                "&page=" + (page || 0) +
                "&size=" + vm.pageSize;

            $http.get(url).then(function (response) {
                vm.items = response.data.content;
                vm.page = response.data.number;
                vm.total = response.data.totalElements;
                vm.totalPages = response.data.totalPages;
                vm.first = response.data.first;
                vm.last = response.data.last;
            });
        }

        function play(item, addToHistory) {
            $http.get("playurl?uri=" + item.url);
            if (addToHistory) {
                addHistory(item);
            }
        }


        function addHistory(item) {
            vm.history.unshift(item);
            vm.history.length = vm.history.length > 20 ? 20 : vm.history.length;
            localStorage.setItem('history', JSON.stringify(vm.history));
        }

        function addTofavorites(item) {
            vm.favorites.unshift(item);
            vm.favorites.length = vm.favorites.length > 20 ? 20 : vm.favorites.length;
            localStorage.setItem('favorites', JSON.stringify(vm.favorites));
        }

        function removeFromfavorites(item) {
            var index = vm.favorites.indexOf(item);
            if (index > -1) {
                vm.favorites.splice(index, 1);
            }
            localStorage.setItem('favorites', JSON.stringify(vm.favorites));
        }

        function addSongs() {
            $http.post("/sounds",
                {
                    'url': vm.url,
                    'title': vm.title
                });
        }
    }
})();
