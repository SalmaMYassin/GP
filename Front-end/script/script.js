	var app = angular.module('myApp', []);
	app.controller('myCtrl', function ($scope, $http) {
		$scope.addStudent= function(){
			var dataObj = {
				username: $scope.username,
				password: $scope.password,
				email: $scope.email,
				type: "student"
			}
			$http.post('http://localhost:8080/lecture/app/accounts/signup/', dataObj)
			.then(function (response) {
				window.location.href = "file:///G:/E-Learning/Year%203/GraduationProjectFiles/GP/Front-end/studentView.html";
			})
			


		}
		$scope.addLecturer= function(){
			var dataObj = {
				username: $scope.username,
				password: $scope.password,
				email: $scope.email,
				type: "lecturer"
			}
			$http.post('http://localhost:8080/lecture/app/accounts/signup/', dataObj)
			.then(function (response) {
				window.location.href = "file:///G:/E-Learning/Year%203/GraduationProjectFiles/GP/Front-end/lecturerView.html";
			})
			
		}
		$scope.authenticate= function(){
			var dataObj = {
				username: $scope.username,
				password: $scope.password
			}
			$http.post('http://localhost:8080/lecture/app/accounts/signin/', dataObj)
			.then(function (response) {
				$scope.all();
			})
		}

		$scope.all = function () {
			$http.get("http://localhost:8080/lecture/app/accounts/all/")
			.then(function (response) {
				$scope.items = response.data;
			})
		}
// 	$scope.student = "student"
// $scope.lecturer ="lecturer"

// 	$scope.addLecturer= function(){
// 		var dataObj = {
// 			username: $scope.username
// 			email: $scope.email
// 			password: $scope.password
// 			type: "lecturer"
// 		}
// 		$http.post('http://localhost:8080/lecture/app/accounts/signup/', dataObj)
// 		.then(function (response) {
// 			$scope.all();
// 		})


// 	}

// $scope.addStudent= function(){
// 	var dataObj = {
// 		username: $scope.username
// 		email: $scope.email
// 		password: $scope.password
// 		type: $scope.student
// 	}
// 	$http.post('http://localhost:8080/lecture/app/accounts/signup/', dataObj)
// }

	// $scope.addUser = function () {
	// 	if($scope.password === $scope.confirmPassword){
	// 		 var dataObj = {
	//             username: $scope.username
	//             email: $scope.email
	//             password: $scope.password
	//         }
	//         $http.post('http://localhost:8080/anotherOne/app/tasks/add', dataObj)
	//             .then(function (response) {
	//                 $scope.all();
	//             })
	//         $('#id').val('');
	// 	}
	// 	else{


	//     }
	// 	}



});
// $('#password, #confirmPassword').on('keyup', function () {
//   if ($('#password').val() == $('#confirmPassword').val()) {
//     $('#message').html('Matching').css('color', 'green');
//   } else 
//     $('#message').html('Not Matching').css('color', 'red');
// });