(function () {
    var myApp = angular.module('myApp', ['ngRoute', 'ngResource']);

    myApp.config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
                when('/some/mine', {
                    templateUrl: 'some/mine.html',
                    controller: 'SomeCtrl'
                }).
                when('/loan/list', {
                    templateUrl: 'loan/list.html',
                    controller: 'LoanCtrl'
                });
        }]);

    myApp.service('SomeRepository', function($resource) {
        var someResource = $resource('/some/:action', {action: '@action'});
        return someResource;
    });

    myApp.service('LoanRepository', function($resource) {
        var loanRepository = $resource('/loan/:action', {action: '@action'});
        return loanRepository;
    });

    myApp.controller('SomeCtrl', ['$scope', 'SomeRepository', function($scope, SomeRepository) {
        $scope.items = SomeRepository.get({action: 'mine'});

    }]);

    myApp.controller('LoanCtrl', ['$scope', 'LoanRepository', function($scope, LoanRepository) {
        $scope.items = LoanRepository.get({action: 'list'});

        $scope.save = function(entity) {
            var p = LoanRepository.save({action: 'add'}, entity);
        }

        $scope.extend = function (entity) {
            var res = LoanRepository.save({action: 'extend'}, entity, function(res) {
                entity.extensions = res.entity.extensions;
            })
        }

    }]);
}());
