<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">

    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script src="ECharts/echarts.min.js"></script>

    <meta charset="UTF-8">
    <title>将页面平均分成四部分</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        .main {
            width: 100%;
            height: 100%;
            position: absolute;
        }

        .quarter-div {
            width: 50%;
            height: 50%;
            float: left;
        }

        .blue {
            background-color: #5BC0DE;
            border: 2px solid #0f0f0f;
        }

        .green {
            background-color: #5CB85C;
            border: 2px solid #0f0f0f;
        }

        .orange {
            background-color: #F0AD4E;
            border: 2px solid #0f0f0f;
        }

        .yellow {
            background-color: #FFC706;
            border: 2px solid #0f0f0f;
        }
    </style>


    <script>
        $(function () {
            //页面加载完毕，绘制统计图表
            getCharts();

        });

        function getCharts() {

            $.ajax({
                url: "workbench/tran/getCharts.do",
                type: "get",
                dataType: "json",
                success: function (data) {
                    /*
                           data
                               {
                                   data.total              总条数
                                   data.dataList           数据集
                               }
                    */

                    var myChart = echarts.init(document.getElementById('main'));
                    var myChart1 = echarts.init(document.getElementById('main1'));
                    var myChart2= echarts.init(document.getElementById('main2'));
                    var myChart3 = echarts.init(document.getElementById('main3'));

                    // 指定图表的配置项和数据
                    //option就是我们要画的图
                    var option = {
                        title: {
                            text: '交易漏斗图',
                            subtext: '统计交易阶段数量的漏斗图'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c}%"
                        },
                        toolbox: {
                            feature: {
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        legend: {
                            data: ['展现', '点击', '访问', '咨询', '订单']
                        },

                        series: [
                            {
                                name: '交易漏斗图',
                                type: 'funnel',
                                left: '10%',
                                top: 60,
                                //x2: 80,
                                bottom: 60,
                                width: '80%',
                                // height: {totalHeight} - y - y2,
                                min: 0,
                                max: data.total,
                                minSize: '0%',
                                maxSize: '100%',
                                sort: 'descending',
                                gap: 2,
                                label: {
                                    show: true,
                                    position: 'inside'
                                },
                                labelLine: {
                                    length: 10,
                                    lineStyle: {
                                        width: 1,
                                        type: 'solid'
                                    }
                                },
                                itemStyle: {
                                    borderColor: '#fff',
                                    borderWidth: 1
                                },
                                emphasis: {
                                    label: {
                                        fontSize: 40
                                    }
                                },
                                data:data.dataList

                                /*
                                    [
                                            {value: 60, name: '访问'},
                                            {value: 40, name: '咨询'},
                                            {value: 20, name: '订单'},
                                            {value: 80, name: '点击'},
                                            {value: 100, name: '展现'}
                                    ]
                                */
                            }
                        ]
                    };


                    var option1 = {
                        title: {
                            text: '某站点用户访问来源',
                            subtext: '纯属虚构',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                        },
                        series: [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius: '50%',
                                data: [
                                    {value: 1048, name: '搜索引擎'},
                                    {value: 735, name: '直接访问'},
                                    {value: 580, name: '邮件营销'},
                                    {value: 484, name: '联盟广告'},
                                    {value: 300, name: '视频广告'}
                                ],
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };

                    var option2 = {
                        xAxis: {
                            type: 'category',
                            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: [120, 200, 150, 80, 70, 110, 130],
                            type: 'bar',
                            showBackground: true,
                            backgroundStyle: {
                                color: 'rgba(180, 180, 180, 0.2)'
                            }
                        }]
                    };

                    var option3 = {
                        title: {
                            text: '堆叠区域图'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'cross',
                                label: {
                                    backgroundColor: '#6a7985'
                                }
                            }
                        },
                        legend: {
                            data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '邮件营销',
                                type: 'line',
                                stack: '总量',
                                areaStyle: {},
                                emphasis: {
                                    focus: 'series'
                                },
                                data: [120, 132, 101, 134, 90, 230, 210]
                            },
                            {
                                name: '联盟广告',
                                type: 'line',
                                stack: '总量',
                                areaStyle: {},
                                emphasis: {
                                    focus: 'series'
                                },
                                data: [220, 182, 191, 234, 290, 330, 310]
                            },
                            {
                                name: '视频广告',
                                type: 'line',
                                stack: '总量',
                                areaStyle: {},
                                emphasis: {
                                    focus: 'series'
                                },
                                data: [150, 232, 201, 154, 190, 330, 410]
                            },
                            {
                                name: '直接访问',
                                type: 'line',
                                stack: '总量',
                                areaStyle: {},
                                emphasis: {
                                    focus: 'series'
                                },
                                data: [320, 332, 301, 334, 390, 330, 320]
                            },
                            {
                                name: '搜索引擎',
                                type: 'line',
                                stack: '总量',
                                label: {
                                    show: true,
                                    position: 'top'
                                },
                                areaStyle: {},
                                emphasis: {
                                    focus: 'series'
                                },
                                data: [820, 932, 901, 934, 1290, 1330, 1320]
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                    myChart1.setOption(option1);
                    myChart2.setOption(option2);
                    myChart3.setOption(option3);

                }
            })
        }
    </script>



</head>
<body>
<div class="main">

    <%--蓝色区域--%>
    <div class="quarter-div blue" id="main">

    </div>

    <%--绿色区域--%>
    <div class="quarter-div green" id="main1">
    </div>

    <%--橘色区域--%>
    <div class="quarter-div orange" id="main2"></div>
    <%--黄色区域--%>
    <div class="quarter-div yellow" id="main3"></div>
</div>
</body>
</html>