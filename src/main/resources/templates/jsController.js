$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8081/AddressBook"
    }).then(function(data) {
        console.log(data);
        $('.home').append(data.name);
    });
});