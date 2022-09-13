const requestUrlForDeleteUser = 'http://localhost:8080/admin/api/users/'

$(async function () {
    await confirmDeleteUser();
})

function confirmDeleteUser() {
    const formWithDeleteUser = document.forms['form_for_delete_user']

    formWithDeleteUser.addEventListener('submit', event => {
        event.preventDefault();

        fetch(requestUrlForDeleteUser + formWithDeleteUser.id_delete_user.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                $('#close_delete_modal_form').click()
                allUsers()
            })
    })
}