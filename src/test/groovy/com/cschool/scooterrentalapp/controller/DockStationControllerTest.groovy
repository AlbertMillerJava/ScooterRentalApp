package com.cschool.scooterrentalapp.controller

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification



@ContextConfiguration
class DockStationControllerTest extends Specification{
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
