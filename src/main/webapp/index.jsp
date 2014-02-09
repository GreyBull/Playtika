<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>Calculator</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
    <input type="text" id="first" placeholder="Type first number..."/><br/>
    <input type="text" id="action" placeholder="Type action..."/><br/>
    <input type="text" id="second" placeholder="Type second number..."/><br/>
    <button onclick="return calculate();">Calculate</button><br/>
    <p id="result"></p>

    <script>
        function getXmlHttp(){
            var xmlhttp;
            try {
                xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (E) {
                    xmlhttp = false;
                }
            }
            if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
                xmlhttp = new XMLHttpRequest();
            }
            return xmlhttp;
        }

        function calculate() {
            var xmlhttp = getXmlHttp();
            xmlhttp.open("POST", "/calc", false);
            xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            var params ="first=" + document.getElementById("first").value + "&second=" + document.getElementById("second").value + "&action=";
            switch (document.getElementById("action").value) {
                case ("+"): params += "plus"; break;
                case ("-"): params += "minus"; break;
                case ("*"): params += "multiply"; break;
                case ("/"): params += "divide"; break;
               default: params += document.getElementById("action").value;
            }
            xmlhttp.send(params);
            if (xmlhttp.status == 200) {
                document.getElementById("result").innerHTML = xmlhttp.responseText;
            }
        }
    </script>
</body>
</html>