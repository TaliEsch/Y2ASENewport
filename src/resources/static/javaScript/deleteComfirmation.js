$("form").submit( function (e) {
    // ask for confirmation, so they can choose from continue or cancel
    console.log("delete button clicked");
    if (confirm("Are you sure you want to delete this?")) {
        // if they click continue, then delete the post
        return true;
    } else {
        // if they click cancel, then prevent the form from submitting
        e.preventDefault();
    }
});