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
                                <th>商品id</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoList as productInfo>
                                <tr>
                                    <td>${productInfo.productId}</td>
                                    <td>${productInfo.productName}</td>
                                    <td><img src="${productInfo.productIcon}" height="100" width="100"></td>
                                    <td>${productInfo.productPrice}</td>
                                    <td>${productInfo.productStock}</td>
                                    <td>${productInfo.productDescription}</td>
                                    <td>${productInfo.categoryType}</td>
                                    <td>${productInfo.createTime?string('dd.MM.yyyy HH:mm:ss')}</td>
                                    <td>
                                        <a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a>
                                    </td>
                                    <td>
                                        <#if productInfo.getProductStatusEunm().message == "上架">
                                            <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                        <#else >
                                            <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
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

</body>

</html>

