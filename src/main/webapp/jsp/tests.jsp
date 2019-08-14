<html>

<head>
  
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).hide();

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
                $(document).hide();
            
            })
        })
    </script>

    <jsp:include page="/WEB-INF/header.jsp" />


</head>

<style type="text/css">
  .tests-containter {
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        display: flex;
        flex-flow:wrap;
        justify-content: center;
        align-items: center;
        flex-direction: row;
        margin-top: 125px;
        border-color:black;        
    }

    .test {
          border: 1px solid black;
          margin-left: 100px;    
          width: 550px;
          height:250px;
          border-radius: 13px;
          box-shadow: 3px 3px gray, -1em 0 .4em gray;         
    }

    .time {
        border: 1px solid black;
        width: 250px;
        height:250px;
        border-radius: 13px;
        box-shadow: 3px 3px gray, -1em 0 .4em gray;
        align-content: center;
        justify-content: center;
        text-align: center;
    }

    .name {
        margin-left: 200px;
    }

.descr {
    
}

.start {
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        width: 176px;
        height: 43px;
        background: #2CB3FE;
        border-radius: 10px;
        color: white;
        margin-left: 25px;
        margin-block-start: 50px;
}
    
.opts {
    flex-direction: row;
}
  
</style>

<body>

    <div id = "test-items" class = "tests-containter">
        <div class = "tests-container test"> 
            <h3 class = "name"> test item name 23 </h3>
            <p class = "opts"> Lang: ENG </p>
            <p class = "opts"> Duration: 25 m </p>
            <p class = "descr"> Descriptuion for test 23 Descriptuion for test 23 Descriptuion for test 23Descriptuion for test 23 
                Descriptuion for test 23 Descriptuion for test 23 Descriptuion for test 23Descriptuion for test 23 Descriptuion for test 23 Descriptuion for test 23 Descriptuion for test 23Descriptuion for test 23
             </p>
            <button id="test-item" name="test" type="submit" class = "start"> Start </button>
        </div>
    </div>


</body>

</html>