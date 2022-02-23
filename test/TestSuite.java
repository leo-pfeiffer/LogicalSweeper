import delegate.GameTest;
import logic.BooleanArrayTest;
import logic.EncoderTest;
import logic.KnowledgeBaseTest;
import model.agent.AgentFactoryTest;
import model.agent.BasicAgentTest;
import model.agent.BeginnerAgentTest;
import model.agent.IntermediateAgentTest;
import model.board.ViewTest;
import model.board.WorldTest;
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
        BooleanArrayTest.class,
        EncoderTest.class,
        GameTest.class,
        IntermediateAgentTest.class,
        KnowledgeBaseTest.class,
        ViewTest.class,
        WorldTest.class,
})
public class TestSuite {
}