package xyz.ekkor

class MainController {

    def mainService

    def grailsCacheAdminService

    def index() {

        /*def mainBanners = Banner.where {
            type == BannerType.MAIN && visible == true
        }.list()

        def mainBanner = mainBanners ? randomService.draw(mainBanners) : null*/
        return [
                isIndex: true,
                choiceArticles: mainService.getChoiceArticles(),
                weeklyArticles: mainService.getWeeklyArticles(),
                questionsArticles: mainService.getQnaArticles(),
                communityArticles: mainService.getCommunityArticles(),
                //columnArticle: mainService.getColumnArticle(),
                //techArticle: mainService.getTechArticle(),
                techArticles: mainService.getTechArticles(),
                //informArticles: mainService.getInformArticles(),
                //classArticles: mainService.getClassArticles(),
                //leaderArticles: mainService.getLeaderArticles(),
                //maintArticles: mainService.getMaintArticles(),
                //promoteArticles: mainService.getPromoteArticles(),
                //mainBanner : mainBanner
        ]

    } // index

    def flush() {
        grailsCacheAdminService.clearAllCaches()
    }
}
