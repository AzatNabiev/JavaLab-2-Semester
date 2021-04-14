<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Resume</title>
</head>
<body>
<#if errors??>
    <#list errors as error>
            ${error.getMessage()}
    </#list>
</#if>
<h1>Resume Page</h1>
<form method="post" action="/addResume">
    <label for="firstName">First Name</label>
    <input name="firstName" type="text" placeholder="Enter firstName">
    <label for="lastName">Last Name</label>
    <input name="lastName" type="text" placeholder="Enter lastName">
    <label for="email">Email</label>
    <input name="email" type="email" placeholder="Enter Email">
    <label for="telegram">Telegram</label>
    <input name="telegram" type="text" placeholder="Enter Telegram">
    <label for="age">Age</label>
    <input name="age" type="number" placeholder="Enter Age">
    <label for="aboutYourself">About Yourself</label>
    <textarea name="aboutYourself"></textarea>
    <button type="submit">Create resume</button>
</form>
</body>
</html>