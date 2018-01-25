function isValidJSON(str) {
    return str === null || str === undefined || str === '' || isJSON(str);
}
function isJSON(str) {
    if (typeof str == 'string') {
        try {
            var obj = JSON.parse(str);
            if (str.indexOf('{') > -1 && str.indexOf('}') > -1) {
                return true;
            } else {
                return false;
            }

        } catch (e) {
            console.log(e);
            return false;
        }
    }
    return false;
}

doSend = function () {
    var method = document.getElementById('rest-method').value.trim();
    var url = document.getElementById('rest-url').value.trim();
    var body = document.getElementById('rest-body').value.trim();
    var headers = document.getElementById('rest-headers').value.trim();
    var cookies = document.getElementById('rest-cookies').value.trim();
    var session = document.getElementById('rest-session').value.trim();
    var remoteAddress  = document.getElementById('rest-remoteAddress').value.trim();

    var host = document.getElementById('mq-host').value.trim();
    var port = document.getElementById('mq-port').value.trim();
    var username = document.getElementById('mq-username').value.trim();
    var password = document.getElementById('mq-password').value.trim();
    var queue = document.getElementById('mq-queue').value.trim();
    var appId = document.getElementById('message-appid').value.trim();
    var messageId = document.getElementById('message-messageId').value.trim();
    var replyTo = document.getElementById('message-replyTo').value.trim();
    if (!isJSON(body)) {
        alert("body must be a json !!");
        return false;
    }
    if(!isValidJSON(headers)){
        alert("headers must be null or json !!");
        return false;
    }
    if(!isValidJSON(cookies)){
        alert("cookies must be null or json !!");
        return false;
    }
    if(!isValidJSON(session)){
        alert("session must be null or json !!");
        return false;
    }
    /*if (deliveryInfo !=null && deliveryInfo !="" ) {
        console.log(deliveryInfo);
        if( !isJSON(deliveryInfo)){
            alert("deliveryInfo must be null or json !!");
        }
        return false;
    }*/
    $.ajax({
        url: "sendmq",
        type: "POST",
        async: false,
        data: {
            "method": method,
            "url": url,
            "body": body,
            "headers":headers,
            "cookies":cookies,
            "session":session,
            "remoteAddress":remoteAddress,
            "host": host,
            "port": port,
            "username": username,
            "password": password,
            "queue": queue,
            "appId":appId,
            "messageId":messageId,
            "replyTo":replyTo
        },
        success: function (e) {
            console.log("success",e);
        },
        error: function (e) {
            alert("fail!");
            console.log("fail",e);
        }
    });
};


