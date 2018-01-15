let dataNavMain = {
    textMatches: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    linkMatches: '/matches',
    textPlaygrounds: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    linkPlaygrounds: '/playgrounds',
    textLease: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    linkLease: '/lease'
};

let NavMain = {
    data: function () { return dataNavMain; },
    template: `
        <div class="container">
            <div class="row hidden-sm hidden-xs">
                <div class="col-md-6">
                    <a :href="linkMatches" class="tile tile-sm">
                        <h2>ИГРАТЬ!</h2>
                        <p>{{ textMatches }}</p>
                    </a>
                    <a :href="linkPlaygrounds" class="tile tile-sm">
                        <h2>ТРЕНИРОВАТЬСЯ!</h2>
                        <p>{{ textPlaygrounds }}</p>
                    </a>
                </div>
                <div class="col-md-6">
                    <a :href="linkLease" class="tile tile-lg">
                        <h2>АРЕНДА<br/>ПЛАТНЫХ<br/>СПОРТИВНЫХ<br/>ПЛОЩАДОК</h2>
                        <p>{{ textLease }}</p>
                    </a>
                </div>
            </div>
            <div class="row visible-sm visible-xs">
                <div class="col-sm-6">
                    <a :href="linkMatches" class="tile tile-sm">
                        <h2>ИГРАТЬ!</h2>
                        <p>{{ textMatches }}</p>
                    </a>
                </div>
                <div class="col-sm-6">
                    <a :href="linkPlaygrounds" class="tile tile-sm">
                        <h2>ТРЕНИРОВАТЬСЯ!</h2>
                        <p>{{ textPlaygrounds }}</p>
                    </a>
                </div>
            </div>
            <div class="row visible-sm visible-xs">
                <div class="col-sm-12">
                    <a :href="linkLease" class="tile tile-lg">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>АРЕНДА<br/>ПЛАТНЫХ<br/>СПОРТИВНЫХ<br/>ПЛОЩАДОК</h2>
                            </div>
                            <div class="col-sm-6">
                                <p>{{ textLease }}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    `
};

new Vue({
    el: 'nav',
    components: {
        'nav-main': NavMain
    }
});