<html>
<#include "../common/head.ftl">
<body>
    <div id="wrapper" class="toggled">
        <#-- 边栏-->
        <#include "../common/nav.ftl">
        <#--主要内容-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOList as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEunm().message}</td>
                                    <td>${orderDTO.getPayStatusEunm().message}</td>
                                    <td>${orderDTO.createTime?string('dd.MM.yyyy HH:mm:ss')}</td>
                                    <td>
                                        <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                    </td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEunm().message == "新订单">
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else >
                                <li ><a href="/sell/seller/order/list?page=${currentPage - 1}&size=10">上一页</a></li>
                            </#if>

                            <#list 1..totalPage as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte totalPage>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else >
                                <li ><a href="/sell/seller/order/list?page=${currentPage + 1}&size=10">上一页</a></li>
                            </#if>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

<script>
    var websocket = null;
    if ('WebSocket' in window){
        websocket = new WebSocket('ws://127.0.0.1/sell/webSocket');
    }else {
        alert("该浏览器不支持websocket");
    }

    websocket.onopen = function (event) {
        console.log("建立连接");
    }

    websocket.onclose = function (event) {
        console.log("连接关闭");
    }

    websocket.onmessage = function (event) {
        console.log("受到消息：" + event.data);
        //弹窗提醒
    }

    websocket.onerror = function () {
        alert("websocket通信发生故障")
    }

    websocket.onbeforeunload = function () {
        websocket.close();
    }
</script>
</body>

</html>

