package xyz.ekkor

import grails.converters.JSON
import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    /*def init = { servletContext ->

        final boolean flush = true
        final boolean failOnError = true

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: flush)

        def adminUser = new User(
                username: 'admin',
                password: 'admin123',
                person: new Person(fullName: '관리자', email: 'admin@ekkor.xyz'),
                avatar: new Avatar(nickname: '관리자'),
                enabled: true,
                createIp: '0.0.0.0'
        )
        //adminUser.enabled = true
        //adminUser.createIp = '0.0.0.0'

        //adminUser.save(flush: flush, failOnError: failOnError)
        userService.saveUser adminUser

        new UserRole(user: adminUser, role: adminRole).save(flush: flush, failOnError: failOnError)
    }*/

    UserService userService

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def classRole = new Role(authority: 'ROLE_CLASS').save(flush: true)
        def leaderRole = new Role(authority: 'ROLE_LEADER').save(flush: true)
        def maintRole = new Role(authority: 'ROLE_MAINT').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        /*environments {
            development {*/
                //if (!User.findByUsername('admin')) {

                    // Admin user 생성
                    def adminUser = new User(
                            username: 'admin',
                            password: 'admin123',
                            person: new Person(fullName: '관리자', email: 'admin@ekkor.xyz'),
                            avatar: new Avatar(nickname: '관리자')
                    )
                    adminUser.enabled = true
                    adminUser.createIp = '0.0.0.0'
                    userService.saveUser adminUser
                    UserRole.create(adminUser, adminRole, true)
                //} // if

                //if (!User.findByUsername('testuser')) {

                    // 테스트 User 생성
                    def testUser = new User(
                            username: 'test111',
                            password: 'test111',
                            person: new Person(fullName: '테스트사용자', email: 'test@ekkor.xyz'),
                            avatar: new Avatar(nickname: '테스트사용자')
                    )

                    testUser.enabled = true
                    testUser.createIp = '0.0.0.0'
                    userService.saveUser testUser
                    UserRole.create(testUser, userRole, true)
                //} testuser if

                def questionsCategory = Category.get('questions') ?: new Category(code: 'questions', labelCode: 'questions.label', defaultLabel: 'Q&A', iconCssNames: 'fa fa-database', sortOrder: 0, writable: true, useNote: true, useOpinion: true, useEvaluate: true, useTag: true, requireTag: true).save(flush: true)
                def techCategory = Category.get('tech') ?: new Category(code: 'tech', labelCode: 'tech.label', defaultLabel: 'Tech', iconCssNames: 'fa fa-code', sortOrder: 1, writable: false, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)
                def communityCategory = Category.get('community') ?: new Category(code: 'community', labelCode: 'community.label', defaultLabel: '커뮤니티', iconCssNames: 'fa fa-comments', sortOrder: 2, writable: false, useNote: true, useOpinion: false, useEvaluate: false, useTag: false).save(flush: true)
                def columnsCategory = Category.get('columns') ?: new Category(code: 'columns', labelCode: 'columns.label', defaultLabel: '칼럼', iconCssNames: 'fa fa-quote-left', sortOrder: 3, writable: true, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)
                def jobsCategory = Category.get('jobs') ?: new Category(code: 'jobs', labelCode: 'jobs.label', defaultLabel: 'Jobs', iconCssNames: 'fa fa-group', sortOrder: 4, writable: false, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)

                // 2 Level Category

                // Tech
                def newsCategory = Category.get('news') ?: new Category(code: 'news', parent: techCategory, labelCode: 'news.label', defaultLabel: 'IT News & 정보', iconCssNames: 'fa fa-code', sortOrder: 0, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)
                def tipsCategory = Category.get('tips') ?: new Category(code: 'tips', parent: techCategory, labelCode: 'tips.label', defaultLabel: 'Tips & Tricks', iconCssNames: 'fa fa-code', sortOrder: 1, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)

                // Community
                def noticeCategory = Category.get('notice') ?: new Category(code: 'notice', parent: communityCategory, labelCode: 'notice.label', defaultLabel: '공지사항', iconCssNames: 'fa fa-comments', sortOrder: 0, useNote: true, useOpinion: false, useEvaluate: false, useTag: true, adminOnly: true).save(flush: true)
                def lifeCategory = Category.get('life') ?: new Category(code: 'life', parent: communityCategory, labelCode: 'life.label', defaultLabel: '사는얘기', iconCssNames: 'fa fa-comments', sortOrder: 1, useNote: true, useOpinion: false, useEvaluate: false, useTag: false).save(flush: true)
                def forumCategory = Category.get('forum') ?: new Category(code: '포럼', parent: communityCategory, labelCode: 'forum.label', defaultLabel: 'Forum', iconCssNames: 'fa fa-code', sortOrder: 1, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)
                def gatheringCategory = Category.get('gathering') ?: new Category(code: 'gathering', parent: communityCategory, labelCode: 'gathering.label', defaultLabel: '정기모임/스터디', iconCssNames: 'fa fa-comments', sortOrder: 2, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)
                def promoteCategory = Category.get('promote') ?: new Category(code: 'promote', parent: communityCategory, labelCode: 'gathering.label', defaultLabel: '학원', iconCssNames: 'fa fa-comments', sortOrder: 3, useNote: true, useOpinion: false, useEvaluate: false).save(flush: true)

                // Job
                //def evalcomCategory = Category.get('evalcom') ?: new Category(code: 'evalcom', parent: jobsCategory, labelCode: 'gathering.label', defaultLabel: '좋은회사/나쁜회사', iconCssNames: 'fa fa-group', sortOrder: 0, useNote: true, useOpinion: false, useEvaluate: false, useTag: false, anonymity: true).save(flush: true)
                //def recruitCategory = Category.get('recruit') ?: new Category(code: 'recruit', parent: jobsCategory, labelCode: 'recruit.label', defaultLabel: '구인', iconCssNames: 'fa fa-group', sortOrder: 1, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)
                //def resumesCategory = Category.get('resumes') ?: new Category(code: 'resumes', parent: jobsCategory, labelCode: 'resumes.label', defaultLabel: '구직', iconCssNames: 'fa fa-group', sortOrder: 3, useNote: true, useOpinion: false, useEvaluate: false, useTag: true).save(flush: true)

            //} // development
       // } // environments

        /*JSON.registerObjectMarshaller(Notification) { notification ->
            def obj = [:]
            obj['type'] = notification.type.toString()
            obj['article'] = [id: notification.articleId]
            obj['content'] = [id: notification.contentId]
            obj['dateCreated'] = notification.dateCreated
            obj['lastUpdated'] = notification.lastUpdated
            obj['sender'] = notification.sender

            return obj
        }

        JSON.registerObjectMarshaller(Content) {
            def obj = [:]
            obj['type'] = it.type.toString()
            obj['textType'] = it.textType.toString()
            obj['text'] = it.text
            obj['voteCount'] = it.voteCount
            obj['selected'] = it.selected
            obj['lastEditor'] = it.lastEditor
            obj['dateCreated'] = it.dateCreated
            obj['lastUpdated'] = it.lastUpdated
            return obj
        }*/
    } // servletContext

    def destroy = {
    }
}
