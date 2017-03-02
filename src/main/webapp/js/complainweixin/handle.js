var lengthCoast = "";
var riverlength = "";
var streetName = "";
var leftRigth = "";
var partName = "";
var rivervalue = "";//投诉受理河道值
var lrvalue = "";//受理投诉左右岸值
var reachvalue = "";//受理投诉河段值
var areapersonname = "";//区级河长
var streetpersonname = "";//街镇河长
var villagepersonname = "";//村级河长
$(function() {  
	var menu = parent.window.document.getElementById("menu1").value;
	var role = $("#role").val();
	if(menu == "35"){//完结
		$("#feedbackQuestion").attr("style","display:none;");
		$("#endQuestion").attr("style","display:none;");
		$("#saveQuestion").attr("style","display:none;");
		$("#answerQuestion").attr("style","display:none;");
		$("#wxQuestion").attr("style","display:none;");
	}else if(menu == "0"){//已回复0
		$("#saveQuestion").attr("style","display:none;");
		if(role =="streetRole" && role =="villageRole"){
			$("#wxQuestion").attr("style","display:none;");
			$("#endQuestion").attr("style","display:none;");
		}
	}else if(menu == "2"){//已受理
		$("#endQuestion").attr("style","display:none;");
		if(role == "streetRole" || role == "villageRole"){
			$("#endQuestion").attr("style","display:none;");
		}
		if(role =="streetRole" || role =="villageRole" || role =="areaRole"){
			$("#wxQuestion").hide();
		}
		if(role == "streetRole"){
			var qId = $("#id1").val();
			$.ajax({
				type : "POST",
				url : "../complain/getIscross",
				data :  {qId:qId}, 
				success : function(flag) {
					if(flag == "succ"){
						$("#feedbackQuestion").attr("style","display:none;");
						$("#endQuestion").attr("style","display:none;");
						$("#saveQuestion").attr("style","display:none;");
						$("#answerQuestion").attr("style","display:none;");
						$("#wxQuestion").attr("style","display:none;");
					}
				},
				error:function(e){
				}
			});
		}
	}else{
		$("#endQuestion").attr("style","display:none;");
	}
	
	//问题类型
	$.ajax({
		type : "POST",
		url : "../waterwx/getquestiontype",
		data :  {}, 
		dataType : "json",
		async:false,
		success : function(data) {
			$("#qtype").empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#qtype").append("<option >"+value.detail+"</option>");
				$("#selecttype11").append("<option >"+value.detail+"</option>");
			});

		}

	});
	//保存选择的问题类型
	$("#selecttype11").on("change",  function (obj){
		//var type = obj.text;
		$("#questiontype1").val($("#selecttype11").val());
	});

	var length="";
	var id = $("#id1").val();
	tssl(id);
	//初始化页面加载地区下拉框
	loadArea();
	//riverClick("","river");
	/*$("#river1").on("click",function(e){
		$("#river1").attr("style","display:none");
	});
	$("#coast1").on("click",function(e){
		$("#coast1").attr("style","display:none");
	});
	$("#reach1").on("click",function(e){
		$("#reach1").attr("style","display:none");
	});*/
	$("#saveQuestion").on("click",function(){
		saveQuestion();
	});
	$("#endQuestion").on("click",function(){
		end();
	});
});
//完结功能
function end(){
	var qId = $("#id1").val();
	$.ajax({
		type : 'post',
		data : {qId:qId},
		url : "../waterwx/endQuestion",
		success : function(data) {
			if(data == "succ"){
				$.alert("操作成功！");
				window.location.reload();
			}else{
				$.alert("操作失败");
			}

		},
		error:function(e){
			alert("error")	;
		}
	}); 
}
//投诉受理
function tssl(qId){
	$("#answerlimitTime").datetimePicker();
	$.ajax({
		type : 'post',
		data : {'qId':qId},
		url : "../waterwx/getQuestion",
		success : function(data) {
			$("#code1").val(data.code);
			$("#selecttype11").val(data.questiontype);
			$("#questioncontent1").val(data.questioncontent);
			$("#area1").val(data.area);
			$("#phone1").val(data.phone);
			$("#questionposition").val(data.questionposition);
			var reachname = data.reachname;
			$("#id1").val(data.id);
			rivervalue = data.reachname;
			lrvalue = data.leftRight;
			reachvalue = data.partName;
			areapersonname = data.areaPersonName;
			streetpersonname = data.streetPersonName;
			villagepersonname = data.villagePersonName;
			var attchIds = data.attachIds;
			var area = $("#area1").val();
			$("#river1").val(rivervalue);
			$("#coast1").val(lrvalue);
			$("#reach1").val(reachvalue);
			if(area != "请选择" && area != "" && area != null){
				loadRiver(area,"river1");
				if(rivervalue != "请选择" && rivervalue !="" && rivervalue !=null){
					loadCoastAndReach(area,rivervalue,"coast1");
				}
				if(streetpersonname != "请选择" && streetpersonname !="" && streetpersonname !=null){
					getStreetPersonName(area,rivervalue);
				}
				if(villagepersonname != "请选择" && villagepersonname !="" && villagepersonname !=null){
					getVillagePerson1(area,rivervalue);
				}
			}
			$("#area1").on("change",function(){
				var areaValue = $("#area1").val();
				$("#river1").val("");
				
				loadRiver(areaValue,"river1");
				//清空左右岸以及河段input框
				$("#river1").val("");
				$("#coast1").val("");
				$("#reach1").val("");
				$("#streetpersonname").val("");
				$("#villagepersonname").val("");
			});
			$("#river1").on("click",function(){
				var area = $("#area1").val();
				loadRiver(area);
			});
			$("#coast1").on("click",function(){
				var area = $("#area1").val();
				var river = $("#river1").val();
				loadCoastAndReach(area,river,"coast1");
			});
			$("#reach1").on("click",function(){
				var area = $("#area1").val();
				var river = $("#river1").val();
				var coast = $("#coast1").val();
				loadReach(area,river,coast,"reach1");
			});
			$("#streetpersonname").on("click",function(){
				var area = $("#area1").val();
				var river = $("#river1").val();
				 getStreetPersonName(area,river);
			});
			$("#villagepersonname").on("click",function(){
				var area = $("#area1").val();
				var river = $("#river1").val();
				var reach = $("#reach1").val();
				 getVillagePerson1(area,river);
			});
		}
	}); 
}
//保存投诉受理
function saveQuestion(){
	var content = $("#questioncontent1").val();
	var type = $("#questiontype1").val();
	var river2 = $("#river1").val();
	var coast2 = $("#coast1").val();
	var reach2 = $("#reach1").val();
	var area2 = $("#area1").val();
	var id = $("#id1").val();
	var areaname = $("#areapersonname").val();
	var streetname = $("#streetpersonname").val();
	var villagename = $("#villagepersonname").val();
	var position = $("#questionposition").val();
	var limitTime = $("#answerlimitTime").val();
	$.ajax({
		type : 'post',
		data : {'type':type,'content':content,"id":id,'river2':river2,'coast2':coast2,'reach2':reach2,'area2':area2,'position':position,'areaname':areaname,'streetname':streetname,'villagename':villagename,'limitTime':limitTime},
		url : "../complain/saveQuestion",
		success : function(data) {
			if(data == "succ"){
				$.alert("保存成功！");
				$('#collect_detail_ret', window.parent.document).click();
				//window.location.reload();
			}else{
				$.alert("保存失败");
			}

		},
		error:function(e){
			$.alert("error");
		}
	}); 
}
//点击地区下拉框时 obj 区分是搜索框，还是投诉受理页面
function loadArea(){
	//地区
	$.ajax({
		type : "POST",
		url : "../waterwx/findAllArea",
		data :  {}, 
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, value){
				$("#area").append("<option class='areaOption'  >"+value+"</option>");
				$("#area1").append("<option class='areaOption'  >"+value+"</option>");
			});

		}

	});
}
//加载河道信息 area:地区信息
function loadRiver(area,obj){
	var requestData = "";
	var url = "";
	if(area == null || area == "" || area == "请选择"){
		requestData = "";
		url = "../waterwx/getRiver";
	}else{//根据地区获取河道
		requestData = {area:area};
		url = "../waterwx/getRiverByArea";
	}
	if("river1" == obj){
		//获取区级河长姓名
		getAreaPersonName(area);
	}
	//获取所有河道
	$.ajax({
		type : "POST",
		url :url,
		data : requestData, 
		dataType : "json",
		success : function(data) {
			//创建河道下拉框
			/*$("#"+obj).empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#"+obj).append("<option class='riverOption' >"+value.riverName+"</option>");
			});*/
			//投诉受理页面 赋河道值
			/*if(rivervalue !=""){
				$("#river1").val(rivervalue);
			}*/
			//$("#"+obj).comboSelect();
			$( "#river1" ).autocomplete({
			  source: function( request, response ) {
				  var term = request.term;
		          $.getJSON( "../waterwx/getRiverByArea?area="+area, function( data, status, xhr ) {
		        	  if(status === "success"){
		        		  var rivers = [];
		                  for(var i=0; i<data.length; i++){
		                	  rivers.push(data[i].riverName);
		                	  console.log(data[i].riverName);
		                  }
		                  response( rivers );
		        	  }
		              
		            });
		          }
			}); 
			getStreetPersonName(area,river);
		}

	});
}
//左右岸下拉框
function loadCoastAndReach(area,river,obj){
	var requestData = {};
	if(area == null || area == "" || area == "请选择"){
		requestData = {rivername:river};
	}else{
		requestData = {area:area,rivername:river};
	}
	var reach = "";
	if(obj == "coast1"){
		reach = "reach1";
		//获取区级河长姓名
		
	}
	if(obj == "coast"){
		reach = "reach";
	}
	/*if(lrvalue != null && lrvalue !=""){
		$("#coast1").val(lrvalue);
	}
	if(reachvalue != null && reachvalue !=""){
		$("#reach1").val(reachvalue);
	}*/
	$( "#coast1" ).autocomplete({
		  source: function( request, response ) {
			  var term = request.term;
	          $.getJSON( "../waterwx/getResponseByTwo?area="+area+"&rivername="+river, function( data, status, xhr ) {
	        	  if(status === "success"){
	        		  var rivers = [];
	                  for(var i=0; i<data.length; i++){
	                	  rivers.push(data[i].leftRight);
	                	  console.log(data[i].leftRight);
	                  }
	                  response( rivers );
	        	  }
	              
	            });
	          }
	});
	$( "#reach1" ).autocomplete({
		  source: function( request, response ) {
			  var term = request.term;
	          $.getJSON( "../waterwx/getResponseByTwo?area="+area+"&rivername="+river, function( data, status, xhr ) {
	        	  if(status === "success"){
	        		  var rivers = [];
	                  for(var i=0; i<data.length; i++){
	                	  rivers.push(data[i].partName);
	                	  console.log(data[i].partName);
	                  }
	                  response( rivers );
	        	  }
	              
	            });
	          }
	});
	//投诉受理页面选择一个河段时
		/*$.ajax({
			type : 'post',
			data : requestData,
			dataType : "json",
			url : "../waterwx/getResponseByTwo",
			success : function(data) {
				$("#"+obj).empty();
				$("#"+obj).append("<option>请选择</option>");
				$("#"+reach).empty();
				$("#"+reach).append("<option>请选择</option>");
				$.each(data, function(i, value){
					$("#"+obj).append("<option  class='river' >"+value.leftRight+"</option>");
				});
				for(var i=0;i<data.length;i++){
					for (var j = data.length - 1 ; j > i; j--){
						var partname = data[i].partName;
						var partname1 = data[j].partName;
						if (partname == partname1){
							data.splice(j,1);
						}
					}
					$("#"+reach).append("<option >"+data[i].partName+"</option>");
				}
				if(lrvalue != null && lrvalue !=""){
					$("#coast1").val(lrvalue);
				}
				if(reachvalue != null && reachvalue !=""){
					$("#reach1").val(reachvalue);
				}
				$("#"+reach).comboSelect();	
				$("#"+obj).comboSelect();	
			}
		}); */
}
//改变左右岸，重新加载河段
function loadReach(area,river,coast,obj){
	if(obj == "reach1"){
		 getVillagePerson(area,river,coast,"");
	}
	/*$.ajax({
		type : "POST",
		url : "../waterwx/getResponseByLf",
		data :  {area:area,rivername:river,lr:coast}, 
		dataType : "json",
		async:false,
		success : function(data) {
			$("#"+obj).empty();
			$("#"+obj).append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#"+obj).append("<option >"+value.partName+"</option>");
			});
			$("#"+obj).comboSelect();				
		}

	});*/
	$( "#reach1" ).autocomplete({
		  source: function( request, response ) {
			  var term = request.term;
	          $.getJSON( "../waterwx/getResponseByLf?area="+area+"&rivername="+river+"&lr="+coast, function( data, status, xhr ) {
	        	  if(status === "success"){
	        		  var rivers = [];
	                  for(var i=0; i<data.length; i++){
	                	  rivers.push(data[i].partName);
	                	  console.log(data[i].partName);
	                  }
	                  response( rivers );
	        	  }
	              
	            });
	          }
	});
}
//获取区级河长姓名
function getAreaPersonName(area){
	$.ajax({
		type : "POST",
		url : "../waterwx/getPersonName",
		data :  {area:area}, 
		dataType : "json",
		success : function(data) {
			$("#areapersonname").empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#areapersonname").append("<option  class='riverOption1' >"+value+"</option>");

			});
			$("#areapersonname").val(areapersonname);
			//加载区级河长电话
			$("#areapersonname").on("change",function(){
				var areaname = $("#areapersonname").val();
				$.ajax({
					type : "POST",
					url : "../waterwx/getTelByAreaName",
					data :  {areaname:areaname}, 
					dataType : "json",
					success : function(data) {
						$("#areaPhone").empty().append("<option>请选择</option>");
						var phone = "";
						$.each(data, function(i, value){
							phone +=value.distMgrTel+",";
						});
						$("#areaPhone").val(phone);
					}
				});
			}); 
		}

	});
}
//加载街镇河长姓名
function getStreetPersonName(area,river){
	$.ajax({
		type : "POST",
		url : "../waterwx/getPersonName",
		data :  {area:area,rivername:river}, 
		dataType : "json",
		success : function(data) {
			$("#streetpersonname").empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#streetpersonname").append("<option  class='riverOption1' >"+value+"</option>");

			});
			$("#streetpersonname").val(streetpersonname);
			$("#streetpersonname").on("change",function(){
				var streetname = $("#streetpersonname").val();
				$.ajax({
					type : "POST",
					url : "../waterwx/getTelByStreetName",
					data :  {streetName:streetname}, 
					dataType : "json",
					success : function(data) {
						$("#streetPhone").empty().append("<option>请选择</option>");
						var streetphone = "";
						$.each(data, function(i, value){
							streetphone +=value.streetMgrTel+",";
						});
						$("#streetPhone").val(streetphone);
					}
				}); 
			});
		}		
	});
}
//加载村级河长信息
function getVillagePerson(area,river,coast,reach){
	var requestData = {};
	if(coast == null || coast == "" || coast == "请选择"){
		requestData = {area:area,rivername:river,reach:reach};
	}else{
		requestData = {area:area,rivername:river,lr:coast};
	}
	$.ajax({
		type : "POST",
		url : "../waterwx/getPersonName",
		data : requestData, 
		dataType : "json",
		success : function(data) {
			$("#villagepersonname").empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#villagepersonname").append("<option  class='riverOption1' >"+value+"</option>");

			});
			$("#villagepersonname").val(villagepersonname);
			//加载村级河长电话
			$("#villagepersonname").on("change",function(){
				var villagename = $("#villagepersonname").val();
				$.ajax({
					type : "POST",
					url : "../waterwx/getTelByVillageName",
					data :  {villageName:villagename}, 
					dataType : "json",
					success : function(data) {
						$("#villagePhone").empty().append("<option>请选择</option>");
						var villagephone = "";
						$.each(data, function(i, value){
							villagephone +=value.villageMgrTel+",";
						});
						$("#villagePhone").val(villagephone);
					}
				}); 
			});
		}

	});
}
//加载村级河长信息
function getVillagePerson1(area,river){
	var requestData = {};
		requestData = {area:area,rivername:river};
	$.ajax({
		type : "POST",
		url : "../waterwx/getVillagePersonName",
		data : requestData, 
		dataType : "json",
		success : function(data) {
			$("#villagepersonname").empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				$("#villagepersonname").append("<option  class='riverOption1' >"+value+"</option>");

			});
			$("#villagepersonname").val(villagepersonname);
			//加载村级河长电话
			$("#villagepersonname").on("change",function(){
				var villagename = $("#villagepersonname").val();
				$.ajax({
					type : "POST",
					url : "../waterwx/getTelByVillageName",
					data :  {villageName:villagename}, 
					dataType : "json",
					success : function(data) {
						$("#villagePhone").empty().append("<option>请选择</option>");
						var villagephone = "";
						$.each(data, function(i, value){
							villagephone +=value.villageMgrTel+",";
						});
						$("#villagePhone").val(villagephone);
					}
				}); 
			});
		}

	});
}