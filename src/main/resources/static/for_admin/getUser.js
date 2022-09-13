async function getUser(id) {
    let requestUrlForGettingUser = 'http://localhost:8080/admin/api/users/' + id

    let response = await fetch(requestUrlForGettingUser)
        .then(res => res.json())
    return response
}