<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
       
       <script type="text/javascript">           
        
            function startTimer(duration, display) {
                var timer = duration, minutes, seconds;
                 
                setInterval(function () {
                        minutes = parseInt(timer / 60, 10);
                        seconds = parseInt(timer % 60, 10);

                        minutes = minutes < 10 ? "0" + minutes : minutes;
                        seconds = seconds < 10 ? "0" + seconds : seconds;

                        display.textContent = minutes + ":" + seconds;

                        if (--timer < 0) {
                            timer = duration;
                        }
                }, 1000);
            }
            
             window.onload = function () {
                var minutes = 60 * 5;
                var display = document.querySelector('#time');
                startTimer(minutes, display);
                getTest();
             };
 
        </script>


  
</head>


    <script type = "text/javascript">
        
        var getUrlParameter = function getUrlParameter(sParam) {
              var sPageURL = window.location.search.substring(1),
              sURLVariables = sPageURL.split('&'),
              sParameterName,
             i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
                }
            }
        };

        function getTest() {    
               

            var pathId = { "testId" : getUrlParameter('test')};

                $.getJSON('<c:url value = "/app/?command=TestSession"/>', pathId, function (test) {
                        startSession(test);
                });
                   
        }

        function startSession(myQuestions) {

            function buildQuiz() {
                // we'll need a place to store the HTML output
                var output = [];

                // for each question...
                myQuestions.forEach((currentQuestion, questionNumber) => {
                // we'll want to store the list of answer choices
                var answers = [];

                // and for each available answer...
                for (letter in currentQuestion.answers) {
                    // ...add an HTML radio button
                    answers.push(
                    `<label>
                        <input type="radio" name="question` + questionNumber + `" value="` + letter + `">
                        ` + letter + `:
                        ` + currentQuestion.answers[letter].answer +`
                    </label>`
                    );
                }
                               

                // add this question and its answers to the output
                output.push(
                        `<div class="slide">
                            <div class="question">` + currentQuestion.text +  `</div>
                            <div class="answers">` + answers.join("") + `</div>
                        </div>`
                    );
                });

                // finally combine our output list into one string of HTML and put it on the page
                quizContainer.innerHTML = output.join("");
            }

            function showResults() {
                    var resultJsonAnswers = [];
                    var testItemsResult = [];
                    // gather answer containers from our quiz
                    const answerContainers = quizContainer.querySelectorAll(".answers");            
                    
                    // keep track of user's answers
                    let numCorrect = 0;

                    // for each question...
                    myQuestions.forEach((currentQuestion, questionNumber) => {
                        // find selected answer
                        const answerContainer = answerContainers[questionNumber];   
                        const selector = 'input[name=question' + questionNumber + ']:checked';
                        const userAnswer = (answerContainer.querySelector(selector) || {}).value;

                        testItemsResult = [...testItemsResult, {
                                            "testId" : currentQuestion.id, 
                                            "answerId": currentQuestion.answers[userAnswer].id
                                        }];



                        // if answer is correct
                        if (userAnswer === currentQuestion.correctAnswer) {
                            // add to the number of correct answers
                            numCorrect++;

                            // color the answers green
                            answerContainers[questionNumber].style.color = "lightgreen";
                        } else {
                            // if answer is wrong or blank
                            // color the answers red
                            answerContainers[questionNumber].style.color = "red";
                        }
                });
                 
                  
                resultJsonAnswers = {testItemsResult, 
                        "testItemId" : getUrlParameter('test')};
                 postDataToCheck(resultJsonAnswers);
               // show number of correct answers out of total
                resultsContainer.innerHTML = numCorrect + ` out of ` + myQuestions.length;
            }

            function showSlide(n) {
                slides[currentSlide].classList.remove("active-slide");
                slides[n].classList.add("active-slide");
                currentSlide = n;
                
                if (currentSlide === 0) {
                    previousButton.style.display = "none";
                } else {
                    previousButton.style.display = "inline-block";
                }
                
                if (currentSlide === slides.length - 1) {
                    nextButton.style.display = "none";
                    submitButton.style.display = "inline-block";
                } else {
                    nextButton.style.display = "inline-block";
                    submitButton.style.display = "none";
                }
            }

            function showNextSlide() {
                showSlide(currentSlide + 1);
            }

            function showPreviousSlide() {
                showSlide(currentSlide - 1);
            }

            const quizContainer = document.getElementById("quiz");
            const resultsContainer = document.getElementById("results");
            const submitButton = document.getElementById("submit");

            // display quiz right away
            buildQuiz();

            const previousButton = document.getElementById("previous");
            const nextButton = document.getElementById("next");
            const slides = document.querySelectorAll(".slide");
            let currentSlide = 0;

            showSlide(0);

            // on submit, show results
            submitButton.addEventListener("click", showResults);
            previousButton.addEventListener("click", showPreviousSlide);
            nextButton.addEventListener("click", showNextSlide);
        };

        function postDataToCheck(testResult) {
                var post_url = '<c:url value = "/app/?command=CheckTest"/>';          

                $.ajax({
                    url: post_url,
                    type: "POST",
                    data: JSON.stringify(testResult),
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function (data) {
                       console.log(data)
                    }
                })

            // $.post( '<c:url value = "/app/?command=CheckTest"/>',  JSON.stringify(testResult), function(data) {
            // console.log(data);
        //});
    }

    </script>

<style type="text/css">
 p {
  text-align: center;
  font-size: 60px;
  margin-top: 0px;
}

body{
    font-size: 20px;
    font-family: 'Work Sans', sans-serif;
    color: #333;
  font-weight: 300;
  text-align: center;
  background-color: #f8f6f0;
}
h1{
  font-weight: 300;
  margin: 0px;
  padding: 10px;
  font-size: 20px;
  background-color: #444;
  color: #fff;
}
.question{
  font-size: 30px;
  margin-bottom: 10px;
}
.answers {
  margin-bottom: 20px;
  text-align: left;
  display: inline-block;
}
.answers label{
  display: block;
  margin-bottom: 10px;
}
button{
  font-family: 'Work Sans', sans-serif;
    font-size: 22px;
    background-color: #279;
    color: #fff;
    border: 0px;
    border-radius: 3px;
    padding: 20px;
    cursor: pointer;
    margin-bottom: 20px;
}
button:hover{
    background-color: #38a;
}





.slide{
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100%;
  z-index: 1;
  opacity: 0;
  transition: opacity 0.5s;
}
.active-slide{
  opacity: 1;
  z-index: 2;
}
.quiz-container{
  position: relative;
  height: 200px;
  margin-top: 40px;
}
</style>

<body>
    
    <div>
        <div>
             <span id="time">05:00</span>
        </div>
    </div>

    <div class="quiz-container">
            <div id="quiz"></div>
    </div>
    
    <button id="previous">Previous Question</button>
    <button id="next">Next Question</button>
    <button id="submit">Submit Quiz</button>
    
    <div id="results">
        
    </div>

</body>

</html>