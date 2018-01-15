let dataFooter = {
    'fHeight': 0
};

let FooterRegular = {
    data: function () { return dataFooter; },
    props: [
        'linkService',
        'linkContacts',
        'linkFeedback',
        'srcVldfLogo',
        'linkVldf',
        'linkVldfVk',
        'linkVldfInstagram',
        'linkVldfYoutube'
    ],
    template: `
        <div class="f-container">
			<div class="f-upper">
                <div class="container">
                    <div class="row hidden-sm hidden-xs">
                        <div class="col-md-9">
                            <a :href="linkService"><i class="fa fa-info-circle" aria-hidden="true"></i>О СЕРВИСЕ</a>
                            <a :href="linkContacts"><i class="fa fa-id-card" aria-hidden="true"></i>КОНТАКТЫ</a>
                        </div>
                        <div class="col-md-3">
                            <a :href="linkFeedback">НАПИСАТЬ НАМ<i class="fa fa-envelope" aria-hidden="true"></i></a>
                        </div>
                    </div>
                    <div class="row visible-sm visible-xs">
                        <div class="col-sm-12">
                            <a :href="linkService"><i class="fa fa-info-circle" aria-hidden="true"></i>О СЕРВИСЕ</a>
                            <a :href="linkContacts"><i class="fa fa-id-card" aria-hidden="true"></i>КОНТАКТЫ</a>
                        </div>
                    </div>
                    <div class="row visible-sm visible-xs">
                        <div class="col-sm-12">
                            <a :href="linkFeedback"><i class="fa fa-envelope" aria-hidden="true"></i>НАПИСАТЬ НАМ</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="f-lower">
                <div class="container">
                    <div class="row hidden-sm hidden-xs">
                        <div class="col-md-9 link-ext">
                            <a :href="linkVldf"><img :src="srcVldfLogo" alt="ВЛДФ"/><p>ВОРОНЕЖСКАЯ ЛИГА ДВОРОВОГО ФУТБОЛА</p></a>
                        </div>
                        <div class="col-md-3 link-soc">
                            <a :href="linkVldfVk"><i class="fa fa-vk" aria-hidden="true"></i></a>
                            <a :href="linkVldfInstagram"> <i class="fa fa-instagram" aria-hidden="true"></i></a>
                            <a :href="linkVldfYoutube"><i class="fa fa-youtube" aria-hidden="true"></i></a>
                        </div>
                    </div>
                    <div class="row visible-sm visible-xs">
                        <div class="col-sm-12 link-ext">
                            <a :href="linkVldf"><img :src="srcVldfLogo" alt="ВЛДФ"/><p>ВОРОНЕЖСКАЯ ЛИГА ДВОРОВОГО ФУТБОЛА</p></a>
                        </div>
                    </div>
                    <div class="row visible-sm visible-xs">
                        <div class="col-sm-12 link-soc">
                            <a :href="linkVldfVk"><i class="fa fa-vk" aria-hidden="true"></i></a>
                            <a :href="linkVldfInstagram"> <i class="fa fa-instagram" aria-hidden="true"></i></a>
                            <a :href="linkVldfYoutube"><i class="fa fa-youtube" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>
                <div :style="{ height: fHeight + 'px' }"></div>
            </div>
        </div>
    `
};

new Vue({
    el: 'footer',
    components: {
        'footer-regular': FooterRegular
    }
});