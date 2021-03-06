<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ruav
  Date: 08.06.18
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${Title}</title>
    <%--<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:400,500,700,400italic|Material+Icons">--%>

    <script
            src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js">
    </script>
    <link rel="stylesheet"
          href="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <style>
        .answered {
            background-color: mediumseagreen;
        }
        .searchButton {
            background-color: cadetblue;
        }
        .head {
            background-color: wheat;
        }
    </style>
</head>
<body>

<div>
    <table>
        <tr>
            <td>
                <form method="get" action="/query">
                    <fieldset class="mdl-textfield mdl-js-textfield">
                        <div class="mdl-textfield mdl-js-textfield">
                            <label class="mdl-textfield__label" for="query">Input your question here</label>
                            <input type="text" name="query" id="query" class="mdl-textfield__input">
                        </div>
                        <br/>
                        <br/>
                        <div>
                            <input type="submit" value="Search" class="mdl-button searchButton">
                        </div>
                    </fieldset>
                </form>
            </td>
        </tr>
    </table>
</div>

<c:if test="${answers ne null}">
    <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
        <thead class="head">
        <td class="mdl-data-table__cell--non-numeric">Title</td>
        <td class="mdl-data-table__cell--non-numeric">Author</td>
        <td class="mdl-data-table__cell--non-numeric">Link</td>
        <td class="mdl-data-table__cell--non-numeric">Date</td>
        <td class="mdl-data-table__cell--non-numeric">Answered</td>
        </thead>
        <c:forEach items="${answers}" var="answer">
            <tr
                <c:if test="${answer.answered eq true}">
                        class="answered"
                </c:if>
            >
                <td class="mdl-data-table__cell--non-numeric">${answer.title}</td>
                <td class="mdl-data-table__cell--non-numeric">${answer.author}</td>
                <td class="mdl-data-table__cell--non-numeric">${answer.url}</td>
                <td class="mdl-data-table__cell--non-numeric">${answer.date}</td>
                <td class="mdl-data-table__cell--non-numeric">${answer.answered}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>


</body>
</html>
