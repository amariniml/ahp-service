function addcriteria(){
	var criteria_number = ($('#criteria-area #criteria').length+1);

	if(criteria_number<30){
		var element = '<div class="col-lg-10 mt-2"><input type="text" id="criteria" name="criteria" value="'+criteria_number+'- " placeholder="Criteria '+criteria_number+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+criteria_number+')" class="btn btn-primary"> <span class="icon-plus"></span></a> <a onclick="removeSubCriteria('+criteria_number+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+criteria_number+'" class="subcriteria d-none mt-4 mb-4"></div>';
		$("#criteria-area").append(element);
	}
	else{
		alert("You reach the max number of criteria, let consider reformulate the tree for consistant results!")
	}

}

function removeSubCriteria(c1,c2,c3,c4,c5){
	var c2 = c2 || false;
	var c3 = c3 || false;
	var c4 = c4 || false;
	var c5 = c5 || false;
	if(!c2){
		var sub_criteria_number = $('#subc-div-'+c1+' #subcriteria-'+c1).length;
		if(sub_criteria_number>2){
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
		}
		else if(sub_criteria_number==2){
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).children().last().remove();
			$('#subc-div-'+c1).addClass('d-none')
		}
	}
	else if(!c3){
		var sub_criteria_number = $('#subc-div-'+c1+'-'+c2+' #subcriteria-'+c1+'-'+c2).length;
		if(sub_criteria_number>2){
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
		}
		else if(sub_criteria_number==2){
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).children().last().remove();
			$('#subc-div-'+c1+'-'+c2).addClass('d-none')
		}
	}
	else if(!c4){
		var sub_criteria_number = $('#subc-div-'+c1+'-'+c2+'-'+c3+' #subcriteria-'+c1+'-'+c2+'-'+c3).length;
		if(sub_criteria_number>2){
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
		}
		else if(sub_criteria_number==2){
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3).addClass('d-none')
		}
	}
	else if(!c5){
		var sub_criteria_number = $('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4+' #subcriteria-'+c1+'-'+c2+'-'+c3+'-'+c4).length;
		if(sub_criteria_number>2){
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).children().last().remove();
		}
		else if(sub_criteria_number==2){
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).children().last().remove();
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).addClass('d-none')
		}
	}
}

