
$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        // Get form
        var form = $('#fileUploadForm')[0];

		// Create an FormData object
        var data = new FormData(form);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/upload",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
            	result = JSON.parse(data)
            	// Clean the div so it does not keep adding
            	$('#divFilelist').html(''); 
            	// append the file
            	  $('#divFilelist').append('<a href="' + result.url + '">' + result.name + '</a>' ); 
/*            	  $('#divFilelist').append('<a href="#" onClick="deleteFunction(\""' + result.name + '\")" > Delete' + result.name + '</a>' );*/  
            	/*});*/
            },
            error: function (e) {

                $("#result").text(e.responseText);
                console.log("ERROR : ", e);


            }
        });
        
               
    });
    $('#getfiles').click ( function (event) {
    	  //  get api call
    	 $.ajax({
             type: "GET",
             url: "/files?name=" + $('#getfiles').val(),
                  success: function (data) {
                	//  $("#result").text(data);
                 // or window.location.href = data.fileUrl;
                	  for ( var i = 0; i < data.length; i++ ) {
                		  $('#result').append ('<a href="' + data[i].name + '</a>' );
                		  }
             },
             error:function (xhr, ajaxOptions, thrownError) {
                 console.log("in error");
             } 
         });
    });
    $('#del').click ( function (event) {
        $.ajax({
            type: "DELETE",
            url: "/delete?name=" + $('#deleteFileName').val(),
            /*data: {_method: 'DELETE'},*/  
            success: function (data) {  
                alert(data);
            }
        });
        }); 
          
   
});
