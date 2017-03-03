'use strict';

angular.module('myApp', [])
    .filter('ceil', function() {
        return function(input) {
            return Math.ceil(input);
        };
    })
    .controller('MainController', function ($scope, $http) {
        $scope.search = "";
        $scope.offset = 0;
        $scope.pageSize = localStorage.getItem('pageSize') || 15;
        $scope.total = 0;
        $scope.history = JSON.parse(localStorage.getItem('history')) || [];
        $scope.favorites = JSON.parse(localStorage.getItem('favorites')) || [];
        $scope.changePageSize = changePageSize;
        $scope.play = play;
        $scope.addTofavorites = addTofavorites;
        $scope.removeFromfavorites = removeFromfavorites;
        $scope.fetch = fetch;
        $scope.prevPage = prevPage;
        $scope.nextPage = nextPage;
        $scope.hasPrevPage = hasPrevPage;
        $scope.hasNextPage = hasNextPage;

        fetch(0);

        function fetch(offset) {
            var searchString = $scope.search && $scope.search.length == 1 ? "" : $scope.search;
            $http.get("https://api.cleanvoice.ru/myinstants/?type=many" +
                "&search=" + searchString +
                "&offset=" + offset +
                "&limit=" + $scope.pageSize)
                .then(function (response) {
                    $scope.offset = offset;
                    $scope.items = response.data.items;
                    $scope.total = response.data.count;
                });
        }

        function prevPage() {
            fetch($scope.offset -= $scope.pageSize);
        }

        function nextPage() {
            fetch($scope.offset += $scope.pageSize);
        }


        function hasPrevPage() {
            return $scope.offset - $scope.pageSize >= 0;
        }

        function hasNextPage() {
            return $scope.offset + $scope.pageSize < $scope.total;
        }


        function play(item, addToHistory) {
            var uri = item.filename;
            $http.get("playurl?uri=https://www.myinstants.com/media/sounds/" + uri).then(function (response) {
            });
            if (addToHistory) {
                addHistory(item);
            }
        }

        function changePageSize(){
            localStorage.setItem('pageSize', JSON.stringify($scope.pageSize));
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
    });
