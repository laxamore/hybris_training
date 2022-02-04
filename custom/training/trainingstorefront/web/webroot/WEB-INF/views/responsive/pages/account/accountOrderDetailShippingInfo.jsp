<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="account-orderdetail well well-tertiary">
    <div class="well-headline">
        <spring:theme code="text.account.order.orderDetails.billingInformtion" />
    </div>
    <ycommerce:testId code="orderDetails_paymentDetails_section">
        <div class="well-content">
            <div class="row">
                <div class="col-sm-12 col-md-9">
                    <div class="row">
                        <div class="col-sm-6 col-md-4 order-billing-address">
                            <order:billingAddressItem order="${orderData}"/>
                        </div>
                        <c:if test="${not empty orderData.paymentInfo}">
                            <div class="col-sm-6 col-md-4 order-payment-data">
                                <order:paymentDetailsItem order="${orderData}"/>
                            </div>
                        </c:if>
                        <div class="col-sm-6 col-md-4 order-payment-data">
                            <div class="label-order">
                                Custom Billing View
                            </div>
                            Name:&nbsp;
                            ${orderData.paymentInfo.billingAddress.title}&nbsp;
                            ${orderData.paymentInfo.billingAddress.firstName}&nbsp;
                            ${orderData.paymentInfo.billingAddress.lastName}
                            <br/>
                            Address Line:&nbsp;
                            ${orderData.paymentInfo.billingAddress.line1}&nbsp;
                            ${orderData.paymentInfo.billingAddress.line2}
                            <br/>
                            Town & Region:&nbsp;
                            ${orderData.paymentInfo.billingAddress.town}&nbsp;
                            ${orderData.paymentInfo.billingAddress.region.name}
                            <br/>
                            Country & Postal Code:&nbsp;
                            ${orderData.paymentInfo.billingAddress.country.name}&nbsp;,
                            ${orderData.paymentInfo.billingAddress.postalCode}
                            <br/>
                            <br/>
                            <div class="label-order">
                                Shipping Method
                            </div>
                            ${orderData.deliveryMode.name}
                            <br/>
                            ${orderData.deliveryMode.description}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </ycommerce:testId>
</div>