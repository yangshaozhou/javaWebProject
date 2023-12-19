/**
 * 修改订单状态
 * @param orderId
 */
function send(orderId) {
    var s = document.getElementById("action_"+orderId);
    var url = "ChangeOrderStateAdmin?orderId=" + orderId + "&state=2"

    console.log(url)
    var xhr = new XMLHttpRequest()
    xhr.open("POST",url,true)

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && (xhr.status >= 200 && xhr.status < 300))  {
            if(xhr.responseText === "ok") {
                document.getElementById('state_' + orderId).innerHTML = "正在派送"
                s.style.display = "none"
                document.getElementById('state_color_' + orderId).className = 'mdui-chip-icon mdui-color-blue'
            } else {
                alert('修改失败,请联系管理员');
            }
        }
    }
    xhr.send()
}