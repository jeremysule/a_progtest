package com.jeremysule.progtest;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static com.jeremysule.progtest.Utils.*;

/**
 * Generate a weighed random value from a given Integer set.
 *
 * This implementation is using float to handle value weight. It comes with
 * all the float caveats around precision. See reference for primitive type float.
 * This implementation makes a defensive copy of the arrays.
 *
 */
public class RandomGen {

    private final int[] randomNums;

    private final float[] probabilitiesRange;

    private final Random random = new Random();

    public RandomGen(int[] randomNums, float[] probabilities) {

        checkValidArguments(randomNums,probabilities);

        this.randomNums =  Arrays.copyOf(randomNums, randomNums.length);

        this.probabilitiesRange = Arrays.copyOf(probabilities, probabilities.length);

        for (int i = 1; i < probabilitiesRange.length; i++) {
            probabilitiesRange[i] += probabilitiesRange[i-1];
        }
    }

    private void checkValidArguments(int[] randomNums, float[] probabilities) {
        if(randomNums.length == 0  ){
            throw new IllegalArgumentException(String.format(ZERO_FORMATTED, "numbers"));
        }
        if(probabilities.length == 0  ){
            throw new IllegalArgumentException(String.format(ZERO_FORMATTED, "probabilities"));
        }
        if (probabilities.length != randomNums.length){
            throw new IllegalArgumentException(ARRAY_SIZE_NO_MATCH);
        }
        for (float prob: probabilities){
            if (compare(0,prob) > 0 ){
                throw new IllegalArgumentException(String.format(PROBS_OUTSIDE_RANGE_FORMATTED,prob));
            }
            if (compare(1,prob) < 0 ){
                throw new IllegalArgumentException(String.format(PROBS_OUTSIDE_RANGE_FORMATTED,prob));
            }
        }
        float sum = (float) IntStream.range(0, probabilities.length)
                .mapToDouble(i -> probabilities[i]).sum();
        if (!isEqual(sum,1f)){
            throw new IllegalArgumentException(String.format(PROBS_NOT_ADD1_FORMATTED,sum));
        }
    }


    int searchAndRetrieveNum(float valueInRange){
        int ceilingIndex = Arrays.binarySearch(probabilitiesRange,valueInRange);
        if ( ceilingIndex < 0) {
            ceilingIndex = ~ceilingIndex;
        }
        return randomNums[ceilingIndex];
    }

    /**

     Returns one of the randomNums. When this method is called
     multiple times over a long period, it should return the
     numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        return searchAndRetrieveNum(random.nextFloat());
    }

}
