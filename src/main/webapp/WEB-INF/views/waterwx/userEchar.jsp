<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
		<title>login</title>
		<script type="text/javascript" src="../assets/jquery-1.12.2.min.js" ></script>
		<script type="text/javascript" src="../assets/jquery.validate.min.js" ></script>
		<script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${ctx }/js/admin/common.js"></script>
		<link rel="stylesheet" href="../assets/bootstrap/bootstrap.min.css" />
		 <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" ></script>
		<script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" ></script>
		<link rel="stylesheet" href="${ctx}/assets/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
		<script type="text/javascript" src="${ctx }/assets/metisMenu/metisMenu.min.js"></script>
		<script type="text/javascript" src="../assets/echart/echarts.js" ></script>
		<link rel="stylesheet" href="../assets/fontawesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../css/thePage.css" />
		<link rel="stylesheet" href="${ctx}/css/main.css" />
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
  		//全市统计图1
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
    	              var questionData = new Array();
    	              var cou = "";
    	              var data = "";
    	              var qType = new Array();//所有问题类型
    	              
    	              $.ajax({
    						type : 'post',
    						data : {},
    						async:false,
    						dataType : "json",
    						url : "${ctx}/swjUser/findUserByRoleTj",
    						success : function(data) {
    							console.log(data);
    							for (var role in data) {  
    								  if (data.hasOwnProperty(role)) {   
    								  // or if (Object.prototype.hasOwnProperty.call(obj,prop)) for safety...  
    								    console.log("prop: " + role + " value: " + data[role])  ;
    								    var json = {};
    								    qType.push(role);
    		  							json.name=role;
    		  							json.value=data[role];
    		  							questionData.push(json);
    								  }  
    								}  
    						}
    					}); 
    	              option = {
    	            	    title : {
    	            	        text: '角色分类统计用户',
    	            	        x:'left'
    	            	    },
    	            	    tooltip : {
    	            	        trigger: 'item',
    	            	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	            	    },
    	            	     legend: {
    	            	        orient : 'vertical',
    	            	        x : 'right',
    	            	        data:qType
    	            	    }, 
    	            	    calculable : true,
    	            	    series : [
    	            	        {
    	            	            name:'访问来源',
    	            	            type:'pie',
    	            	            radius : '50%',
    	            	            center: ['45%', '60%'],
    	            	            data:questionData,
    	            	            itemStyle:{
    	  	            	            normal:{
    	  	            	                  label:{
    	  	            	                    show: true,
    	  	            	                    formatter: '{b} : {c}'
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
  	
  	    </script>
		
	</head>
	<body >
		<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
				<div class="clearfix">
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content" style="background-color:#fff;">
			<div class="col-lg-12" style="border:1px solid #ccc;">
			 	<div id="main" class="col-lg-12" style="height:500px;border:1px solid #ccc;"></div>
			</div>
		</section>
		</div>
		</div>
	</body>
</html>