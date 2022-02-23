package logic;

import model.logic.DNFEncoder;
import org.junit.Test;
import org.logicng.formulas.Formula;

public class DNFEncoderTest {

    @Test
    public void testSetup() {
        DNFEncoder dnf = new DNFEncoder();
        String[] cells = {"D1", "D2", "D3", "D4"};
        Formula formula = dnf.encode(cells, 2, 0);
        int x = 1;
    }

}
