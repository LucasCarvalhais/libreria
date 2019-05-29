function bookInfo() {
    document.getElementById('bookInfo').style.display="none";
    document.getElementById('error').style.display="none";

    var id = document.getElementById("bookCode").value;
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            if (this.status == 200) {
                var data = JSON.parse(this.responseText);
                document.getElementById("bookInfo-id").innerHTML = data.bookId;
                document.getElementById("bookInfo-title").innerHTML = data.title;
                document.getElementById("bookInfo-description").innerHTML = data.description;
                document.getElementById("bookInfo-author").innerHTML = data.author;
                document.getElementById("bookInfo-edition").innerHTML = data.edition;
                document.getElementById('bookInfo').style.display="block";
            }
            if (this.status == 404) {
                document.getElementById('error').style.display="block";
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/book/" + id, true);
    xhttp.send();

}
