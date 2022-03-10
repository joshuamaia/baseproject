package br.com.joshua.baseproject;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@Suite
@SelectClasses({
            PersonTests.class,
            ExpenseControlTests.class
})
public class RunAllTests {

}
