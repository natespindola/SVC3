<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Bootstrap 101 Template</title>

  <!-- Bootstrap -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="js/ui/jquery-ui.min.css">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>
<form action="javascript:;" method="get">
<h1>Hello, world!</h1>
<p>hello</p>
    <div class="ui-widget">
        <div>
            <input type=text id="query" placeholder="Start typing..." /><span id="indicator"></span>
            <div style="width:600px;height:700px;padding-bottom:100px;position:relative;background:#6c94b8;" id="output"></div>
            <label for="query" style="position:relative;margin-left:100px;margin-top:100px;">Tags: </label>
        </div>
        </div>
</form >
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/ui/jquery-ui.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // only begin once page has loaded
        $("#txtBookSearch").autocomplete({ // attach auto-complete functionality to textbox
            // define source of the data
            source: function (request, response) {
                // url link to google books, including text entered by user (request.term)
                console.log("here");
                var booksUrl = "https://www.googleapis.com/books/v1/volumes?q=" + encodeURIComponent(request.term);
                $.ajax({
                    url: booksUrl,
                    dataType: "jsonp",
                    success: function(data) {
                        response($.map(data.items, function (item) {
                            if (item.volumeInfo.authors && item.volumeInfo.title && item.volumeInfo.industryIdentifiers && item.volumeInfo.publishedDate)
                            {
                                return {
                                    // label value will be shown in the suggestions
                                    label: item.volumeInfo.title + ', ' + item.volumeInfo.authors[0] + ', ' + item.volumeInfo.publishedDate,
                                    // value is what gets put in the textbox once an item selected
                                    value: item.volumeInfo.title,
                                    // other individual values to use later
                                    title: item.volumeInfo.title,
                                    author: item.volumeInfo.authors[0],
                                    isbn: item.volumeInfo.industryIdentifiers,
                                    publishedDate: item.volumeInfo.publishedDate,
                                    image: (item.volumeInfo.imageLinks == null ? "" : item.volumeInfo.imageLinks.thumbnail),
                                    description: item.volumeInfo.description,
                                };
                            }
                        }));
                    }
                });
            },
            select: function (event, ui) {
                // what to do when an item is selected
                // first clear anything that may already be in the description
                $('#divDescription').empty();
                // we get the currently selected item using ui.item
                // show a pic if we have one
                if (item.image != '')
                {
                    $('#divDescription').append('<img src="' + ui.item.image + '" style="float: left; padding: 10px;">');
                }
                // and title, author, and year
                $('#divDescription').append('<p><b>Title:</b> ' + ui.item.title  + '</p>');
                $('#divDescription').append('<p><b>Author:</b> ' + ui.item.author  + '</p>');
                $('#divDescription').append('<p><b>First published year:</b> ' + ui.item.publishedDate  + '</p>');
                // and the usual description of the book
                $('#divDescription').append('<p><b>Description:</b> ' + ui.item.description  + '</p>');
                // and show the link to oclc (if we have an isbn number)
                if (ui.item.isbn && ui.item.isbn[0].identifier)
                {
                    $('#divDescription').append('<P><b>ISBN:</b> ' + ui.item.isbn[0].identifier + '</p>');
                    $('#divDescription').append('<a href="http://www.worldcat.org/isbn/' + ui.item.isbn[0].identifier + '" target="_blank">View item on worldcat</a>');
                }
            },
            minLength: 2 // set minimum length of text the user must enter
        });
    });
</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
</body>
</html>