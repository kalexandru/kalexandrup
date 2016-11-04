<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/public/css/styles.css" rel="stylesheet" type="text/css">

    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Create Film Message</title>

</head>
<body>

<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="film">

    <table class="formtable">
        <tr>
            <td class="label">Title:</td>
            <td><sf:input class="control" path="title" type="text" size="28" name="title"/><br/><sf:errors path="title" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label">Film description:</td>
            <td><sf:textarea class="control" cssStyle="white-space: nowrap" path="description" name="description" cols="41" rows="7"/><br/><sf:errors path="description" cssClass="error"/></td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" type="submit" value="Create"/>
        </tr>

    </table>

</sf:form>

</body>
</html>
