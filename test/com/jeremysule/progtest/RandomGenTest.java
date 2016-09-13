package com.jeremysule.progtest;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class RandomGenTest {


    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void testConstructorZeroProbs() throws Exception {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage(String.format(Utils.ZERO_FORMATTED,"probabilities"));

        int[] nums = {-5,6,5,62};
        float[] probs ={} ;

        RandomGen rGen = new RandomGen(nums, probs);
    }

    @Test
    public void testConstructorZeroNums() throws Exception {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage(String.format(Utils.ZERO_FORMATTED,"numbers"));


        int[] nums = {};
        float[] probs ={1f} ;

        RandomGen rGen = new RandomGen(nums, probs);
    }


    @Test
    public void testConstructorSizeNoMatch() throws Exception {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage(Utils.ARRAY_SIZE_NO_MATCH);

        int[] nums = {5};
        float[] probs ={0.5f, 0.5f} ;

        RandomGen rGen = new RandomGen(nums, probs);
    }


    @Test
    public void testConstructorProbsNoAddTo1() throws Exception {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage(String.format(Utils.PROBS_NOT_ADD1_FORMATTED, 0.8f));

        int[] nums = {1, 5};
        float[] probs ={0.5f, 0.3f} ;

        RandomGen rGen = new RandomGen(nums, probs);
    }

    @Test
    public void testConstructorProbsOutSideRange1() throws Exception {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage(String.format(Utils.PROBS_OUTSIDE_RANGE_FORMATTED,"-0.500000"));


        int[] nums = {1, 5};
        float[] probs ={-0.5f, 0.3f} ;

        RandomGen rGen = new RandomGen(nums, probs);
    }


    @Test
    public void testConstructorProbsOutSideRange2() throws Exception {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage(String.format(Utils.PROBS_OUTSIDE_RANGE_FORMATTED,"1.500000"));


        int[] nums = {1, 5};
        float[] probs ={1.5f, 0.3f} ;

        RandomGen rGen = new RandomGen(nums, probs);
    }





    @Test
    public void testNextNumForOne() throws Exception {
        int[] nums = {18};
        float[] probs ={1f} ;

        RandomGen rGen = new RandomGen(nums, probs);

        for (int i = 0; i < 50 ; i++) {
            assertEquals(18,rGen.nextNum());
        }


    }

    @Test
    public void testRepartition() throws Exception {
        int[] nums = {0,1,2,3};
        float[] probs ={0.1f,0.2f,0.3f,0.4f} ;
        RandomGen rGen = new RandomGen(nums, probs);
        int[] counter = new int[4];

        int totalRuns = 10_000_000;
        for (int i = 0; i < totalRuns; i++) {
            counter[rGen.nextNum()]++;
        }
        float testPrecision = 0.001f; //rough
        for (int i = 0; i < 4; i++) {
            assertTrue("Real spread should roughly follow defined probability", Math.abs(counter[i]/(float)totalRuns - probs[i]) < testPrecision);
        }

    }


    @Test
    public void testSearchAndRetrieveNum() throws Exception {
        int[] nums = {18};
        float[] probs ={1f} ;
        RandomGen rGen = new RandomGen(nums, probs);
        assertEquals(18,rGen.searchAndRetrieveNum(0.44f));

        rGen = new RandomGen(new int[]{10,20,30}, new float[]{1f/3,1f/3,1f/3} );
        assertEquals(10,rGen.searchAndRetrieveNum(0f));
        assertEquals(10,rGen.searchAndRetrieveNum(1f/6));
        assertEquals(10,rGen.searchAndRetrieveNum(1f/3));
        assertEquals(20,rGen.searchAndRetrieveNum(1f/3+1f/6));
        assertEquals(20,rGen.searchAndRetrieveNum(2f/3));
        assertEquals(30,rGen.searchAndRetrieveNum(2f/3+1f/6));
        assertEquals(30,rGen.searchAndRetrieveNum(1f));



    }



}