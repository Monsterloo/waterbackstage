<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
		<title>问题统计情况</title>
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
		// 路径配置
			require.config({
			paths:{ 
  				'echarts' : '../assets/echart/dist'
			}
			});
			var questionData = new Array();//所有问题分类
			var areaAll = new Array();//所有地区
  	
	     // 使用
	     function loadPie(){
	        require(
	            [
	                'echarts',
	                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	            ],
	            function (ec) {
	                // 基于准备好的dom，初始化echarts图表
	                var startTime ="";
					var endTime = "";
	                var area10 = new Array();
	                $.ajax({
						type : 'post',
						data : {},
						dataType : "json",
						url : "${ctx}/waterwx/findAllArea",
						success : function(data) {
							$.each(data, function(i, value){
								area10.push(value);
							});
							 var questionLength = area10.length;
							 startTime = $('#search_startTime').val();
					    		endTime = $('#search_endTime').val();
				            $.ajax({
								type : 'post',
								data : {startTime:startTime,endTime:endTime},
								dataType : "json",
								url : "${ctx}/waterwx/getCountByAreaAll",
								success : function(data) {
				             		 var allQ = new Array(questionLength);//问题总量
				             		 for(var k=0; k<questionLength; k++){
				             			allQ[k] = 0;
				             		 }
									$.each(data, function(i, value){
										var json = {};
										var area = value[1];
										for(var ja=0;ja<area10.length;ja++){
											if(area == area10[ja]){
												allQ[ja] = value[0];
												break;
											}
										}
									});
									 $.ajax({
											type : 'post',
											data : {startTime:startTime,endTime:endTime},
											dataType : "json",
											url : "${ctx}/waterwx/getCountByAreaYcl",
											success : function(data) {
												  var yclQ = new Array(questionLength);
												  for(var k=0; k<questionLength; k++){
								             			yclQ[k] = 0;
								             		 }
												$.each(data, function(i, value){
													var area = value[1];
													for(var jf=0;jf<area10.length;jf++){
														if(area == area10[jf]){
															yclQ[jf] = value[0];
															break;
														}
													}
												});
												option = {
													    /* title : {
													        text: '问题统计情况'
													    }, */
													    tooltip : {
													        trigger: 'axis'
													    },
													    legend: {
													        data:['全部','已处理']
													    },
													   
													    calculable : true,
													    xAxis : [
													        {
													            type : 'value'
													            
													        }
													    ],
													    yAxis : [
													        {
													            type : 'category',
													            data : area10
													        }
													    ],
													    series : [
													        {
													            name:'全部',
													            type:'bar',
													            data:allQ,
													            itemStyle:{
									  	            	            normal:{
									  	            	                  label:{
									  	            	                    show: true,
									  	            	                    formatter: '{c}'
									  	            	                  },
									  	            	                  labelLine :{show:true}
									  	            	                }
									  	            	            }
													        },
													        {
													            name:'已处理',
													            type:'bar',
													            data:yclQ,
													            itemStyle:{
									  	            	            normal:{
									  	            	                  label:{
									  	            	                    show: true,
									  	            	                    formatter: '{c}'
									  	            	                  },
									  	            	                  labelLine :{show:true}
									  	            	                }
									  	            	            }
													        }
													       
													    ]
													};
												var myChart = ec.init(document.getElementById('bottom1')); 
												 myChart.setOption(option); 
											},
											error:function(e){
												alert("error")	;
											}
										}); 
								}
							}); 
						}
					}); 
	            } 
	        );
		 };
	       
		 loadPie();
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
					<div id="bottom1" style="height:70%;weight:100%;"></div>
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
				loadPie();
			});
		});
		</script>
	</body>
</html>