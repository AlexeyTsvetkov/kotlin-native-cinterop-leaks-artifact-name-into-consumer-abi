package org.bar

class Bar {
    fun bar() {
        println("Bar")
        org.foo.Foo().foo()
    }
}