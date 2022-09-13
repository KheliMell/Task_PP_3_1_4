const requestUrlForInformationAboutCurrentUser = "http://localhost:8080/user/api/current"
const authorizedUserHeader = document.getElementById('authorized_user_header_for_user')

$(async function () {
    await getInformationAboutCurrentUser();
});

async function getInformationAboutCurrentUser() {
    fetch(requestUrlForInformationAboutCurrentUser)
        .then(res => res.json())
        .then(currentUser => {
            if (currentUser != null) {
                authorizedUserHeader.innerHTML = `
                    <b>
                        <span>${currentUser.email}</span>
                    </b>
                    <span> with roles: </span>
                    <span>${currentUser.roles.map(role => role.name)}</span>
                `
            }
            let user = `$(
                <tr>
                    <td>${currentUser.id}</td>
                    <td>${currentUser.username}</td>
                    <td>${currentUser.surname}</td>
                    <td>${currentUser.age}</td>
                    <td>${currentUser.email}</td>
                    <td>${currentUser.roles.map(role => role.name)}</td>
                </tr>
                `
            $('#user_panel_body').append(user)
        })
}
