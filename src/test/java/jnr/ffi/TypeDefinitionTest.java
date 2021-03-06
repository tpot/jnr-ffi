package jnr.ffi;

import jnr.ffi.annotations.LongLong;
import jnr.ffi.types.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TypeDefinitionTest {

    public TypeDefinitionTest() {


    }
    public static interface TestLib {
        public @int8_t int add_int8_t(@int8_t int i1, @int8_t int i2);
        public @int8_t int add_int8_t(@int8_t Integer i1, @int8_t int i2);
        public @int8_t Long add_int8_t(@int8_t Long i1, @int8_t Integer i2);
        public int add_uint8_t(@u_int8_t int i1, @u_int8_t int i2);
        public int ret_uint8_t(@u_int8_t int i1);
        public @u_int32_t long ret_uint32_t(@u_int32_t long i1);
        public @u_int32_t short ret_uint32_t(@u_int32_t byte i1);
        public void ret_long(@size_t int i1);
    }

    static TestLib testlib;

    @BeforeClass
    public static void setUpClass() throws Exception {
        testlib = TstUtil.loadTestLib(TestLib.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        testlib = null;
    }

    @Test public void doNothing() {
    }

    @Test public void returnUnsigned8() {
        int i1 = 0xdead0001;
        // when passed to the native function, only the lowest 8 bits are passed
        assertEquals("incorrect value returned", 1, testlib.ret_uint8_t(i1));
    }
    @Test public void addUnsigned8() {
        int i1 = 0xdead0001;
        int i2 = 0xbeef0002;
        // when passed to the native function, only the lowest 8 bits are passed
        assertEquals("did not add correctly", 3, testlib.add_uint8_t(i1, i2));
    }
}
