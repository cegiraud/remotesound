'use strict';

angular.module('myApp', [])
    .filter('ceil', function () {
        return function (input) {
            return Math.ceil(input);
        };
    })
    .controller('MainController', function ($scope, $http) {
        $scope.search = "";
        $scope.page = 0;
        $scope.pageSize = 15;
        $scope.total = 0;
        $scope.totalPages = 0;
        $scope.first = 0;
        $scope.prev = 0;
        $scope.next = 0;
        $scope.last = 0;
        $scope.history = JSON.parse(localStorage.getItem('history')) || [];
        $scope.favorites = JSON.parse(localStorage.getItem('favorites')) || [];
        $scope.play = play;
        $scope.addTofavorites = addTofavorites;
        $scope.removeFromfavorites = removeFromfavorites;
        $scope.fetch = fetch;
        $scope.addSongs = addSongs;

        fetch();

        function fetch(url) {
            if (!url) {
                $scope.page = 0;
            }
            var searchURL = url || "sounds/search/findByTitleContainingIgnoreCase?" +
                "title=" + $scope.search +
                "&page=" + $scope.page +
                "&size=" + $scope.pageSize;

            $http.get(searchURL, {headers: {"Content-Type": "application/json"}})
                .then(function (response) {
                    $scope.items = response.data._embedded.sounds;
                    $scope.page = response.data.page.number;
                    $scope.total = response.data.page.totalElements;
                    $scope.totalPages = response.data.page.totalPages;
                    $scope.first = response.data._links.first;
                    $scope.prev = response.data._links.prev;
                    $scope.next = response.data._links.next;
                    $scope.last = response.data._links.last;
                });
        }

        function play(item, addToHistory) {
            $http.get("playurl?uri=" + item.url).then(function () {
            });
            if (addToHistory) {
                addHistory(item);
            }
        }

        function addHistory(item) {
            $scope.history.unshift(item);
            $scope.history.length = $scope.history.length > 20 ? 20 : $scope.history.length;
            localStorage.setItem('history', JSON.stringify($scope.history));
        }

        function addTofavorites(item) {
            $scope.favorites.unshift(item);
            $scope.favorites.length = $scope.favorites.length > 20 ? 20 : $scope.favorites.length;
            localStorage.setItem('favorites', JSON.stringify($scope.favorites));
        }

        function removeFromfavorites(item) {
            var index = $scope.favorites.indexOf(item);
            if (index > -1) {
                $scope.favorites.splice(index, 1);
            }
            localStorage.setItem('favorites', JSON.stringify($scope.favorites));
        }

        function addSongs() {
            $http.post("/sounds",
                {
                    'url': $scope.url,
                    'title': $scope.title
                });
        }
    });
