var ENTITY_MANDALA='mandala';

$(function () {
	function validateForm (e) {
		e.preventDefault();
		//Validate form
		
		submitForm();
		return false;
	}
	
	//parameter object definition
	var param=function(name,value){
		this.name=name;
		this.value=value;
	}
	
	function submitForm () {
		// creating the data object to be sent to backend
		var data=new Array();
		// collecting the field values from the form
		var formEleList = $('#create').serializeArray();
		
		for(var i=0;i<formEleList.length;i++){
			data[data.length]=new param(formEleList[i].name,formEleList[i].value);
		}
		//setting action as PUT
		data[data.length]=new param('action','PUT');
		//making the ajax call
		$.ajax({
			url : "/"+ENTITY_MANDALA,
			type : "POST",
			data:data,
			success : function(data) {
				alert("SUCCESS");
			}
		});
		return false;
	}
	
	
	$("#submit-info").click(validateForm);
	$("#create").submit(validateForm);
});