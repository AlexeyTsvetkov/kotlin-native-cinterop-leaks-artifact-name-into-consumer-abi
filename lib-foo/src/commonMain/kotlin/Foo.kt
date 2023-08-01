package org.foo
open class Foo {
    fun foo() {
        println("Foo")
        org.foo.internal.FooInternal().fooInternal()
    }
}