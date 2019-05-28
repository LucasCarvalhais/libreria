$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/book/1"
    }).then(function(data) {
        $('.bookInfo-id').append(data.bookId);
        $('.bookInfo-title').append(data.title);
        $('.bookInfo-description').append(data.description);
        $('.bookInfo-author').append(data.author);
        $('.bookInfo-edition').append(data.edition);
    });
});