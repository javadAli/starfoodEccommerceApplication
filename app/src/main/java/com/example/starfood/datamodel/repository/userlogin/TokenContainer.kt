package com.example.starfood.datamodel.repository.userlogin

object TokenContainer {
    var token: String? = null
        private set
    var psn: String? = null
        private set
    private var userName: String? = null
    var sessionId:String?=null
    fun sessionIdUPdate(sessionId: String?) {
        this.sessionId = sessionId
    }
    fun tokenUpdate(token: String?) {
        this.token = token
    }
    fun psnUpdate(psn:String?) {
        this.psn = psn
    }
    fun userNameUpdate(userName:String?) {
        this.userName = userName
    }
}