import models.BasicAgentTest;
import models.ViewTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *  Test suite for all JUnit tests.
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {BasicAgentTest.class, ViewTest.class}
)
public class TestSuite { }