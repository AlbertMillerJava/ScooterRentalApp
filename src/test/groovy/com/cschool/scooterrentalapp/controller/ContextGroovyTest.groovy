package com.cschool.scooterrentalapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification



@ContextConfiguration
class ContextGroovyTest extends Specification{
    @Autowired
    private ApplicationContext applicationContext

    def setup(){
        println(applicationContext)
    }

    def "Should load application context"(){
        expect:
        applicationContext != null
    }
}
