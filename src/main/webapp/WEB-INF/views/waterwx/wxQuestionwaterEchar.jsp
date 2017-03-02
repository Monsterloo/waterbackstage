<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
		<title>问题分类统计</title>
        <script src="../assets/jquery-1.12.2.min.js"></script>
    <script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" ></script>
	<script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
	
		<script type="text/javascript" src="../assets/jquery.validate.min.js" ></script>
		<script type="text/javascript" src="${ctx }/js/admin/common.js"></script>
		<link rel="stylesheet" href="../assets/bootstrap/bootstrap.min.css" />
		<script type="text/javascript" src="${ctx }/assets/metisMenu/metisMenu.min.js"></script>
		<script type="text/javascript" src="../assets/echart/echarts.js" ></script>
		<link rel="stylesheet" href="../assets/fontawesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../css/thePage.css" />
    <link rel="stylesheet" href="../css/weixin/weui.min.css"/>
    <link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css"/>
    <link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/assets/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
		<!--[if lt IE 9]>
  		<script type='text/javascript' src="${ctx}/js/respond.min.js"></script>
  		<script type='text/javascript' src="${ctx}/js/html5shiv.min.js"></script>
	<![endif]-->
		<script type="text/javascript">
	// 路径配置
		require.config({
		paths:{ 
				'echarts' : '../assets/echart/dist'
		}
		});
		var questionData = new Array();//所有问题分类
		var areaAll = new Array();//所有地区
		
	 	/* $.ajax({
			type : 'post',
			data : {},
			dataType : "json",
			url : "${ctx}/waterwx/findAllArea",
			success : function(data) {
				$("#questionArea").append("<option onclick='selectArea(this)' class='area' >请选择</option>");
				$.each(data, function(i, value){
					$("#questionArea").append("<option onclick='selectArea(this)' class='area' >"+value+"</option>");
				});
			}
		}); */
		
		
		
			//选择地区重新加载第一图
		 	function selectArea(area){
		 		 require(
		    	            [
		    	                'echarts',
		    	                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		    	            ],
		    	            function (ec) {
		    	                // 基于准备好的dom，初始化echarts图表
		    	                var myChart = ec.init(document.getElementById('leftmain')); 
		    	              // 加载数据  
		    	              var questionData = new Array();
		    	              var startTime ="";
		    	  			  var endTime = "";
		    	              var cou = "";
		    	              var data = "";
		    	              var qType = new Array();//所有问题类型
		    	              
		    	              $.ajax({
		    						type : 'post',
		    						data : {},
		    						async:false,
		    						dataType : "json",
		    						url : "${ctx}/waterwx/findAllTypeByTj",
		    						success : function(data) {
		    							$.each(data, function(i, value){
		    								qType.push(value);
		    							});
		    						}
		    					}); 
		    	              var questionData = new Array();
		    	              var startTime = $('#search_startTime').val();
		    		    	  var endTime = $('#search_endTime').val();
		    	              $.ajax({
		    						type : 'post',
		    						data : {area:area,startTime:startTime,endTime:endTime},
		    						dataType : "json",
		    						async:false,
		    						url : "${ctx}/waterwx/getRiverByAreaToTj",
		    						success : function(data) {
		    							$.each(data, function(i, value){
		    	  							var json = {};
		    	  							json.name=value[0];
		    	  							json.value=value[1];
		    	  							questionData.push(json);
		    							});
		    						}
		    					}); 
		    	              option = {
		    	            	    title : {
		    	            	        text: '地区问题分类统计',
		    	            	        x:'left'
		    	            	    },
		    	            	    tooltip : {
		    	            	        trigger: 'item',
		    	            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    	            	    },
		    	            	    /* legend: {
		    	            	        orient : 'vertical',
		    	            	        x : 'right',
		    	            	        data:qType
		    	            	    }, */
		    	            	    calculable : true,
		    	            	    series : [
		    	            	        {
		    	            	            name:'访问来源',
		    	            	            type:'pie',
		    	            	            radius : '30%',
		    	            	            center: ['51%', '40%'],
		    	            	            data:questionData,
		    	            	            radius : '32%',
		    	            	            center: ['50%', '40%'],
		    	            	            data:questionData,
		    	            	            itemStyle:{
		    	  	            	            normal:{
		    	  	            	                  label:{
		    	  	            	                    show: true,
		    	  	            	                    formatter: '{b}:{c}',
			  	            	                    	textStyle:{
			  	  	            	                  		fontSize:1
			  	  	            	                  	}
		    	  	            	                  },
		    	  	            	                  labelLine :{show:true}
		    	  	            	                }
		    	  	            	            }
		    	            	        }
		    	            	    ]
		    	            	};
		    	              
		    	           
		    	                // 为echarts对象加载数据 
		    	                myChart.setOption(option,true); 
		    	            }
		    	        );
				 		if(area){
							loadRiver(area);
						}else{
							var html = "<option value=\"\">请选择</option>";
							$("#reachname").empty().append(html);
						}
		 			}
			//选择河道，重新加载1图
		 			function riverByTj(){
		 				 var reachname = $("#reachname").val();
		 				 var area = $("#area").val();
				 		 require(
				    	            [
				    	                'echarts',
				    	                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
				    	            ],
				    	            function (ec) {
				    	                // 基于准备好的dom，初始化echarts图表
				    	                var myChart = ec.init(document.getElementById('leftmain')); 
				    	              // 加载数据  
				    	              //var area = new Array();
				    	              var questionData = new Array();
				    	              var startTime ="";
				    	  			  var endTime = "";
				    	              var cou = "";
				    	              var data = "";
				    	              var qType = new Array();//所有问题类型
				    	              
				    	              $.ajax({
				    						type : 'post',
				    						data : {},
				    						async:false,
				    						dataType : "json",
				    						url : "${ctx}/waterwx/findAllTypeByTj",
				    						success : function(data) {
				    							$.each(data, function(i, value){
				    								qType.push(value);
				    							});
				    						}
				    					}); 
				    	              var questionData = new Array();
				    	              //河道
				    	              //var area11 = $("#questionArea").val();
				    	              startTime = $('#search_startTime').val();
				    		    	  endTime = $('#search_endTime').val();
										$.ajax({
				    						type : 'post',
				    						data : {rivername:reachname,area:area,startTime:startTime,endTime:endTime},
				    						dataType : "json",
				    						async:false,
				    						url : "${ctx}/waterwx/getTypeByRiverNameAndAreaTj",
				    						success : function(data) {
				    							$.each(data, function(i, value){
				    	  							var json = {};
				    	  							json.name=value[0];
				    	  							json.value=value[1];
				    	  							questionData.push(json);
				    							});
				    						}
				    					}); 
				    	              option = {
				    	            	    title : {
				    	            	        text: '河段问题统计',
				    	            	        x:'left'
				    	            	    },
				    	            	    tooltip : {
				    	            	        trigger: 'item',
				    	            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    	            	    },
				    	            	    /* legend: {
				    	            	        orient : 'vertical',
				    	            	        x : 'right',
				    	            	        data:qType
				    	            	    }, */
				    	            	    calculable : true,
				    	            	    series : [
				    	            	        {
				    	            	            name:'访问来源',
				    	            	            type:'pie',
				    	            	            radius : '32%',
				    	            	            center: ['50%', '40%'],
				    	            	            data:questionData,
				    	            	            itemStyle:{
				    	  	            	            normal:{
				    	  	            	                  label:{
				    	  	            	                    show: true,
				    	  	            	                    formatter: '{b}:{c}',
					  	            	                    	textStyle:{
					  	  	            	                  		fontSize:1
					  	  	            	                  	}
				    	  	            	                  },
				    	  	            	                  labelLine :{show:true}
				    	  	            	                }
				    	  	            	            }
				    	            	        }
				    	            	    ]
				    	            	};
				    	              
				    	           
				    	                // 为echarts对象加载数据 
				    	                myChart.setOption(option,true); 
				    	            }
				    	            
				    	        );
		 					}
			
	  		//全市统计图1
	  		function loadPie(){
	  			$.getJSON('../waterwx/findAllArea', function(data){
	    			  var len = data.length;
	    			  var html = "<option value=''>请选择</option>";
	    			  for(var i=0; i<len; i++){
	    				  if(data[i]){
	    					  html += "<option value="+data[i]+">"+data[i]+"</option>\n";
	    				  }
	    				 
	    			  }
	    			  $("#area").empty().append(html);
					  $("#reachname").empty().append("<option value=\"\">请选择</option>");
	    		});
  	      require(
    	            [
    	                'echarts',
    	                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	            ],
    	            function (ec) {
    	                // 基于准备好的dom，初始化echarts图表
    	                var myChart = ec.init(document.getElementById('leftmain')); 
    	              // 加载数据  
    	              var area = new Array();
    	              var questionData = new Array();
    	              var startTime ="";
    	  			  var endTime = "";
    	              var cou = "";
    	              var data = "";
    	              var qType = new Array();//所有问题类型
    	              $.ajax({
    						type : 'post',
    						data : {},
    						async:false,
    						dataType : "json",
    						url : "${ctx}/waterwx/findAllTypeByTj",
    						success : function(data) {
    							$.each(data, function(i, value){
    								qType.push(value);
    							});
    						}
    					}); 
    	             startTime = $('#search_startTime').val();
    		    	 endTime = $('#search_endTime').val();
    	            $.ajax({
  					type : 'post',
  					data : {startTime:startTime,endTime:endTime},
  					async:false,
  					dataType : "json",
  					url : "${ctx}/waterwx/getTypeByArea",
  					success : function(data) {
  						$.each(data, function(i, value){
  							area.push(value[0]);
  							var json = {};
  							json.name=value[0];
  							json.value=value[1];
  							questionData.push(json);
  						});
  					},
  					error:function(e){
  						alert("error")	;
  					}
  				});  
    	              option = {
    	            	    title : {
    	            	        text: '问题分类统计',
    	            	        x:'left'
    	            	    },
    	            	    tooltip : {
    	            	        trigger: 'item',
    	            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	            	    },
    	            	    /* legend: {
    	            	        orient : 'vertical',
    	            	        x : 'right',
    	            	        data:qType
    	            	    }, */
    	            	    calculable : true,
    	            	    series : [
    	            	        {
    	            	            name:'访问来源',
    	            	            type:'pie',
    	            	            radius : '32%',
    	            	            center: ['50%', '40%'],
    	            	            data:questionData,
    	            	            itemStyle:{
    	  	            	            normal:{
    	  	            	                  label:{
    	  	            	                    show: true,
    	  	            	                    formatter: '{b}:{c}',
	  	            	                    	textStyle:{
	  	  	            	                  		fontSize:1
	  	  	            	                  	}
    	  	            	                  },
    	  	            	                  labelLine :{show:true}
    	  	            	                }
    	  	            	            }
    	            	        }
    	            	    ]
    	            	};
    	                // 为echarts对象加载数据 
    	                myChart.setOption(option,true); 
    	            }
    	        );
  			};

  			
  			function loadRiver(area){
  				$.getJSON('../waterwx/getRiverByArea?area='+area, function(data){
  					var len = data.length;
  					var html = "<option value=''>选择河段</option>";
  					for(var i=0; i<len; i++){
  						if(data[i]){
  							html += "<option value="+data[i].riverName+">"+data[i].riverName+"</option>\n";
  						}
  					}
  					$("#reachname").empty().append(html);
  				});
  			}
  			
  			loadPie();
  		
  	    </script>
		
	</head>
	<body >
		<div id="wrapper">
    		<section class="content" style="background-color:#fff;">
			<div class="col-lg-12" style="border:1px solid #ccc;">
			<div class="weui_cell">
			
            <div class="weui_cell_hd"><label class="weui_label">地区</label></div>
            <div class="weui_cell_bd weui_cell_primary">
                <select class="weui_select" name="area" id="area">
                </select>
            </div>
	    	</div>
	    	<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">河段</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	               <select class="weui_select" name="reachname" id="reachname">
	               </select>
	            </div>
	    	</div>
	    	
			<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="weui_label">时间</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
                <input type="text" style="height:30px;" id="search_startTime" class="form-control" placeholder="请输入开始时间" style="border-radius:4px">
                <input type="text" style="height:30px;" id="search_endTime" class="form-control" placeholder="请输入结束时间" style="border-radius:4px">
            	</div>
    		</div>
	    		<div class="bd spacing" style="width:100%; text-align: center;">
						<button type="button" class="weui_btn weui_btn_primary" id="timeSearch">查询</button>
				</div>
			
			<br/>
					<div id="leftmain" style="height:70%;weight:100%;"></div>
		</div>
		</section>
		</div>
		<script type="text/javascript">
		$(function(){
			$("#area").on("change",function(){
	  			var area = $("#area").val();
	  			if(area==''){
	  				loadPie();
	  			}else{
		  		    selectArea(area);
		  		}
	  		});
			
			$("#reachname").on("change",function(){
 				var reachname = $("#reachname").val();
 				var area = $("#area").val();
		  			if(area!=''&&reachname!=''){
		  				riverByTj(area,reachname);
		  			}else if(area!=''){
		  				selectArea(area);	
		  			}else{
		  				loadPie();
		  			}
		  		});
			
			
			$("#search_startTime,#search_endTime").datetimepicker({
				language: 'zh-CN',
				format: "yyyy-MM-dd hh:ii:ss",
				autoclose: true,
				todayBtn: true
			});
			$("#timeSearch").on("click",function(){
				loadPie();
			});
		});
		</script>
	</body>
</html>