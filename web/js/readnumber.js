/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function readnum(num, audioElm) {
    var org_src = audioElm.src;
    if (/^[0-9]+$/) {
        var arr = new Array();
        for (var i = 0; i < num.length; i++) {
            arr[i] = num.charAt(i);
            //speak(arr[i], audioElm);
            window.setTimeout("speak(arr[i], audioElm)", 500);
        }
    }
}
//setTimeout(function(){},1000);
function speak(n, audioElm) {
    switch (n)
    {
        case "0":
            var src = audioElm.src.split("#")[0];
            audioElm.src = src + "#t=0.7,1.2";
            audioElm.play();
            break;

        case "1":
            var src = audioElm.src.split("#")[0];
            audioElm.src = src + "#t=1.5,2";
            audioElm.play();
            break;

        case "2":
            var src = audioElm.src.split("#")[0];
            audioElm.src = src + "#t=2.5,3";
            audioElm.play();
            break;
    }
    var show = document.getElementById("show");
    show.innerHTML += "<p>" + audioElm.src + "</p>";

}
