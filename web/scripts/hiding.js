/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function show()
{
    var status = document.getElementById("status");
    status.style.visibility = "visible";
}

function hide()
{
    var status = document.getElementById("status");
    status.style.visibility = "hidden";
}

function showHideItems()
{
    var status = document.getElementById("status");
    status.style.visibility = (status.style.visibility=="hidden") ? "visible" : "hidden";
}




