
    event.preventDefault(event);
    var post_url = $(this).attr("action");
    var request_method = $(this).attr("method");

    var userInfo = {
        "userName": $("#user-name").val(),
        "userSurname": $("#user-surname").val(),
        "userEmail": $("#email-text").val(),
        "userPassword": $("#password-text").val()
    }


    $.ajax({
        url: post_url,
        type: "POST",
        data: JSON.stringify(userInfo),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            window.location.replace(data.redirect);
        }
    });
