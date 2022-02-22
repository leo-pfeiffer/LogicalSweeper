import delegate.GameTest;
import model.AgentFactoryTest;
import model.BasicAgentTest;
import model.BeginnerAgentTest;
import model.ViewTest;
import model.WorldTest;
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