function addSubCriteria(c1,c2,c3,c4,c5){
	var c2 = c2 || false;
	var c3 = c3 || false;
	var c4 = c4 || false;
	var c5 = c5 || false;
	if(!c2){
		var sub_criteria_number = $('#subc-div-'+c1+' #subcriteria-'+c1).length;
		console.log(sub_criteria_number)
		if(sub_criteria_number == 0){
			$('#subc-div-'+c1).removeClass('d-none');
			$('#subc-div-'+c1).append('<div class="col-lg-10 mt-4"><input type="text" id="subcriteria-'+c1+'" name="criteria" value="'+c1+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+(sub_criteria_number+1)+'"></div><div class="col-lg-2 mt-4"><a onclick="addSubCriteria('+c1+','+(sub_criteria_number+1)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+(sub_criteria_number+1)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+(sub_criteria_number+1)+'" class="subcriteria d-none mt-4 mb-4"></div>');
			$('#subc-div-'+c1).append('<div class="col-lg-10 mt-2"><input type="text" id="subcriteria-'+c1+'" name="criteria" value="'+c1+'.'+(sub_criteria_number+2)+'- " placeholder="Criteria '+c1+'.'+(sub_criteria_number+2)+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+c1+','+(sub_criteria_number+2)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+(sub_criteria_number+2)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+(sub_criteria_number+2)+'" class="subcriteria d-none mt-4 mb-4"></div>');
		}
		else{
			$('#subc-div-'+c1).append('<div class="col-lg-10 mt-2"><input type="text" id="subcriteria-'+c1+'" name="criteria" value="'+c1+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+(sub_criteria_number+1)+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+c1+','+(sub_criteria_number+1)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+(sub_criteria_number+1)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+(sub_criteria_number+1)+'" class="subcriteria d-none mt-4 mb-4"></div>');
		}
	}
	else if(!c3){
		var sub_criteria_number = $('#subc-div-'+c1+'-'+c2+' #subcriteria-'+c1+'-'+c2).length;
		if(sub_criteria_number == 0){
			$('#subc-div-'+c1+'-'+c2).removeClass('d-none');
			$('#subc-div-'+c1+'-'+c2).append('<div class="col-lg-10 mt-4"><input type="text" id="subcriteria-'+c1+'-'+c2+'" name="criteria" value="'+c1+'.'+c2+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+(sub_criteria_number+1)+'"></div><div class="col-lg-2 mt-4"><a onclick="addSubCriteria('+c1+','+c2+','+(sub_criteria_number+1)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+c2+','+(sub_criteria_number+1)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+c2+'-'+(sub_criteria_number+1)+'" class="subcriteria d-none mt-4 mb-4"></div>');
			$('#subc-div-'+c1+'-'+c2).append('<div class="col-lg-10 mt-2"><input type="text" id="subcriteria-'+c1+'-'+c2+'" name="criteria" value="'+c1+'.'+c2+'.'+(sub_criteria_number+2)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+(sub_criteria_number+2)+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+c1+','+c2+','+(sub_criteria_number+2)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+c2+','+(sub_criteria_number+2)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+c2+'-'+(sub_criteria_number+2)+'" class="subcriteria d-none mt-4 mb-4"></div>');
		}
		else{
			$('#subc-div-'+c1+'-'+c2).append('<div class="col-lg-10 mt-2"><input type="text" id="subcriteria-'+c1+'-'+c2+'" name="criteria" value="'+c1+'.'+c2+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+(sub_criteria_number+1)+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+c1+','+c2+','+(sub_criteria_number+1)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+c2+','+(sub_criteria_number+1)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+c2+'-'+(sub_criteria_number+1)+'" class="subcriteria d-none mt-4 mb-4"></div>');
		}
	}
	else if(!c4){
		var sub_criteria_number = $('#subc-div-'+c1+'-'+c2+'-'+c3+' #subcriteria-'+c1+'-'+c2+'-'+c3).length;
		if(sub_criteria_number == 0){
			$('#subc-div-'+c1+'-'+c2+'-'+c3).removeClass('d-none');
			$('#subc-div-'+c1+'-'+c2+'-'+c3).append('<div class="col-lg-10 mt-4"><input type="text" id="subcriteria-'+c1+'-'+c2+'-'+c3+'" name="criteria" value="'+c1+'.'+c2+'.'+c3+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+c3+'.'+(sub_criteria_number+1)+'"></div><div class="col-lg-2 mt-4"><a onclick="addSubCriteria('+c1+','+c2+','+c3+','+(sub_criteria_number+1)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+c2+','+c3+','+(sub_criteria_number+1)+')" class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+c2+'-'+c3+'-'+(sub_criteria_number+1)+'" class="subcriteria d-none mt-4 mb-4"></div>');
			$('#subc-div-'+c1+'-'+c2+'-'+c3).append('<div class="col-lg-10 mt-2"><input type="text" id="subcriteria-'+c1+'-'+c2+'-'+c3+'" name="criteria" value="'+c1+'.'+c2+'.'+c3+'.'+(sub_criteria_number+2)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+c3+'.'+(sub_criteria_number+2)+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+c1+','+c2+','+c3+','+(sub_criteria_number+2)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+c2+','+c3+','+(sub_criteria_number+2)+')"  class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+c2+'-'+c3+'-'+(sub_criteria_number+2)+'" class="subcriteria d-none mt-4 mb-4"></div>');
		}
		else{
			$('#subc-div-'+c1+'-'+c2+'-'+c3).append('<div class="col-lg-10 mt-2"><input type="text" id="subcriteria-'+c1+'-'+c2+'-'+c3+'" name="criteria" value="'+c1+'.'+c2+'.'+c3+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+c3+'.'+(sub_criteria_number+1)+'"></div><div class="col-lg-2 mt-2"><a onclick="addSubCriteria('+c1+','+c2+','+c3+','+(sub_criteria_number+1)+')" class="btn btn-primary"><span class="icon-plus"></span></a><a onclick="removeSubCriteria('+c1+','+c2+','+c3+','+(sub_criteria_number+1)+')"  class="btn btn-danger"><span class="icon-minus"></span></a></div><div id="subc-div-'+c1+'-'+c2+'-'+c3+'-'+(sub_criteria_number+1)+'" class="subcriteria d-none mt-4 mb-4"></div>');
		}
	}
	else if(!c5){
		var sub_criteria_number = $('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4+' #subcriteria-'+c1+'-'+c2+'-'+c3+'-'+c4).length;
		if(sub_criteria_number == 0){
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).removeClass('d-none');
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).append('<div class="col-lg-12 mt-4"><input type="text" id="subcriteria-'+c1+'-'+c2+'-'+c3+'-'+c4+'" name="criteria" value="'+c1+'.'+c2+'.'+c3+'.'+c4+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+c3+'.'+c4+'.'+(sub_criteria_number+1)+'"></div>');
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).append('<div class="col-lg-12 mt-2"><input type="text" id="subcriteria-'+c1+'-'+c2+'-'+c3+'-'+c4+'" name="criteria" value="'+c1+'.'+c2+'.'+c3+'.'+c4+'.'+(sub_criteria_number+2)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+c3+'.'+c4+'.'+(sub_criteria_number+2)+'"></div>');
		}
		else{
			$('#subc-div-'+c1+'-'+c2+'-'+c3+'-'+c4).append('<div class="col-lg-12 mt-2"><input type="text" id="subcriteria-'+c1+'-'+c2+'-'+c3+'-'+c4+'" name="criteria" value="'+c1+'.'+c2+'.'+c3+'.'+c4+'.'+(sub_criteria_number+1)+'- " placeholder="Criteria '+c1+'.'+c2+'.'+c3+'.'+c4+'.'+(sub_criteria_number+1)+'"></div>');
		}
	}
}


