package xyz.ekkor

import grails.plugin.springsecurity.SpringSecurityService

class SideBarTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    SpringSecurityService springSecurityService
    NotificationService notificationService

    def sidebar = { attrs, body ->

        Category category = attrs.category
        int notificationCount = 0

        if (springSecurityService.loggedIn) {
            Avatar avatar = Avatar.load(springSecurityService.principal.avatarId)
            notificationCount = notificationService.count(avatar)
        }

        out << render(template: '/layouts/sidebar', model: [category: category, notificationCount: notificationCount])

    } // sidebar

    def encodedURL = { attrs, body ->
        def redirectUrl = request.forwardURI.substring(request.contextPath.length())
        def queryString = request.queryString ? '?'+request.queryString : ''

        if(attrs.withDomain) redirectUrl = grailsApplication.config.grails.serverURL + redirectUrl

        out << URLEncoder.encode(redirectUrl+queryString, request.characterEncoding)
    }
}
