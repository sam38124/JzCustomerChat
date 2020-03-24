package com.example.customerlibrary

class AdminBeans {
    companion object {
        fun setMessageInstance(admin:String,password:String){
            this.admin=admin
            this.password=password
        }
        var admin=""
        var password=""
    }
}