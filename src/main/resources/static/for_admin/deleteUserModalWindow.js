$('#modal_delete_user_call').on('show.bs.modal', event => {
    let button = $(event.relatedTarget)
    let id = button.data('id')
    openDeleteModalWindow(id)
})


async function openDeleteModalWindow(id) {

    let user = await getUser(id)
    let formForDeleteUser = document.forms['form_for_delete_user']
    formForDeleteUser.addEventListener('button', closeDeleteModalWindow())

    formForDeleteUser.id_delete_user.value = user.id
    formForDeleteUser.username_delete_user.value = user.username
    formForDeleteUser.surname_delete_user.value = user.surname
    formForDeleteUser.age_delete_user.value = user.age
    formForDeleteUser.email_delete_user.value = user.email
    $('#selected_roles_of_delete_user').empty()

    await fetch(requestUrlForAllRoles)
        .then(res => res.json())
        .then(allRolesFromDB => {
            allRolesFromDB.forEach(roleForForm => {
                let element = document.createElement("option");

                element.text = roleForForm.name;
                element.value = roleForForm.id;

                $('#selected_roles_of_delete_user')[0].appendChild(element);
            })
        })

    function closeDeleteModalWindow() {
        formForDeleteUser.reset()
    }
}
