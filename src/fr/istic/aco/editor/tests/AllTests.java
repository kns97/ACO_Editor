package fr.istic.aco.editor.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Utility class to run all the tests
 */
@Suite
@SelectClasses({ CmdTests.class, EngineTest.class, GreetingsInvokerImplTest.class, HistoryTest.class })
public class AllTests {

}
