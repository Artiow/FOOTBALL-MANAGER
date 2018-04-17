let dataNavMain = {
    textTourney: '',
    linkTourney: '/xxx',
    textLease: '',
    linkLease: '/xxx',
    textMatches: '',
    linkMatches: '/xxx',
    textPlaygrounds: '',
    linkPlaygrounds: '/xxx'
};

let NavMain = {
    data: function () { return dataNavMain; },
    template: `
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <a :href="linkTourney" class="tile">
                        <h2>ТУРНИРЫ<br/><br/></h2>
                        <p>{{ textTourney }}</p>
                    </a>
                    <a :href="linkMatches" class="tile">
                        <h2>ИГРАТЬ!<br/><br/></h2>
                        <p>{{ textMatches }}</p>
                    </a>
                </div>
                <div class="col-md-6">
                    <a :href="linkLease" class="tile">
                        <h2>АРЕНДА ПЛАТНЫХ<br/>СПОРТИВНЫХ ПЛОЩАДОК<br/></h2>
                        <p>{{ textLease }}</p>
                    </a>
                    <a :href="linkPlaygrounds" class="tile">
                        <h2>ТРЕНИРОВАТЬСЯ!<br/><br/></h2>
                        <p>{{ textPlaygrounds }}</p>
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