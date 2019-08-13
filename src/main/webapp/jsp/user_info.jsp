<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
   
  <c:url value="/resources/img/unpictured.png" var="avatarUrl" />

    <script type="text/javascript">
        $(document).ready(function () {
            $.getJSON("/?command=UserProfile", function (user) {
                $("#email").attr('href', "mailto:" + user.userEmail).append(user.userEmail);
                $("#name").append(user.userName).append(" " + user.userSurname);
                
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


.table-container {
  display: flex;
    justify-content: center;
    align-items: center;
    padding: 250px;
}

</style>
    <jsp:include page="/WEB-INF/header.jsp" />

</head>

<body>

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
        
        </div> 
             
    </div>


    <div id="main-content" class = "table-container" role = "table">
      
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
</body>

</html>