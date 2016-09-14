package com.jeremysule.progtest;

class Utils {

    public static final float PRECISION = 0.000001f;

    public static final String ZERO_FORMATTED = "Array for %s must not be empty.";

    public static final String ARRAY_SIZE_NO_MATCH = "Array sizes must match.";

    public static final String PROBS_NOT_ADD1_FORMATTED = "Probabilities must add to 1, current sum is %6f";

    public static final String PROBS_OUTSIDE_RANGE_FORMATTED = "Probabilities must be betwwen 0.0f and 1.0f but found %f";

    /**
     * Equaity comparison for float, with {@link #PRECISION}
     * @param a
     * @param b
     * @return true if a and b can be considered equals.
     */
    public static boolean isEqual(float a, float b) {
        return compare(a,b) == 0;
    }

    /**
     * Equality comparison for float, with {@link #PRECISION}
     *
     * @param a
     * @param b
     * @return 0 if can be considered equals, -1 id a&lt;b, 1 if a&gt;b
     */
    public static int compare(float a, float b) {
        float diff =  a-b;
        if (Math.abs(diff) < PRECISION)
            return 0;
        if (diff < 0.0f)
            return -1;
        return 1;
    }




}
