(function () {
    angular.module('myApp',['ngMaterial','ngMessages','md.data.table'])
        .controller('MainController',['$scope','$http',function($scope,$http) {
            $scope.userReport = {
                task:'1) 是是是  \n 2) 啥啥啥'
            }
            $scope.selected = [];
            $scope.userId = 'how'

            $scope.Report = {
                data:[
                    {startDate:'2017-01-01'},
                    {startDate:'2017-01-11'},
                    {startDate:'2017-01-21'},
                    {startDate:'2017-02-01'},
                    {startDate:'2017-01-11'},
                ]
            }

            $scope.newReport = {
                startDate:'',
                endDate:'',
                reporter:'',
                submitDate:'',
                summary:''
            }
            
            $scope.goNewReport = function () {
                $scope.data.selectedIndex = '1';
            }

            $scope.data = {
                selectedIndex:'1'
            }

            $scope.find = function() {
                $http.get("http://localhost:8080/commit?userId="+$scope.userId).then(function (resp) {
                    $scope.newReport.summary = resp.data.map(function (d) {
                        return d.message;
                    }).join('\n');

                })
            }

            $scope.find();
        }]);
})(angular);