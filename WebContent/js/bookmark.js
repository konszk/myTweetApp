/**
 * 
 */

function changeImg(id){
    var image = document.getElementById(id).src;
    var mutter_id = id.substr(9);
    
    console
    if (image.indexOf("not_bookmark.png") != -1){
        document.getElementById(id).src = "./image/bookmark.jpg";
        $.ajax({
        	url: "http://localhost:8080/docoTsubu/Bookmark",
        	type: "POST",
        	data: {id: mutter_id, action: 'post'}
        }).done(function(result){
        	console.log("Success:" + result);
        }).fail(function(result){
        	console.log("Failed")
        });
    } else {
        document.getElementById(id).src = "./image/not_bookmark.png";
        $.ajax({
        	url: "http://localhost:8080/docoTsubu/Bookmark",
        	type: "POST",
        	data: {id: mutter_id, action: 'delete'}
        }).done(function(result){
        	console.log("Success:" + result);
        }).fail(function(result){
        	console.log("Failed")
        });
    }
}