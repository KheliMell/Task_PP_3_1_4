$('#modal_edit_user_call').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget)
    let id = button.data('id')
    openEditModalWindow(id)
})


async function openEditModalWindow(id) {

    let user = await getUser(id)
    let formForEditUser = document.forms['form_for_edit_user']
    formForEditUser.addEventListener('button', closeEditModalWindow())

    formForEditUser.id_edit_user.value = user.id
    formForEditUser.username_edit_user.value = user.username
    formForEditUser.surname_edit_user.value = user.surname
    formForEditUser.age_edit_user.value = user.age
    formForEditUser.email_edit_user.value = user.email
    $('#selected_roles_of_edit_user').empty()

    await fetch(requestUrlForAllRoles)
        .then(res => res.json())
        .then(allRolesFromDB => {
            allRolesFromDB.forEach(roleForForm => {
                let element = document.createElement("option");

                element.text = roleForForm.name;
                element.value = roleForForm.id;

                $('#selected_roles_of_edit_user')[0].appendChild(element);
            })
        })

    function closeEditModalWindow() {
        formForEditUser.reset()
    }
}
