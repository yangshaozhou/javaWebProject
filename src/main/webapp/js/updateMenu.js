function changeGoods(nameId,priceId,descriptionId) {
    var urlSearchParams = new URLSearchParams(window.location.search)
    var id = urlSearchParams.get('id')

    var name = document.getElementById(nameId).value
    var price = document.getElementById(priceId).value
    var description = document.getElementById(descriptionId).value

    var imageInput = document.getElementById('img_modify');
    var imageFile = imageInput.files[0]
    var url = "/modifyGoods"

    var formData = new FormData();
    formData.append('name',name)
    formData.append('price', price);
    formData.append('description', description);
    formData.append('id', id);

    if (imageFile) {
        formData.append('image',imageFile);
    }

  var xhr = new XMLHttpRequest();
    xhr.open("POST",url,true)

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

    xhr.send(formData)
}


function  displayImage(input) {
    var imagePreview = document.getElementById("imagePreview")
    imagePreview.innerHTML = '';
    if(input.files && input.files[0]) {
        var reader = new FileReader()
        reader.onload = function (e) {
            var img = document.createElement('img')
            img.src = e.target.result
            img.style.width='100px'
            img.style.height='100px'
            img.style.height='auto'
            imagePreview.appendChild(img)
        }
        reader.readAsDataURL(input.files[0])
    }
}