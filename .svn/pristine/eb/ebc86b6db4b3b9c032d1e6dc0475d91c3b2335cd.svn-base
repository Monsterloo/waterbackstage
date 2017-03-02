<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
		<title>仪表盘</title>
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
		 	$.ajax({
				type : 'post',
				data : {},
				dataType : "json",
				url : "${ctx}/waterwx/findAllArea",
				success : function(data) {
					$("#questionArea").append("<option onclick='selectArea(this)' class='area' >请选择</option>");
					$("#questionArea2").append("<option onclick='selectAreaToRiver(this)' class='area2' >请选择</option>");
					$.each(data, function(i, value){
						$("#questionArea").append("<option onclick='selectArea(this)' class='area' >"+value+"</option>");
						$("#questionArea2").append("<option onclick='selectAreaToRiver(this)' class='area2' >"+value+"</option>");
						//$("#questionArea").append(value);
					});
				}
			}); 
			//根据地区统计河道问题数量  图2
			function selectAreaToRiver(obj){
				var area = obj.text;
				// 使用 地区统计2图
	  	        require(
	  	            [
	  	                'echarts',
	  	                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
	  	            ],
	  	            function (ec) {
	  	                // 基于准备好的dom，初始化echarts图表
	  	                var myChart = ec.init(document.getElementById('main')); 
	  	              // 加载数据  
	  	              var legend = new Array();//河道标题
	  	              var riverData = new Array();//河道统计数据
	  	              var cou = "";
	  	              var data = "";
	  	              var area1= new Array();//所有地区
	  	          	var startTime = $('#search_startTime').val();
		    		var endTime = $('#search_endTime').val();
		    		var requestUrl = "";
		    		var requestData = {};
		    		if(area == "请选择"){
		    			loadPie2();
		    		}else{
	  	            $.ajax({
						type : 'post',
						data : {area:area,startTime:startTime,endTime:endTime},
						async:false,
						dataType : "json",
						url : "${ctx}/waterwx/getCountRiverByAreaTj",
						success : function(data) {
							$.each(data, function(i, value){
								legend.push(value[0]);
								var json = {};
								json.name=value[0];
								json.value=value[1];
								riverData.push(json);
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
	  	            	    legend: {
	  	            	        orient : 'vertical',
	  	            	        x : 'right',
	  	            	        y : 'top',
	  	            	        data:legend
	  	            	    },
	  	            	   toolbox: {
	  	                    show : true,
	  	                    x:'center',
	  	                    feature : {
	  	                        mark : {show: true},
	  	                        dataView : {show: true, readOnly: false}
	  	                    }
	  	                }, 
	  	            	    calculable : true,
	  	            	    series : [
	  	            	        {
	  	            	            name:'河道统计',
	  	            	            type:'pie',
	  	            	            radius : '50%',
	  	            	            center: ['30%', '60%'],
	  	            	            data:riverData,
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
	  	            }
	  	        );
			}
			//选择地区重新加载第一图
		 	function selectArea(obj){
		 		var div = $(obj).parent().parent();
		 		var stat = obj.text;
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
		    	              startTime = $('#search_startTime').val();
		    		    		endTime = $('#search_endTime').val();
		    	              $.ajax({
		    						type : 'post',
		    						data : {area:stat,startTime:startTime,endTime:endTime},
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
		    	            	            radius : '55%',
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
					   $.ajax({
							type : 'post',
							data : {area:stat},
							dataType : "json",
							url : "${ctx}/waterwx/getRiverByArea",
							success : function(data) {
								$("#questionRiver").empty().append("<option onclick='riverByTj(this)' class='river' >请选择</option>");
								$.each(data, function(i, value){
									$("#questionRiver").append("<option onclick='riverByTj(this)' class='river' >"+value.riverName+"</option>");
									//$("#questionArea").append(value);
								});
							}
						}); 
		 	}
			//选择河道，重新加载1图
		 			function riverByTj(obj){
		 				var div = $(obj).parent().parent();
				 		var stat = obj.text;
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
				    	              var area11 = $("#questionArea").val();
				    	              startTime = $('#search_startTime').val();
				    		    		endTime = $('#search_endTime').val();
										$.ajax({
				    						type : 'post',
				    						data : {rivername:stat,area:area11,startTime:startTime,endTime:endTime},
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
				    	            	            radius : '55%',
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
				 		
		 			}
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
  	            
  	            $.ajax({
					type : 'post',
					data : {},
					async:false,
					dataType : "json",
					url : "${ctx}/waterwx/findQuestionAllArea",
					success : function(data) {
						$.each(data, function(i, value){
							area1.push(value);
							areaAll.push(value);
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
					url : "${ctx}/waterwx/getAreaQuestionTj",
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
  	            	    legend: {
  	            	        orient : 'vertical',
  	            	        x : 'right',
  	            	        y : 'top',
  	            	        data:area1
  	            	    },
  	            	  toolbox: {
	  	                    show : true,
	  	                    x:'center',
	  	                    z:8,
	  	                    feature : {
	  	                        mark : {show: true},
	  	                        dataView : {show: true, readOnly: false}
	  	                    }
  	            	  },
  	            	 calculable : true,
  	            	    series : [
  	            	        {
  	            	            name:'地区统计',
  	            	            type:'pie',
  	            	            radius : '50%',
  	            	            center: ['30%', '60%'],
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
  		//全市统计图1
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
    	            	            radius : '55%',
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
  	
	     // 使用
	        require(
	            [
	                'echarts',
	                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	            ],
	            function (ec) {
	                // 基于准备好的dom，初始化echarts图表
	                
	                var area10 = new Array();
	                $.ajax({
						type : 'post',
						data : {},
						dataType : "json",
						url : "${ctx}/waterwx/findQuestionAllArea",
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
								url : "${ctx}/waterwx/getQuestionCountByAreaAll",
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
											url : "${ctx}/waterwx/getQuestionCountByAreaYcl",
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
													    title : {
													        text: '问题统计情况'
													    },
													    tooltip : {
													        trigger: 'axis'
													    },
													    legend: {
													        data:['全部','已处理']
													    },
													   
													    calculable : true,
													    xAxis : [
													        {
													            type : 'category',
													            data : area10
													        }
													    ],
													    yAxis : [
													        {
													            type : 'value'
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
	        // 使用
	        require(
	            [
	                'echarts',
	                'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
	            ],
	            function (ec) {
	                // 基于准备好的dom，初始化echarts图表
	                var myChart = ec.init(document.getElementById('bottom')); 
	              // 加载数据  
	              var ycl = [0,0,0,0,0,0,0,0,0,0,0,0];//已处理
	              var wcl = [0,0,0,0,0,0,0,0,0,0,0,0];//未处理
	            
	            $.ajax({
					type : 'post',
					data : {},
					async:false,
					dataType : "json",
					url : "${ctx}/waterwx/getYcl",
					success : function(data) {
						$.each(data, function(i, value){
							var json = {};
							var month = value[0];
							var month1 = month.substr(month.length-2);
							var month2= month1.substr(0,1);
							if(month2== 0){
								month1 = month1.substr(-1);
							}
							ycl[month1-1] = value[1];
							json.name=value[0];
							json.value=value[1];
							ycl.push(json);
						});
					}
				}); 
	         
	            $.ajax({
					type : 'post',
					data : {},
					async:false,
					dataType : "json",
					url : "${ctx}/waterwx/getWcl",
					success : function(data) {
						
						$.each(data, function(i, value){
							var json = {};
							var month = value[0];
							var month1 = month.substr(month.length-2);
							var month2= month1.substr(0,1);
							if(month2== 0){
								month1 = month1.substr(-1);
							}
							wcl[month1-1] = value[1];
							json.name=value[0];
							json.value=value[1];
							ycl.push(json);
							json.name=value[0];
							json.value=value[1];
							wcl.push(json);
						});
					},
					error:function(e){
						alert("error")	;
					}
				}); 
	             option = {
   					 title : {
						        text: '投诉处理情况'
						    },
						    tooltip : {
						        trigger: 'axis'
						    },
						    legend: {
						        data:['已处理','未处理']
						    },
						    calculable : true,
						    xAxis : [
						        {
						            type : 'category',
						            boundaryGap : false,
						            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value',
						            /* axisLabel : {
						                formatter: '{value} °C'
						            } */
						            data:[0,10,20,30,40,50]
						        }
						    ],
						    series : [
						        {
						            name:'已处理',
						            type:'line',
						            data:ycl,
						            markPoint : {
						                data : [
						                    {type : 'max', name: '最大值'},
						                    {type : 'min', name: '最小值'}
						                ]
						            }
						        },
						        {
						            name:'未处理',
						            type:'line',
						            data:wcl
						        }
						    ]
						};
						                    
							              
	           
	                // 为echarts对象加载数据 
	                myChart.setOption(option); 
	            }
	        );
	      	var tableQTypeTh = new Array();
	      	var typeLenght =0;
	     	$.ajax({
					type : 'post',
					data : {},
					dataType : "json",
					url : "${ctx}/waterwx/findAllTypeByTj",
					success : function(data) {
						$.each(data, function(i, value){
							tableQTypeTh.push(value);
						});
					 typeLenght = tableQTypeTh.length;
					}
				}); 
	     	var tableQTypeTd = new Array(typeLenght);
						var td = new Array(17);
	        $.ajax({
				type : 'post',
				data : {},
				dataType : "json",
				url : "${ctx}/waterwx/findAllArea",
				success : function(data) {
					$.each(data, function(i, value){
						td[0] = value;
						 $.ajax({
	    						type : 'post',
	    						data : {area:value},
	    						dataType : "json",
	    						async:false,
	    						url : "${ctx}/waterwx/getRiverByAreaToTj",
	    						success : function(data) {
	    							if(data[0]  == undefined){
	    								for(var tf=0;tf<tableQTypeTh.length+1;tf++){
	    	  									td[tf+1] = 0;
	    	  							}
	    							}else{
	    	  							var sum = 0;
	    							$.each(data, function(i, value){
	    	  									sum +=value[1];
	    								for(var tf=0;tf<tableQTypeTh.length;tf++){
	    									if(tableQTypeTh[tf] == value[0]){
	    	  									//tableQTypeTd[tf] = value[1];
	    	  									td[tf+2] = value[1];
	    	  								}
	    	  							}
	    							});
	    	  								td[1]= sum;
	    							}
	    						}
	    					}); 
						 var area = value;
						 $.ajax({
								type : 'post',
								data : {area:area},
								async:false,
								dataType : "json",
								url : "${ctx}/waterwx/getCountAreaYcl",
								success : function(data) {
									var tdLength = td.length;
									var sum = td[1];
									td[tdLength-4] = data[0][0];
									if(sum == 0){
										td[tdLength-3] = 0;
									}else{
									var ycl = data[0][0];
									var persent = ycl/sum*100;
									persent = Math.round(persent*100)/100;
									td[tdLength-3] = persent +"%";
									}
								}
							});
						 $.ajax({
								type : 'post',
								data : {area:area},
								async:false,
								dataType : "json",
								url : "${ctx}/waterwx/getCountAreaZzcl",
								success : function(data) {
									var tdLength = td.length;
									var sum = td[1];
									td[tdLength-2] = data[0][0];
									if(sum == 0){
										td[tdLength-1] = 0;
									}else{
									var ycl = data[0][0];
									var persent = ycl/sum*100;
									persent = Math.round(persent*100)/100;
									td[tdLength-1] = persent +"%";
									}
								}
							});
						 var html="<tr><td></td>";
						 console.log(td.length);
						 for(var tt=0;tt<td.length;tt++){
							 if(td[tt] == null || td[tt] == "" || td[tt] == NaN){
								 td[tt] = 0;
							 }
							 html+="<td>"+td[tt]+"</td>";
						 }
						 html+="</tr>";
						 $("#tableTj").append(html);
					});
				}
			}); 
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
    			<div style="display:none;" >
               		
                  
           		</div>
           		 
			<div class="col-lg-12" style="border:1px solid #ccc;">
				 
				<div >
					<div class="col-lg-1" style="padding-right: 0px; padding-top: 10px; font-size: 15px; padding-left: 40px;">
						<span>时间:</span>
					</div>
					<div class="col-lg-2" style="width:150px;" >
						<div class="input-group"  style="width:150px;padding-top:5px;">
	                        <input type="text" style="height:30px;" id="search_startTime" class="form-control" placeholder="请输入开始时间" style="border-radius:4px">
	                    </div>
					</div>
					<div class="col-lg-2" style="width:150px;" >
							<div class="input-group"  style="width:150px;padding-top:5px;">
	                            <input type="text" style="height:30px;" id="search_endTime" class="form-control" placeholder="请输入结束时间" style="border-radius:4px">
	                        </div>
					</div>
					<div class="col-lg-2" style="height:30px;margin-left:10px;">
						<button type="button" class="btn btn-success" id="timeSearch" style="height:30px;padding-top:5px;margin-top:5px;">查询</button>
						
					</div>
					<div>
<form class="navbar-form navbar-left"  id="exportDownload" action="${ctx }/waterwx/exportExcel" >
           		 		<!-- <input type="text" style="height:30px;" id="startTime" name="startTime" class="form-control" placeholder="请输入开始时间" style="border-radius:4px">
           		 		<input type="text" style="height:30px;" id="endTime" name="endTime" class="form-control" placeholder="请输入结束时间" style="border-radius:4px"> -->
           		 		<button type="button" class="btn btn-success" id="exportButton"  style="height:30px;padding-top:5px;margin-top:5px;">导出</button> <span style="color:red">(只能导出本周的统计数据)</span>
					</form>
				</div>
					<div>
					
					
				
			 		 <div style="z-index:100;padding-top:80px;padding-left:20px;position:absolute;">地区  <select id="questionArea" > </select>
			 			河段  <select id="questionRiver"><option value="请选择" >请选择</option></select>
			 		</div> 
			 		<div style="z-index:5;padding-top:330px;padding-left:40px;position:absolute;">地区  <select id="questionArea2" > </select>
			 		</div> 
			 		<!-- <div>1234<select id="Area" ><option value="请选择" >请选择</option> </select>
			 			<select id="River"><option value="请选择">请选择</option> </select>
			 		</div> -->
			 	<div id="leftmain" class="col-lg-12" style="margin-top:5px;height:250px;border:1px solid #ccc;">
			 	</div>
			 	<div class="col-lg-12" style="height:200px;border:1px solid #ccc;">
			 		<div class="col-lg-2" id="" ><font size="4" style="font-weight:bold;">投诉分类统计</font></div>
			 		<div id="main" class="col-lg-10" style="height:200px;"></div>
			 	</div>
			</div>
		
			<div class="col-lg-12" id="bottom1"  style="height:200px;border:1px solid #ccc;"></div>
			<div class="col-lg-12" id="bottom"  style="height:200px;border:1px solid #ccc;"></div>
			<div class="col-lg-12">
				<table class="table table-hover table-striped table-bordered" id="tableTj" style="width:100%;">
						<tr></tr>
						<tr></tr>
						<tr>
							<td></td>
							<td rowspan="2">名称</td>
							<td rowspan="2">小计</td>
							<td colspan="11">问题分类</td>
							<td colspan="4">办理情况</td>
						</tr>
						<tr>
							<td></td>
							<td>工业废水排放</td>
							<td>养殖污染</td>
							<td>沿岸排水设施</td>
							<td>违法建设</td>
							<td>建筑废弃物</td>
							<td>生活垃圾</td>
							<td>堤身破损</td>
							<td>堤顶道路损坏</td>
							<td>穿堤建筑物损坏</td>
							<td>标志牌责任牌破损</td>
							<td>其他问题</td>
							<td>已整改</td>
							<td>占比</td>
							<td>正在整改</td>
							<td>占比</td>
						</tr>
				</table>
			</div>
		</section>
		</div>
		</div>
		<script type="text/javascript">
		$(function(){
			 $("#search_startTime,#search_endTime").datetimepicker({
				language: 'zh-CN',
				format: "yyyy-MM-dd hh:ii:ss",
				autoclose: true,
				todayBtn: true
			}); 
			/* $("#search_endTime").datetimepicker({
				language: 'zh-CN',
				format: "yyyy-MM-dd hh:ii:ss",
				autoclose: true,
				todayBtn: true
			});
			$("#search_startTime").datetimepicker({
				language: 'zh-CN',
				format: "yyyy-MM-dd hh:ii:ss",
				autoclose: true,
				todayBtn: true
			}); */
			$("#timeSearch").on("click",function(){
				
				startTime = $('#search_startTime').val();
	    		endTime = $('#search_endTime').val();
	    		window.location.reload();
			});
			$("#exportButton").click(function(){
				
				if (confirm("确认导出？")) {
					var startTime = $('#search_startTime').val();
 		    		var	endTime = $('#search_endTime').val();
					$("#startTime").val(startTime);
					$("#endTime").val(endTime);
					$("#exportDownload").submit();
					//$("#exportForm").submit();
					alert("正在导出，请稍后！");
				} 
			});
			/* $("#exportSearch").on("click",function(){
				
				startTime = $('#search_startTime').val();
	    		endTime = $('#search_endTime').val();
	    		alert("导出 ");
			}); */
		});
		</script>
	</body>
</html>