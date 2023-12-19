function addGoods(nameId,priceId,descriptionId) {
    var name = document.getElementById(nameId).value
    var price = document.getElementById(priceId).value
    var description = document.getElementById(descriptionId).value
    var url = "/addGoods"


    var xhr = new XMLHttpRequest()

    xhr.open("POST",url,true)
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300))  {
            if(xhr.responseText === "ok") {
                alert("添加成功")
                window.location.reload()
            } else {
                alert(xhr.responseText);
            }
        }
    }

    var params = "name=" + name +
        "&price=" + price +
        "&description=" + description;

    xhr.send(params)
}

//返回页面

function goback() {
    window.location.href = "menu.jsp"
}