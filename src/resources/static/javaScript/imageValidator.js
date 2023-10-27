document.getElementById("imageError").style.display = "none";
// onChange event for image input
$(".img").on("change", function () {
// console.log("image changed "+this.files[0].size);
    if(this.files[0].size > 5000000){
        // gets rid of the img src if the image is too big
        $(".img").val("");
        // error message
        document.getElementById("imageError").style.display = "block";
        // style the alert-warning
        document.getElementById("imageError").style.margin = "auto";
        document.getElementById("imageError").style.width = "20rem";

        // adds style to the image input
        $("#imageError").html("Image size is too big");
    } else{
        document.getElementById("imageError").style.display = "none";
    };
});