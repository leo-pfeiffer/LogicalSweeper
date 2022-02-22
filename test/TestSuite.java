import delegate.GameTest;
import models.AgentFactoryTest;
import models.BasicAgentTest;
import models.BeginnerAgentTest;
import models.ViewTest;
import models.WorldTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all JUnit tests.
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameTest.class,
        AgentFactoryTest.class,
        BasicAgentTest.class,
        BeginnerAgentTest.class,
        ViewTest.class,
        WorldTest.class,
})
public class TestSuite {
}