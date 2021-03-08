<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">

    <div>Our Tube</div>

    <div> <a href="/signUp">Sign Up</a></div>

    <div> <a href="/signIn">Sign In</a></div>

    <div>
        <form action="/uploadVideo" method="post" enctype="multipart/form-data">
            <input type="file" name="file" placeholder="Choose the file">
            <input type="text" name="name" placeholder="Write the name">
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>