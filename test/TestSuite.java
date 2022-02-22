import delegate.GameTest;
import logic.DNFEncoderTest;
import logic.EntailmentCheckerTest;
import logic.KnowledgeBaseTest;
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
        AgentFactoryTest.class,
        BasicAgentTest.class,
        BeginnerAgentTest.class,
        DNFEncoderTest.class,
        EntailmentCheckerTest.class,
        GameTest.class,
        KnowledgeBaseTest.class,
        ViewTest.class,
        WorldTest.class,
})
public class TestSuite {
}