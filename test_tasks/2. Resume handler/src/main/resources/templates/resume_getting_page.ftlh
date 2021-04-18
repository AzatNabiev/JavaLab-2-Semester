<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>All resumes</title>
</head>
<body>
<h1>Resumes:</h1>
<#if resumes ??>
    <#list resumes as resume>
        <p>----------------------</p>
        <p>Resume</p>
        <p>${resume.getFirstName()}</p>
        <p>${resume.getLastName()}</p>
        <p>${resume.getEmail()}</p>
        <p>${resume.getTelegram()}</p>
        <p>${resume.getAge()}</p>
        <p>${resume.getAboutYourself()}</p>
        <p>----------------------</p>
    </#list>
</#if>
<form method="get" action="/getAllResumes">
    <button type="submit">
        Get all resumes
    </button>
</form>
<h1>Resumes by ID</h1>
<#if resumeById ??>
    <p>${resumeById.getFirstName()}</p>
    <p>${resumeById.getLastName()}</p>
    <p>${resumeById.getEmail()}</p>
    <p>${resumeById.getTelegram()}</p>
    <p>${resumeById.getAge()}</p>
    <p>${resumeById.getAboutYourself()}</p>
    <form method="post" action="/deleteResume/${resumeById.get_id()}">
        <button type="submit">
            Delete Resume
        </button>
    </form>
    <form method="post" action="/changePost">
        <label for="firstName">First Name</label>
        <input name="firstName" type="text" value="${resumeById.getFirstName()}">
        <label for="lastName">Last Name</label>
        <input name="lastName" type="text" value="${resumeById.getLastName()}">
        <label for="email">Email</label>
        <input name="email" type="email" value="${resumeById.getEmail()}">
        <label for="telegram">Telegram</label>
        <input name="telegram" type="text" value="${resumeById.getTelegram()}">
        <label for="age">Age</label>
        <input name="age" type="number" value="${resumeById.getAge()}">
        <label for="aboutYourself">About Yourself</label>
        <textarea name="aboutYourself">${resumeById.getAboutYourself()}</textarea>
        <button type="submit">Change resume</button>
    </form>

</#if>
<form method="get" action="/getAllResumesById">
    <input name="post-id" type="text">
    <button type="submit">
        Get resume by Id
    </button>
</form>
</body>
</html>