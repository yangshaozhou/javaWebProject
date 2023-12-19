function changeGoods(nameId,priceId,descriptionId) {
    var urlSearchParams = new URLSearchParams(window.location.search)
    var id = urlSearchParams.get('id')
    console.log("nameId",nameId)

    var name = document.getElementById(nameId).value
    var price = document.getElementById(priceId).value
    var description = document.getElementById(descriptionId).value
    var url = "/modifyGoods"


    var xhr = new XMLHttpRequest()
    xhr.open("POST",url,true)
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300))  {
            if(xhr.responseText === "ok") {
                alert("修改成功")
                window.location.reload()
            } else {
                alert(xhr.responseText);
            }
        }
    }

    var params = "name=" + name +
        "&price=" + price +
        "&description=" + description + "&id=" + id;

    console.log(params)

    xhr.send(params)
}