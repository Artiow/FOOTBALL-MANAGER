let AuthCompleted = {
    template: `
        <div class="auth auth-completed">
            <a href="/personalpage"><i class="fa fa-user" aria-hidden="true"></i><slot></slot></a>
            <a href="/logout"><i class="fa fa-sign-out" aria-hidden="true"></i><p>ВЫЙТИ</p></a>
        </div>
    `
};
let AuthFailed = {
    template: `
        <div class="auth auth-failed">
            <a href="/login"><i class="fa fa-sign-in" aria-hidden="true"></i><p>ВОЙТИ</p></a>
        </div>
    `
};

new Vue({
    el: '.h-frame',
    components: {
        'auth-completed': AuthCompleted,
        'auth-failed': AuthFailed
    }
});