

    $(".rateYo").each(function (e) {
    var rating = $(this).attr("data-rating");
    $(this).rateYo({
    onSet: function (rating) {
    ratingFunc(rating, $(this).next().next().val());
},
    rating: rating,
    // starWidth: "20px",
    // spacing: "5px",
    // precision: 2,
});
    $(this).rateYo('option', 'readOnly', true);
});
    function ratingFunc(rating){}
