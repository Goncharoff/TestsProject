<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <!-- <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->

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
        #main-content {
            height: 10em;
            position: relative;
            top: 650px;
        }


    </style>
    <jsp:include page="/WEB-INF/header.jsp" />

</head>

<body>



    <div id="main-content">
        <h4 id="email">
            </h3>
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