<html>

<head>

    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
        var maxPage = 0;

        function setTestItems(offsetParam) {

            $(document).ready(function () {
                
                $(document).hide();
                var paginationOptions = { limit: 3, offset: offsetParam };
                
                $.getJSON("app/?command=TestsMeta", paginationOptions, function (tests) {
                    maxPage = tests.totalNumber / 3;
                    
                    $.each(tests.testItems, function (inx, test) {
                        var name = ' <h3 id = "testName" class = "name">' + test.name + '</h3>';
                        var lang = '<p id = "lang">' + 'Lang: ' + test.languageId + '</p>';
                        var duration = '<p id = "duration">' + 'Duration: ' + test.duration + '</p>';
                        var testDescription = '<p id = "testDescr" class = "descr">' + test.description + '</p>';
                        var butn = '<footer><button id="testItemBtnId" name="test" type="submit" class = "start-button" onclick= "redirectBtn(' + test.testItemId + ')"> Start </button></footer>';
                        var container = '<div id = "testContainer" class = "tests-container test">' + name + lang + duration + testDescription + butn + '</div>';

                        $("#test-items").prepend(container);
                    })
                    $(document).show(); 
                    paginationButtonVision();                 
                }).fail(function($jqXHR) {
                    if($jqXHR.status == 401) {
                        var data = JSON.parse($jqXHR.responseText);
                        window.location.replace(data.redirect);
                    } else {
                        console.log(jqXHR.status);
                    }
            });

            

            })
            

        }
    </script>

    <script type = "text/javascript">
       function redirectBtn(id) {
            var url = "/TestsProject/tests/test_session/?test=" + id;
            window.location = url;
        };
    </script>

    <script type="text/javascript">
        function paginationButtonVision() {            
                if (pageCounter <= 1) {
                    $('#prev').hide();
                } else {
                    $('#prev').show();
                }

                if (pageCounter >= maxPage) {
                    $('#next').hide();
                } else {
                    $('#next').show();
                }
        }
   
    </script> 

    <script type="text/javascript">
        var pageCounter = 1;
       

        function processPagination(isIncreasing) {

            if ( pageCounter <= maxPage) {


                if (isIncreasing) {
                    pageCounter++;
                }  else  {
                    pageCounter--;
                }

                
               
            }
            
            $('#test-items').empty();
            setTestItems(pageCounter);
            
            paginationButtonVision();

        }

    </script>

    <jsp:include page="/WEB-INF/header.jsp" />


</head>

<style type="text/css">
    .tests-containter {
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        display: flex;
        flex-flow: wrap;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        margin-top: 125px;
        border-color: black;
    }

    .test {
        border: 1px solid black;
        margin-left: 100px;
        box-shadow: 0 20px 40px -14px rgba(0, 0, 0, 0.25);
        border-radius: 13px;
        display: flex;
        flex: 1 1 auto;
        flex-direction: column;
        padding: 1rem;
        width: 550px;
        margin-top: 20px;
    }


    .name {
        margin-left: 200px;
    }

    .descr {
        flex: 1 1 auto;
        margin-bottom: 1.25rem;
    }

    .start-button {
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        width: 176px;
        height: 43px;
        background: #2CB3FE;
        border-radius: 10px;
        color: white;
    }

    .pagination {
        display: flex;
        align-content: center;
        justify-content: center;
        text-align: center;
        line-height: 38px
    } 

    .prev {         
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        width: 176px;
        height: 43px;
        background: #2CB3FE;
        border-radius: 10px;
        margin-top: 25px;
        margin-right: 300px;
    }

    
    .next {         
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        width: 176px;
        height: 43px;
        background: #2CB3FE;
        border-radius: 10px;
        margin-top: 25px;
        margin-left: 500px;
    }
    
    a {
        color: white;
    } 
</style>

<body onload="javascript:setTestItems(pageCounter)">

    <div id="test-items" class="tests-containter">
       
    </div>

    <div id="pagination" class="pagination">
        <div id = "next" class = "pagination next">
            <a href = "#" onclick='processPagination(true)' style = "text-decoration: none;">Next &raquo; </a> 
        </div>

        <div id = "prev" class = "pagination prev">
            <a href = "#" onclick='processPagination(false)' style = "text-decoration: none;"> &laquo; Pervious</a> 
        </div>
    </div>
   

</body>

</html>