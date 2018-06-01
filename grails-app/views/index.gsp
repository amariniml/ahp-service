<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/tether.min.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="icomoon.css">
	<link rel="stylesheet" href="css/style.css">
	<title>AHP version 3.0</title>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="contenedor">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
			<a class="navbar-brand" href="#">AHP TOOL</a>
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active">
					<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Link</a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<footer>
	<div class="contenedor mt-5 mb-5">
		<h1 class="display-4 mb-4" id="footer-title">Step 1: Build the Hierarchy</h1>
		<div class="progress">
			<div class="progress-bar progress-bar-striped" id="progress_bar" role="progressbar" style="width: 33.33%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
	</div>
</footer>

<div id="structure" class="contenedor mt-5 mb-5">
	<h1 class="mb-3">Please, enter the required data for continue</h1>
	<hr>
    <g:form name="myForm" id="form-ahp" url="[action:'createHierarchy',controller:'create']">
	%{--<form action="#" id="form-ahp">--}%

		<div class="col-lg-12">
			<input type="text" id="goal" name="goal" placeholder="Enter the goal of the process here...">
		</div>


		<div class="col-lg-6 mt-5">
			<a onclick="addcriteria()" class="btn btn-block btn-primary">
				<span class="icon-plus"></span> Criteria
			</a>
		</div>
		<div class="col-lg-6 mt-5">
			<a onclick="removecriteria()" class="btn btn-block btn-danger">
				<span class="icon-minus"></span> Criteria
			</a>
		</div>

		<div id="criteria-area">
			<div class="col-lg-10 mt-3">
				<input type="text" onkeydown="checkIdentification();" id="criteria" value="1- " name="criteria" placeholder="Criteria 1">
			</div>
			<div class="col-lg-2 mt-3">
				<a onclick="addSubCriteria(1)" class="btn btn-primary">
					<span class="icon-plus"></span>
				</a>
				<a onclick="removeSubCriteria(1)" class="btn btn-danger">
					<span class="icon-minus"></span>
				</a>
			</div>
			<div id="subc-div-1" class="subcriteria d-none mt-4 mb-4"></div>

			<div class="col-lg-10 mt-2">
				<input type="text" id="criteria" value="2- " name="criteria" placeholder="Criteria 2">
			</div>
			<div class="col-lg-2 mt-2">
				<a onclick="addSubCriteria(2)" class="btn btn-primary">
					<span class="icon-plus"></span>
				</a>
				<a onclick="removeSubCriteria(2)" class="btn btn-danger">
					<span class="icon-minus"></span>
				</a>
			</div>
			<div id="subc-div-2" class="subcriteria d-none mt-4 mb-4"></div>
		</div>

		<div class="col-lg-6 mt-5">
			<a onclick="addalternative()" class="btn btn-block btn-primary">
				<span class="icon-plus"></span> Alternative
			</a>
		</div>
		<div class="col-lg-6 mt-5">
			<a onclick="removealternative()" class="btn btn-block btn-danger">
				<span class="icon-minus"></span> Alternative
			</a>
		</div>

		<div id="alternative-area">
			<div class="col-lg-12 mt-3">
				<input type="text" id="alternative" value="Ford" name="alternative" placeholder="Enter the alternative n°1 for start the evaluation...">
			</div>
			<div class="col-lg-12 mt-2">
				<input type="text" id="alternative" value="Fiat" name="alternative" placeholder="Enter the alternative n°2 for start the evaluation...">
			</div>
			<div class="col-lg-12 mt-2">
				<input type="text" id="alternative" value="Chevrolet" name="alternative" placeholder="Enter the alternative n°3 for start the evaluation...">
			</div>
		</div>





		<button type="submit" class="btn btn-block btn-primary mt-5">Continue</button>
	%{--</form>--}%
    </g:form>
</div>






<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/tether.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/frontfunctionality.js"></script>

</body>
</html>

