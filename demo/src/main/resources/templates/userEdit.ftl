<#import "parts/common.ftl" as c>

<@c.page>
Edit of User

<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}">
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}"
                      ${user.roles?seq_contains(role)?string("cheked", "")}>${role}</label>
    </div>
    </#list>
    <input type="text" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="csrf">
    <button type="submit">Save</button>
</form>
<a href="/">Back to Home</a>
</@c.page>