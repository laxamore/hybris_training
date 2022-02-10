<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<template:page pageTitle="${pageTitle}">
    <div class="trainingCustomPage">
        <div class="productDetailPanelCustom">
            <div class="product-details page-title">
                <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
                    <div class="title">
                        <div class="name">
                            ${fn:escapeXml(product.name)}
                        </div>
                        <span class="sku">ID</span>
                        <span class="code">${fn:escapeXml(product.code)}</span>
                    </div>
                </ycommerce:testId>
            </div>

            <div>
                <c:forEach items="${galleryImages}" var="container" varStatus="varStatus">
                    <div class="item">
                        <div>
                            <img src="${fn:escapeXml(container.product.url)}">
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="trainingCustomPage_price">
                <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
                    <product:productPricePanel product="${product}"/>
                </ycommerce:testId>
            </div>

            <div>
                <button id="prevBtn">Previous Product</buttuon>
                <button id="nextBtn">Next Product</buttuon>
            </div>
        </div>
    </div>
</template:page>
