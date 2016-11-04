<%@ page import="com.movie.spring.social.service.FilmService" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@include file="includes/header.jsp"%>



<table class="filterable">
    <tr>
        <th>Title</th>

        <th>Description</th>

        <th>Category Name</th>

        <th>Release Year</th>

        <th>Language Name</th>

        <th>Length&nbsp;</th>

        <th>Rating&nbsp;</th>

        <th>Actor Names</th>
    </tr>
    <c:forEach var="film" items="${films}">
        <tr>
            <td ><c:out value="${film.title}"></c:out></td>

            <td ><c:out value="${film.description}"></c:out></td>

            <td ><c:out value="${film.categoryName}"></c:out></td>

            <td ><c:out value="${film.release_year}"></c:out></td>

            <td ><c:out value="${film.language}"></c:out></td>

            <td ><c:out value="${film.length}"></c:out></td>

            <td ><c:out value="${film.rating}"></c:out></td>

            <td ><c:out value="${film.actorName}"></c:out></td>
        </tr>
    </c:forEach>
</table>

<%@include file="includes/footer.jsp"%>