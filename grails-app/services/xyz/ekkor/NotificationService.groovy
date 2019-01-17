package xyz.ekkor

import grails.transaction.Transactional

@Transactional
class NotificationService {

    def serviceMethod() {

    }

    def count(Avatar avatar) {
        NotificationRead notificationRead = NotificationRead.findOrSaveByAvatar(avatar)

        def query = Notification.where {
            and {
                eq('receiver', avatar)
                gt('lastUpdated', notificationRead.lastRead)
            }
        }
        query.count()
    } // count
}
