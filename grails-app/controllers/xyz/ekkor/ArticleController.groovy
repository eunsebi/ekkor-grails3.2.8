package xyz.ekkor

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ArticleController {

    ArticleService articleService
    SpringSecurityService springSecurityService
    UserService userService

    static responseFormats = ['html', 'json']

    static allowedMethods = [save   : "POST", update: ["PUT", "POST"], delete: ["DELETE", "POST"], scrap: "POST",
                             addNote: "POST", assent: ["PUT", "POST"], dissent: ["PUT", "POST"]]

    def beforeInterceptor = {
        response.characterEncoding = 'UTF-8' //workaround for https://jira.grails.org/browse/GRAILS-11830
    }

    def index(String code, Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort = params.sort ?: 'id'
        params.order = params.order ?: 'desc'
        params.query = params.query?.trim()

        def category = Category.get(code)

        if (category == null) {
            notFound()
            return
        }


        println "test"

    } // index

    protected void notFound() {

        withForm {
            html {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label', default: 'Article'), params.id])
                redirect( uri: '/')
            }
            json { render status: NOT_FOUND }
        }
    }

    def seq(Long id) {
        println "seq"
        redirect uri: "/article/${id}"
    }
}
