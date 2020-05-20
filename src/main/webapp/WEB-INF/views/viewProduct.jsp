<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-wrapper">
	<div class="container" style="margin:auto;">
		<div class="row">
			<div class="col-md-6">
				<h1>Product Detail</h1>
				<p>Here is the Detail information of the product!<p>
				<c:set var="imageFilename"	value="/resources/images/${product.id }.jpg"/>
				<img src="<c:url value="${imageFilename }"/>" alt="image" style="width: 80%" />
			</div>
			<div class="col-md-6" style="margin:auto;">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p><strong>Manufacturer : </strong>${product.manufacturer}</p>
				<p><strong>Category : </strong>${product.category}</p>
				<h3>${product.price}원</h3>
			</div>
		</div>
	</div>
</div>