function deleteGoods(id) {
    var url = "/deleteGoods?id="+id;
    console.log(url)

    var xhr = new XMLHttpRequest()

    xhr.open("GET",url,true);
    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300))  {
            if(xhr.responseText === "ok") {
                alert("删除成功")
                window.location.reload(true)
            } else {
                alert(xhr.responseText);
            }
        }
    }

    xhr.send();
}