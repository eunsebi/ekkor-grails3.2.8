package xyz.ekkor

class LoggedIn {

    User user
    Date dateCreated
    String remoteAddr

    static constraints = {
        remoteAddr nullable: true
    }

    static mapping = {
        remoteAddr dateCreated: 'desc'
    }

    @Override
    String toString() {
        "${remoteAddr} | ${dateCreated}"
    }
}
