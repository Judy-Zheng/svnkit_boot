(function () {
    angular.module('myApp',['ngMaterial','ngMessages','md.data.table'])
        .config(function($mdDateLocaleProvider) {
            $mdDateLocaleProvider.formatDate = function(date) {
                return date?moment(date).format('YYYY-MM-DD'):'';
            };
        })
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

            Date.prototype.addDays = function(days) {
                var dat = new Date(this.valueOf());
                dat.setDate(dat.getDate() + days);
                return dat;
            }

            $scope.add7Days = function (newVal,oldVal) {
                $scope.newReport.endDate = $scope.newReport.startDate.addDays(7);
                $scope.newReport.submitDate = $scope.newReport.endDate;
            }
            
            $scope.goNewReport = function () {
                $scope.data.selectedIndex = '1';
            }

            $scope.data = {
                selectedIndex:'1'
            }

            $scope.onlyThursdayPredicate = function(date) {
                var day = date.getDay();
                return day === 4;
            };

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