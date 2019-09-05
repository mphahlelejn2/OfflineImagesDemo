package com.jeff.offlineimagesdemo.model
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "response")
data class ServerRespond( @field:Element(name = "data") @param:Element(name = "data") var data: Data
)
