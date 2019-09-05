package com.jeff.offlineimagesdemo.data

interface SaveCallBack {
    fun onComplete()
    fun onError(toString: String)
}
