let HeaderRegular = {
    props: [
        'caption'
    ],
    template: `
        <div class="h-frame">
            <div class="h-regular-container">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <a class="logo" href="/"><h1>БОЛЬШОЙ<br/>ВОРОНЕЖСКИЙ<br/>СПОРТИВНЫЙ<br/>ПОРТАЛ</h1></a>
                        </div>
                        <div class="col-sm-6">
                            <slot></slot>
                        </div>
                    </div>
                </div>
            </div>
            <div class="h-regular-info">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <h2>{{ caption }}</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
    `
};

new Vue({
    el: 'header',
    components: {
        'header-regular': HeaderRegular
    }
});