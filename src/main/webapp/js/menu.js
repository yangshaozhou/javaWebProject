document.getElementById("checkBtn").addEventListener("click",function (){
    event.preventDefault()
    var dishName = document.getElementById("user").value
    var xhr = new XMLHttpRequest();

    var url = "/showAllGoods?" + "search=" + dishName
    xhr.open("GET",url,true)
    xhr.onload = function () {
        if(xhr.status >=200 && xhr.status < 300) {
            console.log(xhr.responseText)
            document.documentElement.innerHTML = xhr.responseText
        }
        else {
            console.log("请求失败,",xhr.status)
        }
    }
    xhr.onerror = function () {
        console.log("网络错误");
    }
    xhr.send();
})

//跳转到添加菜品页面
document.getElementById("addButton").addEventListener("click",function ()  {
    window.location.href = "addmenu.jsp"
})