$(document).ready(function(){ 
listarProducto();
listarCat(0)
limpiar();
$("#correo").numeric();
jQuery('#telefono').keyup(function () { this.value = this.value.replace(/[^0-9\.]/g,''); });

});
$("#boton").click(function(){
	var esc= $("#esc").val();	
	var apellnombres= $("#apellnombres").val();
	var correo= $("#correo").val();
	var telefono = $("#telefono").val();
	var id = $("#id").val();
	if(id==0){
		$.post( "ac", {esc :esc,apellnombres:apellnombres,correo:correo,telefono:telefono, opc:3}).done(function(data){
			limpiar();
			listarCat(0)
			listarProducto();
			});
	}else{
		bootbox.confirm("Desea Modificar?", function(result) {
		if(result){
		 	bootbox.alert("Registro Modificado Correctamente...!", function() {		
			$.post( "ac", {idesc :esc, apellnombres:prapellnombres, correo:correo, telefono:telefono, ida:id, opc:6}).done(function(data){
				$("#id").val(0);
				limpiar();
				listarCat(0)
				listarProducto();			
			});
		 	});
		}else{
	    	bootbox.alert("El registro no se Modifico...!");
	    	limpiar();
			listarCat(0)
			listarProducto();
	    }});		
	}
});
function listarCat(x){
	var i, c =1;
	$("#esc").empty().append('<option selected="selected" value="test">Seleccionar Escuela</option>')
		$.get("ac", {opc : "1"}, 
		function(data) {
		var d = JSON.parse(data);
		for (i = 0; i < d.length; i++) {
			if (x == d[i].idescuela) {
				$("#esc").append(
						"<option selected='selected' value='" + d[i].idescuela + "'>"
								+ d[i].nombre + "</option>");
			} else {
				$("#esc").append(
						"<option value='" + d[i].idescuela + "'>"
								+ d[i].nombre + "</option>");
			}
		}
	});	
}

function listarProducto(){
	var i, c =1;
	$.get("ac",{opc:"2"},function(data){	
		var d = JSON.parse(data);
		$('#tablita tbody').empty();
		for(i=0;i<d.length;i++){
			$("#tablita tbody").append("<tr><td style='color:blue'>"+c+"</td><td>"+d[i].nom_escuela+"</td><td>"+d[i].apellnombres+"</td><td>"+d[i].correo+"</td><td>"+d[i].telefono+
			"</td><td><a href='#' style='color:green' onclick='modificar("+d[i].idalumno+")'><i class='far fa-edit'></i></a></td><td><a href='#' style='color:red' onclick='eliminar("+d[i].idalumno+")'><i class='far fa-trash-alt'></i></a></td></tr>")
			c++;
		}
	});
}

function eliminar(id){	
	bootbox.confirm("Desea Eliminar?", function(result) {
    if(result){
    	bootbox.alert("Registro Eliminado Correctamente...!", function() {
    		$.get("ac",{id:id,opc:5},function(data){
     			limpiar();
    			listarCat(0);
    			listarProducto();
		});
    	});
		 
    }else{
    	bootbox.alert("El registro no se Elimino...!")
    }});
}
function modificar(id){	
	$.post("ac",{id:id,opc:4},function(data){
		var x = JSON.parse(data);
		$("#apellnombres").val(x[0].apellnombres);
		$("#correo").val(x[0].correo);
		$("#telefono").val(x[0].telefono);
		$("#id").val(x[0].idalumno);		
		listarCat(x[0].idescuela);
	});
}
function limpiar(){
	$("#c").val("");
	$("#correo").val("");
	$("#telefono").val("");
	$("#telefono").focus();
}
