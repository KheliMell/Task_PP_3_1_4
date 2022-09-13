const requestForAllUsers = 'http://localhost:8080/admin/api/users'

$(async function () {
    await allUsers();
});

const tableWithAllUsers = $('#body_all_users_table')

async function allUsers() {
    tableWithAllUsers.empty()

    fetch(requestForAllUsers)
        .then(res => res.json())
        .then(listOfAllUsers => {
            listOfAllUsers.forEach(user => {
                let columnWithCurrentUser = `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.surname}</td>     
                            <td>${user.age}</td>                                          
                            <td>${user.email}</td>
                            <td>${user.roles.map(role => role.name)}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="button_edit_user"
                                data-action="modal_edit_user_call" data-id="${user.id}" data-target="#modal_edit_user_call">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="button_delete_user"
                                data-action="modal_delete_user_call" data-id="${user.id}" data-target="#modal_delete_user_call">Delete</button>
                            </td>
                        </tr>
                `
                tableWithAllUsers.append(columnWithCurrentUser)
            })
        })
}