function removecriteria(){
	var criteria_number = $('#criteria-area #criteria').length;
	if(criteria_number>2){
		$("#criteria-area").children().last().remove();
		$("#criteria-area").children().last().remove();
	}
	else{
		alert("You can't delete because at least 2 elements are needeed for the proccess.")
	}
}

function addalternative(){
	var alternative_number = ($('#alternative-area #alternative').length+1);

	if(alternative_number<10){
		var element = '<div class="col-lg-12 mt-2"><input type="text" id="alternative" name="alternative" placeholder="Enter the alternative n°'+alternative_number+' for start the evaluation..."></div>';
		$("#alternative-area").append(element);
		// $("#criteria-area").append('<p>holaaa</p>');
	}
	else{
		alert("You reach the max number of alternatives, let consider reformulate the tree for consistant results!")
	}

}

function removealternative(){
	var alternative_number = $('#alternative-area #alternative').length;
	if(alternative_number>2){
		$("#alternative-area").children().last().remove();
	}
	else{
		alert("You can't delete because at least 2 alternatives are needeed for comparasion.")
	}
}


function makeSomeMagic(row,column,criteria){
	criteria = criteria || 0;

	if(criteria==0){
		var transposeElem = "#c"+column+row;
		var elem = "#c"+row+column;
	}
	else{
		var transposeElem = "#"+criteria+column+row;
		var elem = "#"+criteria+row+column;
	}

	var value = $(elem).val();

	if(value>9 || value==0){
		$(elem).val('');
		$(transposeElem).val('');
	}
	else if(value!=0){
		$(transposeElem).val(1/value);
	}
}


function bar_mod(number,title){
	$('#progress_bar').animate({
		width: number
	},3000);
	$('#footer-title').html(title);
}

function checkIdentification(){

}
