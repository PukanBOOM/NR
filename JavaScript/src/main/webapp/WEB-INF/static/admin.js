$(function() {
    getTableWithUsers()
    getDefaultModal()
    addNewUser()
})
const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findAllUsers: async() => fetch('admin'),
    deleteUser: async (id) => await fetch(`admin/${id}`, {method: 'DELETE', headers: userFetchService.head}),
    findOneUser: async (id) => await fetch(`admin/${id}`),
    addNewUser: async (user) => await fetch('/', {method: 'POST', headers: userFetchService.head, body: JSON.stringify(user)}),
    updateUser: async (user, id) => await fetch(`admin/${id}`, {method: 'PUT', headers: userFetchService.head,body: JSON.stringify(user)})
}

async function getTableWithUsers() {
    let table = $('#userTable tbody');
    table.empty();

    await userFetchService.findAllUsers()
        .then(res => res.json())
        .then(users => {
            users.forEach( user => {
                let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.password}</td>
                            <td>
                                <button type="button" data-userid="${user.id}" data-action="edit" class="btn btn-primary"
                                data-toggle="modal" data-target="#modal" >Edit</button>
                            </td>
                            <td>
                                <button type="button" data-userid="${user.id}" data-action="delete" class="btn btn-danger"
                                data-toggle="modal" data-target="#modal">Delete</button>
                            </td>
                        </tr>
                )`;
                table.append(tableFilling);
            })
        })
    $("#userTable").find('button').on('click', (event) => {
        let defaultModal = $('#modal');

        let targetButton = $(event.target);
        let buttonUserId = targetButton.attr('data-userid');
        let buttonAction = targetButton.attr('data-action');

        defaultModal.attr('data-userid', buttonUserId);
        defaultModal.attr('data-action', buttonAction);
        defaultModal.modal('show');
    })
}


async function deleteUser(modal, id) {
    await userFetchService.deleteUser(id)
    modal.find('.modal-title').html('');
    modal.find('.modal-body').html('User was deleted');
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(closeButton);
    userFetchService.findOneUser().then(user => {
        modal.find('#id').val(user.id);
        modal.find('#email').val(user.username).prop('disabled', true);
        modal.find('#name').val(user.email).prop('disabled', true);
        modal.find('#password').parent().remove();
        getTableWithUsers()
    })
}
async function getDefaultModal() {
    $('#modal').modal({
        keyboard: true,
        backdrop: "static",
        show: false
    }).on("show.bs.modal", (event) => {
        let thisModal = $(event.target);
        let userid = thisModal.attr('data-userid');
        let action = thisModal.attr('data-action');
        switch (action) {
            case 'edit':
                editUser(thisModal, userid);
                break;
            case 'delete':
                deleteUser(thisModal, userid);
                break;
        }
    }).on("hidden.bs.modal", (e) => {
        let thisModal = $(e.target);
        thisModal.find('.modal-title').html('');
        thisModal.find('.modal-body').html('');
        thisModal.find('.modal-footer').html('');
    })
}
async function editUser(modal, id) {
    let preuser = await userFetchService.findOneUser(id);
    let user = preuser.json();

    modal.find('.modal-title').html('Edit user');

    let editButton = `<button  class="btn btn-outline-success" id="editButton">Edit</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(editButton);
    modal.find('.modal-footer').append(closeButton);

    user.then(user => {
        let bodyForm = `
            <form class="form-group" id="editUser">
                <input type="text" class="form-control" id="id" name="id" value="${user.id}" disabled><br>
                <input class="form-control" type="text" id="username" value="${user.username}"><br>
                <input class="form-control" type="text" id="email" value="${user.email}"><br>
                <input class="form-control" type="password" id="password" value="${user.password}"><br>
            </form>
        `;
        modal.find('.modal-body').append(bodyForm);
    })

    $("#editButton").on('click', async () => {
        let id = modal.find("#id").val().trim();
        let username = modal.find("#username").val();
        let email = modal.find("#email").val().trim();
        let password = modal.find("#password").val();
        let data = {
            id: id,
            username: username,
            email: email,
            password: password
        }
        const response = await userFetchService.updateUser(data, id);
        modal.modal('hide');
        getTableWithUsers();
    })
}
async function addNewUser() {
    $('#addNewUserButton').click(async () =>  {
        let addUserForm = $('#form')
        let username = addUserForm.find("#username").val();
        let email = addUserForm.find("#email").val().trim();
        let password = addUserForm.find("#password").val();
        let data = {
            username: username,
            email: email,
            password: password
        }
        const response = await userFetchService.addNewUser(data);
        getTableWithUsers()
        addUserForm.find('#AddNewUserLogin').val('');
        addUserForm.find('#AddNewUserPassword').val('');
        addUserForm.find('#AddNewUserAge').val('');
    })
}