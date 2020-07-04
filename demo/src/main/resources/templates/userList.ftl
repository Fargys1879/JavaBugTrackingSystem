<#import "parts/common.ftl" as c>

<@c.page>
List of Users
<table>
    <thead>
        <tr>
            <th><strong>Username</strong></th>
            <th><strong>Role</strong></th>
            <th>Edit User Role</th>
        </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr >
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">Edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>