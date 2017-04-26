(function () {
    angular.module('myApp',['ngMaterial','ngMessages','md.data.table'])
        .config(function($mdDateLocaleProvider) {
            $mdDateLocaleProvider.formatDate = function(date) {
                return date?moment(date).format('YYYY-MM-DD'):'';
            };
        })
        .controller('MainController',['$scope','$http','$mdToast',function($scope, $http, $mdToast) {
            $scope.userReport = {
                task:'1) 是是是  \n 2) 啥啥啥'
            }
            var apiUrl = window.location.href;
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
                committer:'',
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
                var startDate = moment($scope.newReport.startDate).format('YYYY-MM-DD');
                $http.get(apiUrl+"/commit?userId="+$scope.newReport.reporter+"&startDate="+startDate)
                    .then(function (resp) {
                    $scope.newReport.summary = resp.data.map(function (d) {
                        return d.message;
                    }).join('\n');
                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('查询成功!')
                                .position('top right')
                                .hideDelay(3000));
                },function (err) {
                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('查询失败!'+err.data.message)
                                .position('top right')
                                .hideDelay(3000));
                })
            }

            $scope.generateReport = function () {
                var items = $scope.newReport.summary.split("\n");
                items = items.map(function (val,index) {
                    return {number:index+1,content:val}
                })
                var data = {
                    beginDay:moment($scope.newReport.startDate).format('YYYY-MM-DD'),
                    endDay:moment($scope.newReport.endDate).format('YYYY-MM-DD'),
                    committer:$scope.newReport.committer,
                    list:items
                }
                $http.post(apiUrl+'/commit/generateReport',data).then(function(rep) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('生成周报成功!')
                            .position('top right')
                            .hideDelay(3000)
                    );
                },function (err) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('生成周报失败!'+err.data.message)
                            .position('top right')
                            .hideDelay(3000));
                })
            }


        }]);
})(angular);