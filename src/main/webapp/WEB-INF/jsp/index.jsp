<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${Title}</title>

    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

    <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
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
    <script>
        $(document).ready( function () {
            $('#myTable').DataTable({
                paging: true,
                ordering: true,
                "language": {
                    "search": "Apply filter _INPUT_ to table"
                }
            });
        } );
    </script>
</head>
<body>

<div>
    <table>
        <tr>
            <td>
                <form method="get" action="/query">
                    <fieldset class="mdl-textfield mdl-js-textfield">
                        <div class="mdl-textfield mdl-js-textfield">
                            <%--<label class="mdl-textfield__label" for="query">Input your question here</label>--%>
                            <input type="text" name="query" id="query" class="mdl-textfield__input" placeholder="Input your question here">
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


<div >
<c:if test="${answers ne null}">
    <table id="myTable" class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
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
                        style="background-color: mediumseagreen"
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
</div>

</body>
</html>
