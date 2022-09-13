const requestUrlForPatchUser = 'http://localhost:8080/admin/api/users'

$(async function () {
    await confirmEditUser();
})

function confirmEditUser() {
    const formWithEditUser = document.forms['form_for_edit_user']

    formWithEditUser.addEventListener('submit', event => {
        event.preventDefault();

        let newRolesForEditUser = []
        for (let i = 0; formWithEditUser.roles.options.length > i; i++) {
            if (formWithEditUser.roles.options[i].selected) {
                if (formWithEditUser.roles.options[i].text === 'ADMIN') {
                    for (let j = 0; j < formWithEditUser.roles.options.length; j++) {
                        newRolesForEditUser.push({
                            id: formWithEditUser.roles.options[j].value,
                            name: formWithEditUser.roles.options[j].text
                        })
                    }
                } else {
                    newRolesForEditUser.push({
                        id: formWithEditUser.roles.options[i].value,
                        name: formWithEditUser.roles.options[i].text
                    })
                }
            }
        }

        fetch(requestUrlForPatchUser, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formWithEditUser.id_edit_user.value,
                username: formWithEditUser.username_edit_user.value,
                surname: formWithEditUser.surname_edit_user.value,
                age: formWithEditUser.age_edit_user.value,
                email: formWithEditUser.email_edit_user.value,
                password: formWithEditUser.password_edit_user.value,
                roles: newRolesForEditUser
            })
        })
            .then(() => {
                $('#close_edit_modal_form').click()
                allUsers()
            })
    })
}