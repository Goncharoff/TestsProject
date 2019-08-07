<html>

<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $.getJSON("/?command=TestsMeta", function (tests) {
                $.each(tests, function (inx, test) {
                    $("#row_div").append('<div class = "column" >' +
                        "<div id = " + test.testItemId + ' class = "card">' +
                        "<h3>" + test.name + "</h3>" +
                        "<p>" + test.theme + "</p>" +
                        "<p>" + test.description + "</p>" +
                        '<form action="/?command=TestSession">' +
                        '<input type="hidden" name="command" value="TestSession" />' +
                        '<button id = "test-item" name = "test" type="submit" value = ' + test.testItemId + '> Start </button> ' +
                        '</form>')
                })
            })
        })
    </script>

</head>

<style type="text/css">
    * {
        box-sizing: border-box;
    }

    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    /* Float four columns side by side */
    .column {
        float: bottom;
        width: 45%;
        height: 250px;
        padding: 25px 10px;
    }

    /* Remove extra left and right margins, due to padding in columns */
    .row {
        margin: 0 -5px;
        padding: 100px;
    }

    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }

    /* Style the counter cards */
    .card {
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        /* this adds the "card" effect */
        padding: 16px;
        text-align: center;
        background-color: #f1f1f1;
    }

    /* Responsive columns - one column layout (vertical) on small screens */
    @media screen and (max-width: 600px) {
        .column {
            width: 100%;
            display: block;
            margin-bottom: 20px;
        }
    }

    #test-item {
        width: 176px;
        height: 43px;
        background: #2CB3FE;
        border-radius: 10px;
    }
</style>

<body>

    <div id="row_div" class="row">

    </div>

</body>

</html>