<#import "parts/common.ftl" as c>

<@c.page>
List of Users
<table>
    <thead>
        <tr>
            <th><strong>Username</strong></th>
            <th><strong>Role</strong></th>
            <th>User Tasks</th>
        </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr >
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>


        </tr>
    </#list>
    </tbody>
    <a href="/">Back to Home</a>
</table>
</@c.page>