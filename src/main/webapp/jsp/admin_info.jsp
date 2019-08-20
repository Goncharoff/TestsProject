<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <c:url value="/resources/img/unpictured.png" var="avatarUrl" />
    
    
    <script type="text/javascript">
         $("#body").hide(); 
        var totalNumberCorrect = 0;
        var totalNumberOfQuestions = 0;

        $(document).ready(function () {
                            
            $.getJSON("app/?command=UserProfile", function (user) {
                $("#email").attr('href', "mailto:" + user.userEmail).append(user.userEmail);
                $("#name").append(user.userName).append(" " + user.userSurname);

                $.each(user.userStatistics, function (i, item) {
                    
                    $("#statisticTable")
                          .append($('<div class="flex-table row" role="rowgroup">')
                          .append($('<div class="flex-row first" role="cell">').text(item.testName))
                          .append($('<div class="flex-row first" role="cell">').text(item.correctAnswers))
                          .append($('<div class="flex-row first" role="cell">').text(item.passedAnswers))
                          .append($('<div class="flex-row first" role="cell">').text(new Date(item.dateRecorded).toLocaleDateString("en-US")))
                        );

                         totalNumberCorrect += parseInt(item.correctAnswers);
                         totalNumberOfQuestions += parseInt(item.passedAnswers);
                });
                
                 $("#body").show();        
            }).fail(function($jqXHR) {
                if($jqXHR.status == 401) {
                    var data = JSON.parse($jqXHR.responseText);
                    window.location.replace(data.redirect);
                } else {
                    console.log(jqXHR.status);
                }
            });
        });
        
    </script>


    
<style>

img {
  border-radius: 50%;
  border: 2px solid black;
}

.container {
    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    display: flex;
    justify-content: center;
    align-items: left;
    flex-direction: row;
    margin-right: 125px;
    margin-top: 40px;
}

.user-info {
  flex-direction: column;
}

.user-info:frist-child {
  padding: 10px;  
}

.user-info:last-child {
  margin-top: -15px;
  padding: 0px;  
}

.sum-info {
    flex-direction: column;
}

div {
  box-sizing: border-box;
}

.table-container {
  display: block;
  margin: 2em auto;
  width: 90%;
  max-width:1000px;
  margin-top: 250px
}


.flex-table {
  display: flex;
  flex-flow: row wrap;
  border-left: solid 1px black;
  transition: 0.5s;
}
.flex-table:first-of-type {
  border-top: solid 1px black;
  border-left: solid 1px black;
}
.flex-table:first-of-type .flex-row {
  background: black;
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
  border-right: solid 1px black;
  border-bottom: solid 1px black;
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
  border-bottom: solid 1px black;
}
.column .flex-row:hover {
  background: black;
  transition: 500ms;
}

.flex-cell {
  width: calc(100% / 3);
  text-align: center;
  padding: 0.5em 0.5em;
  border-right: solid 1px black;
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
    border-bottom: solid 1px black;
  }

  .header .flex-row {
    border-bottom: solid 1px;
  }

  .flex-row {
    width: 100%;
  }
  .flex-row.first {
    width: 100%;
    border-bottom: solid 1px black;
  }

  .column {
    width: 100%;
  }
  .column .flex-row {
    border-bottom: solid 1px black;
  }

  .flex-cell {
    width: 100%;
  }
}

.center {
  text-align: center;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #black;}

}

</style>
    <jsp:include page="/WEB-INF/header.jsp" />

</head>

<body>
    <div  id = "body">
    
    <div id = "user-info-container" class = "container">
      
        <div class = "container image">
             <img src="${avatarUrl}" alt="avatar">
        </div>
        
        <div class = "container user-info">
            <h4 id="name" class = "user-info"></h4>  
            <h4 class = "user-info">Email:</h4>
            <a id="email" href="" class = "user-info"></a>
        </div>
        
        <div class = "container sum-info">
            <p id = "sumInfo" class = "sum-info"></p>
            <p id = "totalNumber" class = "sum-info"> </p>
        </div>
         
    </div>


    <div class="table-container" role="table" aria-label="Destinations" id = "statisticTable">
       
        <div class="flex-table header" role="rowgroup">
        <div class="flex-row first" role="columnheader">Test name</div>
        <div class="flex-row" role="columnheader">Correct anwers</div>
        <div class="flex-row" role="columnheader">Wrong answers</div>
        <div class="flex-row" role="columnheader">Date pass</div>
        
    </div>

   

</div>

</div>
  
</div>
</body>

</html>