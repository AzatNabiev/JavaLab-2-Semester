<#ftl encoding="UTF-8">
<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>
<h2><a href="?lang=ru">РУ</a></h2>
<h2><a href="?lang=en">EN</a></h2>
<h1><@spring.message 'sign_in_page.authorization.title'/></h1>
<@spring.bind 'userSignInForm'/>
<#if namesErrorMessage ??>
    <p style=".error">${namesErrorMessage}</p>
</#if>
<div>
    <div>
        <form action="/signIn" method="post">
            <label><@spring.message 'sign_up_page.email'/></label>
            <@spring.formInput "userSignInForm.email"/>
            <@spring.showErrors "<br>" "error"/>
            <label><@spring.message 'sign_up_page.password'/></label>
            <@spring.formInput "userSignInForm.password"/>
            <@spring.showErrors "<br>" "error"/>
            <input type="submit" value='<@spring.message 'sign_up_page.signUpButton'/>'>
        </form>
    </div>

</div>
</body>
</html>