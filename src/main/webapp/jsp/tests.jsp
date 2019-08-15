<html>

<head>
  
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).hide();
             
                $.getJSON("app/?command=TestsMeta", function (tests) {

                $.each(tests, function (inx, test) {
                    var name = ' <h3 id = "testName" class = "name">' + test.name + '</h3>';
                    var lang = '<p id = "lang">' + 'Lang: ' + test.languageId + '</p>';
                    var duration = '<p id = "duration">' + 'Duration: ' + test.duration + '</p>';
                    var testDescription = '<p id = "testDescr" class = "descr">' + test.description + '</p>';
                    var butn = '<footer><button id="testItemId" name="test" type="submit" class = "start-button"> Start </button></footer>';
                    var container = '<div id = "testContainer" class = "tests-container test">' + name + lang + duration + testDescription + butn + '</div>';
                        
                    $("#test-items").prepend(container);
                })
                
                $(document).hide();
            
            })
        })
    </script>
    <script type = "text/javascript">
        var pageCounter = 0;
        var paginationParams = {limit: pageCounter, offset: 3};
        function processPagination() {
            $('#test-items').empty();
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
        flex-flow:wrap;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        margin-top: 125px;
        border-color:black;        
    }

    .test {
          border: 1px solid black;
          margin-left: 100px;    
          box-shadow: 0 20px 40px -14px rgba(0,0,0,0.25);
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
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

</style>

<body>

    <div id = "test-items" class = "tests-containter">
      
      <a class = "btnPrev" href = "#" class = "btn"> < Prevous </a>
      <a class = "btnNext" href = "#" class = "btn"> Next > </a>  
    </div>

  

</body>

</html>