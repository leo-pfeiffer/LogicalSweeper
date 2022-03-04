import delegate.ObscuredSweeperTest;
import logic.BooleanArrayTest;
import logic.CnfEncoderTest;
import logic.DnfEncoderTest;
import logic.KnowledgeBaseTest;
import model.agent.AgentFactoryTest;
import model.agent.BasicAgentTest;
import model.agent.SpsAgentTest;
import model.agent.SpsSatAgentTest;
import model.board.ViewTest;
import model.board.RectWorldTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all JUnit tests.
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AgentFactoryTest.class,
        BasicAgentTest.class,
        SpsAgentTest.class,
        BooleanArrayTest.class,
        CnfEncoderTest.class,
        DnfEncoderTest.class,
        ObscuredSweeperTest.class,
        SpsSatAgentTest.class,
        KnowledgeBaseTest.class,
        ViewTest.class,
        RectWorldTest.class,
})
public class TestSuite {
}