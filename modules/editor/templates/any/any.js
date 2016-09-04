angular.module('streamPipesApp')
    .directive('any', function ($interval) {
        return {
            restrict : 'E',
            templateUrl : 'modules/editor/templates/any/any.tmpl.html',
            scope : {
                staticProperty : "="
            },
            link: function ($scope) {

                $scope.toggle = function(option, options) {
                    console.log(option.name);
                    angular.forEach(options, function(o) {
                        if (o.elementId === option.elementId) {
                            o.selected = !o.selected;
                        }
                    });
                }

                $scope.exists = function(option, options) {
                   angular.forEach(options, function(o) {
                        if (o.elementId === option.elementId) {
                            return option.selected;
                        }
                    });
                }

                $scope.staticProperty.validator = function() {
                    console.log("validate");
                    return selected($scope.staticProperty.properties.options);
                }

                var selected = function(options) {
                    angular.forEach(options, function(o) {
                        if (o.selected) return true;
                    });
                    return false;
                }

            }
        }

    });