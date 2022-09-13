const authorizedUserHeader = document.getElementById('authorized_user_header')
const tableWithInformationAboutCurrentUser = document.getElementById('table_with_information_about_user')
const requestUrlForCurrentUser = 'http://localhost:8080/admin/api/users/current'
// Получение текущего пользователя

$(async function () {
    await getCurrentUser()
})

//Заполнение шапки с информацией о пользователе (email + roles), заполнение левой вкладки в нав. панели (User)
async function getCurrentUser() {
    fetch(requestUrlForCurrentUser)
        .then(res => res.json()
        )
        .then(currentUser => {
                if (currentUser != null) {
                    authorizedUserHeader.innerHTML = `
                    <b>
                        <span>${currentUser.email}</span>
                    </b>
                    <span> with roles: </span>
                    <span>${currentUser.roles.map(role => role.name)}</span>`

                    tableWithInformationAboutCurrentUser.innerHTML = `
                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>First Name</th>
                                            <th>Last Name</th>
                                            <th>Age</th>
                                            <th>Email</th>
                                            <th>Role</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>${currentUser.id}</td>
                                            <td>${currentUser.username}</td>
                                            <td>${currentUser.surname}</td>
                                            <td>${currentUser.age}</td>
                                            <td>${currentUser.email}</td>
                                            <td>${currentUser.roles.map(role => role.name)}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                    `
                }
            }
        )
}
