<#ftl encoding="UTF-8">
<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>
<h2><a href="?lang=ru">РУ</a></h2>
<h2><a href="?lang=en">EN</a></h2>
<h1><@spring.message 'sign_up_page.registration.title'/></h1>
<@spring.bind "userSignUpForm"/>
<#if namesErrorMessage ??>
   <p style=".error"> ${namesErrorMessage}</p>
</#if>
<div>
    <div>
        <form action="/signUp" method="post">
            <label>first name</label>
            <@spring.formInput "userSignUpForm.firstName"/>
            <@spring.showErrors "<br>" "error"/>
            <label>last name</label>
            <@spring.formInput "userSignUpForm.lastName"/>
            <@spring.showErrors "<br>" "error"/>
            <label>Email</label>
            <@spring.formInput "userSignUpForm.email"/>
            <@spring.showErrors "<br>" "error"/>
            <label>Password</label>
            <@spring.formInput "userSignUpForm.password"/>
            <@spring.showErrors "<br>" "error"/>
            <input type="submit" value="Sign up">
        </form>
    </div>

</div>
</body>
</html>