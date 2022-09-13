const requestUrlForAllRoles = 'http://localhost:8080/admin/api/roles'
const requestUrlForSaveNewUser = 'http://localhost:8080/admin/api/users'

$(async function () {
    await createNewUser();
})

async function createNewUser() {
    await fetch(requestUrlForAllRoles)
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let element = document.createElement("option");
                element.text = role.name;
                element.value = role.id;
                $('#newUserRoles')[0].appendChild(element);
            })
        })

    const formForNewUser = document.forms["form_for_new_user"]

    formForNewUser.addEventListener('submit', addNewUser)

    function addNewUser(event) {
        event.preventDefault()
        let newUserRoles = []
        for (let i = 0; i < formForNewUser.roles.options.length; i++) {
            if (formForNewUser.roles.options[i].selected) {
                if (formForNewUser.roles.options[i].text === 'ADMIN') {
                    for (let j = 0; j < formForNewUser.roles.options.length; j++) {
                        newUserRoles.push({
                            id: formForNewUser.roles.options[j].value,
                            name: formForNewUser.roles.options[j].text
                        })
                    }
                } else {
                    newUserRoles.push({
                        id: formForNewUser.roles.options[i].value,
                        name: formForNewUser.roles.options[i].text
                    })
                }
            }
        }

        fetch(requestUrlForSaveNewUser, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: formForNewUser.username.value,
                surname: formForNewUser.surname.value,
                age: formForNewUser.age.value,
                email: formForNewUser.email.value,
                password: formForNewUser.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            formForNewUser.reset()
            allUsers()
        })
    }

}