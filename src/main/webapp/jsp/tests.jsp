<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

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

    <jsp:include page="/WEB-INF/header.jsp" />


</head>

<style type="text/css">
    /* * {
        box-sizing: border-box;
    } */

    /* body {
        font-family: Arial, Helvetica, sans-serif;
    } */

    /* Float four columns side by side */
    /* .column {
        float: bottom;
        width: 45%;
        height: 250px;
        padding: 25px 10px;
    } */

    /* Remove extra left and right margins, due to padding in columns */
    /* .row {
        margin: 0 -5px;
        padding: 100px;
    } */

    /* Clear floats after the columns */
    /* .row:after {
        content: "";
        display: table;
        clear: both;
    } */

    /* Style the counter cards */
    /* .card {
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        padding: 16px;
        text-align: center;
        background-color: #f1f1f1;
    } */

    /* Responsive columns - one column layout (vertical) on small screens */
    /* @media screen and (max-width: 600px) {
        .column {
            width: 100%;
            display: block;
            margin-bottom: 20px;
        }
    } */

    #test-item {
        width: 176px;
        height: 43px;
        background: #2CB3FE;
        border-radius: 10px;
    }

    .parent {
        display: flex;
        flex-direction: column;
        background: #fff;
        position: center;
        padding: 100px;
        width: 750px;
        margin: 0 auto;
    }

    .child {
        padding: 50px;
        margin: 25px;
        background: #fff;
        flex-grow: 1;
        text-align: center;
        line-height: 15px;
        box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
    }
</style>

<body>

    <!-- <div id="row_div" class="row">

    </div> -->

    <div class="parent">
        <div class="child">
            <h1> test item name </h1>
            <p> nothing </p>
            <p><small> descriptuion for test </small></p>
            <button id="test-item" name="test" type="submit" value="1"> Start </button>
        </div>
        <div class="child">
            <h3> test item name 23 </h3>
            <p> nothing 23 </p>
            <p> descriptuion for test 23 </p>
            <button id="test-item" name="test" type="submit" value="2"> Start </button>
        </div>
    </div>

</body>

</html>