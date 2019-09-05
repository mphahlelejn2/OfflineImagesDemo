package com.jeff.offlineimagesdemo.model
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false,name = "data")
data class Data(@field:Element(name = "images") @param:Element(name = "images") var images: Images)