<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
		<title>投诉分类统计</title>
        <script src="../assets/jquery-1.12.2.min.js"></script>
    <script src="../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/weixin/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" ></script>
		<script type="text/javascript" src="../assets/jquery.validate.min.js" ></script>
		<script type="text/javascript" src="${ctx }/js/admin/common.js"></script>
		
		<script type="text/javascript" src="${ctx }/assets/metisMenu/metisMenu.min.js"></script>
		<script type="text/javascript" src="../assets/echart/echarts.js" ></script>
		<link rel="stylesheet" href="../assets/fontawesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../css/thePage.css" />
		<link rel="stylesheet" href="../assets/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="../css/weixin/weui.min.css"/>
    <link rel="stylesheet" href="../assets/jQuery-ui/jquery-ui-1.10.0.custom.css"/>
    <link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/assets/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
		<!--[if lt IE 9]>
  		<script type='text/javascript' src="${ctx}/js/respond.min.js"></script>
  		<script type='text/javascript' src="${ctx}/js/html5shiv.min.js"></script>
	<![endif]-->
		<script type="text/javascript">
		var startTime ="";
		var endTime = "";
		// 路径配置
			require.config({
			paths:{ 
  				'echarts' : '../assets/echart/dist'
			}
			});
			var questionData = new Array();//所有问题分类
			var areaAll = new Array();//所有地区

  			loadPie2();
  		// 使用 地区统计2图
  		function loadPie2(){
  	        require(
  	            [
  	                'echarts',
  	                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
  	            ],
  	            function (ec) {
  	                // 基于准备好的dom，初始化echarts图表
  	                var myChart = ec.init(document.getElementById('main')); 
  	              // 加载数据  
  	              var area = new Array();
  	              var areaData = new Array();
  	              var cou = "";
  	              var data = "";
  	              var area1= new Array();//所有地区
	  	          var startTime ="";
				  var endTime = "";
  	            
  	          	var startTime = $('#search_startTime').val();
	    		var endTime = $('#search_endTime').val();
  	            $.ajax({
					type : 'post',
					data : {startTime:startTime,endTime:endTime},
					async:false,
					dataType : "json",
					url : "${ctx}/waterwx/findTj",
					success : function(data) {
						$.each(data, function(i, value){
							area.push(value[0]);
							var json = {};
							json.name=value[0];
							json.value=value[1];
							areaData.push(json);
						});
					},
					error:function(e){
						alert("error")	;
					}
				}); 
  	              option = {
  	            	    tooltip : {
  	            	        trigger: 'item',
  	            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
  	            	    },
  	            	    /* legend: {
  	            	        orient : 'vertical',
  	            	        x : 'right',
  	            	        y : 'top',
  	            	        data:area1
  	            	    }, */
  	            	 /*  toolbox: {
	  	                    show : true,
	  	                    x:'center',
	  	                    z:8,
	  	                    feature : {
	  	                        mark : {show: true},
	  	                        dataView : {show: true, readOnly: false}
	  	                    }
  	            	  }, */
  	            	 calculable : true,
  	            	    series : [
  	            	        {
  	            	            type:'pie',
  	            	            radius : '40%',
  	            	            center: ['50%', '40%'],
  	            	            data:areaData,
  	            	          itemStyle:{
  	            	            normal:{
  	            	                  label:{
  	            	                    show: true,
  	            	                    formatter: '{b} : {c} '
  	            	                  },
  	            	                  labelLine :{show:true}
  	            	                }
  	            	            }
  	            	        }
  	            	    ]
  	            	};
  	                // 为echarts对象加载数据 
  	                myChart.setOption(option); 
  	            }
  	        );
  		}

	        //加载地区下拉框
	        function loadArea(){
	        	
	        }
	        //根据地区加载河道下拉框
	        function loadRiver(area){
	        	
	        }
  	    </script>
		
	</head>
	<body >
		<div id="wrapper">
    		<section class="content" style="background-color:#fff;">
			<div class="col-lg-12" style="border:1px solid #ccc;">
				
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
			 		<div class="col-lg-2" id="" ><font size="4" style="font-weight:bold;">投诉分类统计</font></div>
			 		<div id="main" style="height:70%;weight:100%;"></div>
		</div>
		</section>
		</div>
		<script type="text/javascript">
		$(function(){
			$("#search_startTime,#search_endTime").datetimepicker({
				language: 'zh-CN',
				format: "yyyy-MM-dd hh:ii:ss",
				autoclose: true,
				todayBtn: true
			});
			$("#timeSearch").on("click",function(){
				loadPie2();
			});
		});
		</script>
	</body>
</html>