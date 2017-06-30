package ctci;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


/**
 * Created by chungnguyen on 6/26/17.
 */
public class ArraysAndStringsTest {

    ArraysAndStrings as = new ArraysAndStrings();

    @Test
    public void isPermutationPalindrome() {
        String tacoCat = "taco cat"; // true
        String nope = "NOPE"; // false

        boolean bool = as.isPermutationPalindrome(tacoCat);
        System.out.println(bool);
        Assert.assertThat(bool, is(true));


        boolean bool1 = as.isPermutationPalindrome(nope);
        System.out.println(bool1);
        Assert.assertThat(bool1, is(false));
    }

}