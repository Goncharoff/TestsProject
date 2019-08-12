<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
   

    <script type="text/javascript">
        $(document).ready(function () {
            $.getJSON("/?command=UserProfile", function (user) {
                $("#email").append(user.userEmail);
                $("#name").append(user.userName);
                $("#surname").append(user.userSurname);
                $.each(user.userStatistics, function (i, item) {
                    $("#tableBody")
                        .append($("<tr>")
                            .append($("<td>").text(item.testName))
                            .append($("<td>").text(item.correctAnswers))
                            .append($("<td>").text(item.passedAnswers))
                            .append($("<td>").text(new Date(item.dateRecorded).toLocaleDateString("en-US")))
                        );
                });
            });
        });
    </script>

    <style>
       div {
  box-sizing: border-box;
}

.table-container {
  display: block;
  margin: 2em auto;
  width: 90%;
  max-width: 600px;
}

.flag-icon {
  margin-right: 0.1em;
}

.flex-table {
  display: flex;
  flex-flow: row wrap;
  border-left: solid 1px #d9d9d9;
  transition: 0.5s;
}
.flex-table:first-of-type {
  border-top: solid 1px #1565C0;
  border-left: solid 1px #1565C0;
}
.flex-table:first-of-type .flex-row {
  background: #1976D2;
  color: white;
  border-color: #1565C0;
}
.flex-table.row:nth-child(odd) .flex-row {
  background: #f4f2f1;
}
.flex-table:hover {
  background: #F5F5F5;
  transition: 500ms;
}

.flex-row {
  width: calc(100% / 4);
  text-align: center;
  padding: 0.5em 0.5em;
  border-right: solid 1px #d9d9d9;
  border-bottom: solid 1px #d9d9d9;
}

.rowspan {
  display: flex;
  flex-flow: row wrap;
  align-items: flex-start;
  justify-content: center;
}

.column {
  display: flex;
  flex-flow: column wrap;
  width: 75%;
  padding: 0;
}
.column .flex-row {
  display: flex;
  flex-flow: row wrap;
  width: 100%;
  padding: 0;
  border: 0;
  border-bottom: solid 1px #d9d9d9;
}
.column .flex-row:hover {
  background: #F5F5F5;
  transition: 500ms;
}

.flex-cell {
  width: calc(100% / 3);
  text-align: center;
  padding: 0.5em 0.5em;
  border-right: solid 1px #d9d9d9;
}

@media all and (max-width: 767px) {
  .flex-row {
    width: calc(100% / 3);
  }
  .flex-row.first {
    width: 100%;
  }

  .column {
    width: 100%;
  }
}
@media all and (max-width: 430px) {
  .flex-table .flex-row {
    border-bottom: 0;
  }
  .flex-table .flex-row:last-of-type {
    border-bottom: solid 1px #d9d9d9;
  }

  .header .flex-row {
    border-bottom: solid 1px;
  }

  .flex-row {
    width: 100%;
  }
  .flex-row.first {
    width: 100%;
    border-bottom: solid 1px #d9d9d9;
  }

  .column {
    width: 100%;
  }
  .column .flex-row {
    border-bottom: solid 1px #d9d9d9;
  }

  .flex-cell {
    width: 100%;
  }
}
    </style>
    <jsp:include page="/WEB-INF/header.jsp" />

</head>

<body>



    <div id="main-content" class = "table-container" role = "table">
        <h4 id="email"></h4>
            <h4 id="name"></h4>
            <h4 id="surname"></h4>
            <div class="container">

                <table id="statisticTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Test name</th>
                            <th>Correct anwers</th>
                            <th>Wrong answers</th>
                            <th>Date pass</th>
                        </tr>
                    </thead>
                    <tbody id="tableBody">

                    </tbody>
                </table>
            </div>
    </div>
</body>

</html>