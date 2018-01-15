let HeaderMain = {
    template: `
        <div class="h-frame h-main-container">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <h1>БОЛЬШОЙ<br/>ВОРОНЕЖСКИЙ<br/>СПОРТИВНЫЙ<br/>ПОРТАЛ</h1>
                    </div>
                    <div class="col-sm-6">
                        <slot></slot>
                    </div>
                </div>
            </div>
        </div>
    `
};

new Vue({
    el: 'header',
    components: {
        'header-main': HeaderMain
    }
});